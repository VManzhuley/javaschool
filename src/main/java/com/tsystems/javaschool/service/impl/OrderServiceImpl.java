package com.tsystems.javaschool.service.impl;

import com.tsystems.javaschool.dao.ClientDAO;
import com.tsystems.javaschool.dao.OrderDAO;
import com.tsystems.javaschool.dao.ProductDAO;
import com.tsystems.javaschool.dto.*;
import com.tsystems.javaschool.entity.Order;
import com.tsystems.javaschool.entity.ProductOrdered;
import com.tsystems.javaschool.service.ClientService;
import com.tsystems.javaschool.service.OrderService;
import com.tsystems.javaschool.service.ProductService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

    public OrderServiceImpl(OrderDAO orderDAO, ProductDAO productDAO, ClientDAO clientDAO, ClientService clientService, ProductService productService) {
        this.orderDAO = orderDAO;
        this.productDAO = productDAO;
        this.clientDAO = clientDAO;
        this.clientService = clientService;
        this.productService = productService;
    }

    @Override
    public int addOrder(CartDTO cart, ClientDTO client, OrderDTO orderDTO) {
        Order order = new Order();

        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        order.setDate(format.format(new Date()));
        order.setClient(clientDAO.findByMail(client.getEmail()));
        order.setPayment(orderDTO.getPayment());
        order.setShipping(orderDTO.getShipping());
        order.setStatus("New");

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

        cart.deleteAll();
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
        orderDTO.setAmountTotal(orderDAO.getAmount(order.getId()));

        return orderDTO;
    }

    @Override
    public List<OrderDTO> allByPage(int page) {
        return orderDAO.allByPage(page).stream().map(this::mapToOrderDTO).collect(Collectors.toList());
    }

    @Override
    public int getTotalPages() {
        return orderDAO.getTotalPages();
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
    public void updateStatus(OrderDTO orderDTO, String status) {
        Order order= orderDAO.getById(orderDTO.getId());
        order.setStatus(status);
        orderDAO.update(order);
    }
}
