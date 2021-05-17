package com.tsystems.javaschool.service.impl;

import com.tsystems.javaschool.dao.ClientDAO;
import com.tsystems.javaschool.dao.OrderDAO;
import com.tsystems.javaschool.dao.ProductDAO;
import com.tsystems.javaschool.dto.*;
import com.tsystems.javaschool.entity.Client;
import com.tsystems.javaschool.entity.Order;
import com.tsystems.javaschool.entity.ProductOrdered;
import com.tsystems.javaschool.entity.Status;
import com.tsystems.javaschool.entity.product.Product;
import com.tsystems.javaschool.error.BusinessLogicException;
import com.tsystems.javaschool.error.WrongParameterException;
import com.tsystems.javaschool.service.CartService;
import com.tsystems.javaschool.service.ClientService;
import com.tsystems.javaschool.service.OrderService;
import com.tsystems.javaschool.service.ProductService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.security.Principal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;


@Service
@Transactional
public class OrderServiceImpl implements OrderService {
    private final OrderDAO orderDAO;
    private final ProductDAO productDAO;
    private final ClientDAO clientDAO;
    private final ClientService clientService;
    private final ProductService productService;
    private final CartService cartService;

    public OrderServiceImpl(OrderDAO orderDAO, ProductDAO productDAO, ClientDAO clientDAO, ClientService clientService, ProductService productService, CartService cartService) {
        this.orderDAO = orderDAO;
        this.productDAO = productDAO;
        this.clientDAO = clientDAO;
        this.clientService = clientService;
        this.productService = productService;
        this.cartService = cartService;
    }

    @Override
    public long addOrder(CartDTO cart, ClientDTO clientDTO, OrderDTO orderDTO, Principal principal) {
        cartService.checkAvailability(cart);
        if (cart.getIsMissQuantity()) throw new BusinessLogicException();

        for (CartItemDTO item : cart.getCartItems()
        ) {
            Product product = productDAO.getById(item.getProduct().getId());
            product.setQuantity(product.getQuantity() - item.getQuantity());
            productDAO.update(product);
        }

        Client client;
        if (principal != null) {
            clientDTO.setUserNameParent(principal.getName());
            ClientDTO clientPrincipal = clientService.findByUserName(principal.getName());

            if (clientPrincipal.equals(clientDTO)) {
                client = clientDAO.findByUserName(principal.getName());
            } else {
                if (clientService.emailExist(clientDTO.getEmail()) && !clientPrincipal.getEmail().equals(clientDTO.getEmail()))
                    throw new WrongParameterException("There is an account with that email address: " + clientDTO.getEmail());

                client = clientService.add(clientDTO);
            }
        } else {
            if (clientService.emailExist(clientDTO.getEmail()))
                throw new WrongParameterException("There is an account with that email address: " + clientDTO.getEmail());

            client = clientService.add(clientDTO);
        }

        Order order = new Order();

        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        order.setDate(format.format(new Date()));
        order.setClient(client);
        order.setPayment(orderDTO.getPayment());
        order.setShipping(orderDTO.getShipping());
        order.setStatus(Status.NEW);

        orderDAO.addOrder(order);


        for (CartItemDTO item : cart.getCartItems()
        ) {
            ProductOrdered productOrdered = new ProductOrdered();

            productOrdered.setOrder(order);
            productOrdered.setPrice(item.getAmount());
            productOrdered.setQuantity(item.getQuantity());
            productOrdered.setProduct(productDAO.getById(item.getProduct().getId()));

            orderDAO.addProductOrdered(productOrdered);
        }

        cartService.removeAll(cart, principal);

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
    public List<OrderDTO> allByPage(int page) {
        return orderDAO.allByPage(page).stream().map(this::mapToOrderDTO).collect(Collectors.toList());
    }

    @Override
    public long getTotalPagesToAdmin() {
        return orderDAO.getTotalPagesToAdmin();
    }

    @Override
    public OrderDTO getById(int id) {
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
    public void updateStatus(int idOrder, String status) {
        Order order = orderDAO.getById(idOrder);
        if ((order.getStatus() != Status.CANCELED) && (order.getStatus() != Status.COMPLETED)) {
            order.setStatus(Status.valueOf(status));
            orderDAO.update(order);

            if (order.getStatus() == Status.CANCELED) {
                returnProduct(order);
            }

        }
    }

    @Override
    public List<OrderDTO> allByClientAndPage(Principal principal, int page) {
        Client client = clientDAO.findByUserName(principal.getName());

        return orderDAO.allByClientAndPage(client.getId(), page).stream().map(this::mapToOrderDTO).collect(Collectors.toList());
    }

    @Override
    public long getTotalPagesToUser(Principal principal) {
        Client client = clientDAO.findByUserName(principal.getName());
        return orderDAO.getTotalPagesToUser(client.getId());
    }

    @Override
    public CartDTO repeatOrder(int id, Principal principal, CartDTO cartDTO) {
        cartService.removeAll(cartDTO, principal);
        OrderDTO orderDTO = getById(id);


        if (orderDTO.getClient().getEmail().equals(principal.getName()) ||
                orderDTO.getClient().getUserNameParent().equals(principal.getName())) {

            for (ProductOrderedDTO productOrderedDTO : orderDTO.getProductOrderedList()) {
                CartItemDTO item = new CartItemDTO();
                item.setProduct(productOrderedDTO.getProduct());
                item.setQuantity(productOrderedDTO.getQuantity());
                cartDTO.addCartItem(item);
            }
            cartService.mergeCart(cartDTO, principal);
        }

        return cartDTO;
    }

    @Override
    public void returnProduct(Order order) {
        for (ProductOrdered productOrdered : order.getProductOrderedList()
        ) {
            Product product = productOrdered.getProduct();
            product.setQuantity(product.getQuantity() + productOrdered.getQuantity());
            productDAO.update(product);
        }
    }

}
