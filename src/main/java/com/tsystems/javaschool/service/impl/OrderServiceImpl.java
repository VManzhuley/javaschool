package com.tsystems.javaschool.service.impl;

import com.tsystems.javaschool.dao.ClientDAO;
import com.tsystems.javaschool.dao.OrderDAO;
import com.tsystems.javaschool.dao.ProductDAO;
import com.tsystems.javaschool.dao.StatisticDAO;
import com.tsystems.javaschool.dto.*;
import com.tsystems.javaschool.entity.Client;
import com.tsystems.javaschool.entity.Order;
import com.tsystems.javaschool.entity.ProductOrdered;
import com.tsystems.javaschool.entity.Status;
import com.tsystems.javaschool.entity.product.Product;
import com.tsystems.javaschool.error.BusinessLogicException;
import com.tsystems.javaschool.error.WrongParameterException;
import com.tsystems.javaschool.messaging.JMSProducer;
import com.tsystems.javaschool.service.CartService;
import com.tsystems.javaschool.service.ClientService;
import com.tsystems.javaschool.service.OrderService;
import com.tsystems.javaschool.service.ProductService;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.security.Principal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;


@Service
@Transactional
@Log4j2
public class OrderServiceImpl implements OrderService {

    private final OrderDAO orderDAO;
    private final ProductDAO productDAO;
    private final ClientDAO clientDAO;
    private final ClientService clientService;
    private final ProductService productService;
    private final CartService cartService;
    private final JMSProducer jmsProducer;
    private final StatisticDAO statisticDAO;

    public OrderServiceImpl(OrderDAO orderDAO,
                            ProductDAO productDAO,
                            ClientDAO clientDAO,
                            ClientService clientService,
                            ProductService productService,
                            CartService cartService,
                            JMSProducer jmsProducer,
                            StatisticDAO statisticDAO) {
        this.orderDAO = orderDAO;
        this.productDAO = productDAO;
        this.clientDAO = clientDAO;
        this.clientService = clientService;
        this.productService = productService;
        this.cartService = cartService;
        this.jmsProducer = jmsProducer;
        this.statisticDAO = statisticDAO;
    }

    @Override
    public long createOrder(CartDTO cart, ClientDTO clientDTO, OrderDTO orderDTO) {

        cartService.checkAvailability(cart);

        if (cart.getIsMissQuantity()) {
            log.warn("Client: {} trying to make order, but some products are missing", cart.getUserName());
            throw new BusinessLogicException("Some products in cart are missing on warehouse");
        }


        for (CartItemDTO item : cart.getCartItems()) {
            Product product = productDAO.getById(item.getProduct().getId());
            product.setQuantity(product.getQuantity() - item.getQuantity());
            productDAO.update(product);
        }

        Client client;
        if (cart.getUserName() != null) {
            clientDTO.setUserNameParent(cart.getUserName());
            ClientDTO clientPrincipal = clientService.getByUserName(cart.getUserName());

            if (clientPrincipal.equals(clientDTO)) {
                client = clientDAO.findByUserName(cart.getUserName());
            } else {
                if (clientService.emailExist(clientDTO.getEmail()) && !clientPrincipal.getEmail().equals(clientDTO.getEmail())) {
                    log.warn("Client: {} trying to make order with mail: {}, but this mail is already exist", clientPrincipal.getEmail(), clientDTO.getEmail());
                    throw new WrongParameterException("There is an account with that email address: " + clientDTO.getEmail());
                }

                client = clientService.createClient(clientDTO);
            }
        } else {
            if (clientService.emailExist(clientDTO.getEmail())) {
                log.warn("New client trying to make order with mail: {}, but this mail is already exist", clientDTO.getEmail());
                throw new WrongParameterException("There is an account with that email address: " + clientDTO.getEmail());
            }

            client = clientService.createClient(clientDTO);
        }

        Order order = new Order();

        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        order.setDate(format.format(new Date()));
        order.setClient(client);
        order.setPayment(orderDTO.getPayment());
        order.setShipping(orderDTO.getShipping());
        order.setStatus(Status.NEW);
        order.setAmount(cart.getAmountTotal());

        log.info("Client: {} make a new order №{}", clientDTO.getEmail(), order.getId());
        orderDAO.createOrder(order);

        List<ProductOrdered> topBeforeOrder = statisticDAO.topProductOrdered("ALL", "quantity", 10);

        for (CartItemDTO item : cart.getCartItems()) {
            ProductOrdered productOrdered = new ProductOrdered();

            productOrdered.setOrder(order);
            productOrdered.setPrice(item.getAmount());
            productOrdered.setQuantity(item.getQuantity());
            productOrdered.setProduct(productDAO.getById(item.getProduct().getId()));

            log.info("Product: {} added to ordered to order№{}", productOrdered.getProduct().getId(), order.getId());
            orderDAO.createProductOrdered(productOrdered);
        }

        List<ProductOrdered> topAfterOrder = statisticDAO.topProductOrdered("ALL", "quantity", 10);

        for (int i = 0; i < 10; i++) {
            if (topAfterOrder.get(i).getProduct().getId() != topBeforeOrder.get(i).getProduct().getId()) {
                log.info("Top product will be updated!");
                jmsProducer.sendMessage();
                break;
            }
        }

        cartService.removeAll(cart);

        return order.getId();
    }

    @Override
    public OrderDTO mapToOrderDTO(Order order) {
        OrderDTO orderDTO = new OrderDTO();

        orderDTO.setId(order.getId());
        orderDTO.setDate(order.getDate());
        orderDTO.setPayment(order.getPayment());
        orderDTO.setShipping(order.getShipping());
        orderDTO.setStatus(order.getStatus());
        orderDTO.setClient(clientService.mapToClientDTO(order.getClient()));
        orderDTO.setAmount(order.getAmount());

        return orderDTO;
    }

    @Override
    public List<OrderDTO> getAllByPage(int page) {
        return orderDAO.getAllByPage(page).stream().map(this::mapToOrderDTO).collect(Collectors.toList());
    }

    @Override
    public long getTotalPagesToAdmin() {
        return orderDAO.getTotalPagesToAdmin();
    }

    @Override
    public OrderDTO getById(long id) {
        Order order = orderDAO.getById(id);
        OrderDTO orderDTO = mapToOrderDTO(order);

        orderDTO.setProductOrderedList(order.getProductOrderedList().stream().map(this::mapToProductOrderedDTO).collect(Collectors.toList()));

        return orderDTO;
    }

    @Override
    public ProductOrderedDTO mapToProductOrderedDTO(ProductOrdered productOrdered) {
        ProductOrderedDTO productOrderedDTO = new ProductOrderedDTO();

        productOrderedDTO.setProduct(productService.mapToProductDTO(productOrdered.getProduct()));
        productOrderedDTO.setAmount(productOrdered.getPrice());
        productOrderedDTO.setQuantity(productOrdered.getQuantity());

        return productOrderedDTO;
    }

    @Override
    public void updateStatus(long idOrder, String status) {
        Order order = orderDAO.getById(idOrder);
        if ((order.getStatus() != Status.CANCELED) && (order.getStatus() != Status.COMPLETED)) {
            order.setStatus(Status.valueOf(status));

            log.info("Order№{} update its status to: {} ", idOrder, status);
            orderDAO.update(order);

            if (order.getStatus() == Status.CANCELED) {
                returnProduct(order);
            }

        }
    }

    @Override
    public List<OrderDTO> getAllByClientAndPage(Principal principal, int page) {
        Client client = clientDAO.findByUserName(principal.getName());

        return orderDAO.getAllByClientAndPage(client.getId(), page).stream().map(this::mapToOrderDTO).collect(Collectors.toList());
    }

    @Override
    public long getTotalPagesToUser(Principal principal) {
        Client client = clientDAO.findByUserName(principal.getName());
        return orderDAO.getTotalPagesToUser(client.getId());
    }

    @Override
    public void repeatOrder(long idOrder, Principal principal, CartDTO cartDTO) {
        log.info("Client: {} will repeat his order№{}", cartDTO.getUserName(), idOrder);
        cartService.removeAll(cartDTO);
        OrderDTO orderDTO = getById(idOrder);

        if (orderDTO.getClient().getEmail().equals(principal.getName()) ||
                orderDTO.getClient().getUserNameParent().equals(principal.getName())) {

            for (ProductOrderedDTO productOrderedDTO : orderDTO.getProductOrderedList()) {
                if (!productOrderedDTO.getProduct().isOutdated()) {
                    CartItemDTO item = new CartItemDTO();
                    item.setProduct(productOrderedDTO.getProduct());
                    item.setQuantity(productOrderedDTO.getQuantity());
                    cartDTO.addCartItem(item);
                }
            }
            cartService.mergeCart(cartDTO, principal);
        }
    }

    @Override
    public void returnProduct(Order order) {
        for (ProductOrdered productOrdered : order.getProductOrderedList()
        ) {
            Product product = productOrdered.getProduct();
            product.setQuantity(product.getQuantity() + productOrdered.getQuantity());

            log.info("Products for order№{} will be returned", order.getId());
            productDAO.update(product);
        }
    }

}
