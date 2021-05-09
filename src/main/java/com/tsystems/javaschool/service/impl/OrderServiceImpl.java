package com.tsystems.javaschool.service.impl;

import com.tsystems.javaschool.dao.ClientDAO;
import com.tsystems.javaschool.dao.OrderDAO;
import com.tsystems.javaschool.dao.ProductDAO;
import com.tsystems.javaschool.dto.CartDTO;
import com.tsystems.javaschool.dto.CartItemDTO;
import com.tsystems.javaschool.dto.ClientDTO;
import com.tsystems.javaschool.dto.OrderDTO;
import com.tsystems.javaschool.entity.Order;
import com.tsystems.javaschool.entity.ProductOrdered;
import com.tsystems.javaschool.service.OrderService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.util.Date;


@Service
@Transactional
public class OrderServiceImpl implements OrderService {
    private final OrderDAO orderDAO;
    private final ProductDAO productDAO;
    private final ClientDAO clientDAO;

    public OrderServiceImpl(OrderDAO orderDAO, ProductDAO productDAO, ClientDAO clientDAO) {
        this.orderDAO = orderDAO;
        this.productDAO = productDAO;
        this.clientDAO = clientDAO;
    }

    @Override
    public int addOrder(CartDTO cart, ClientDTO client, OrderDTO orderDTO) {
        Order order = new Order();

        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        order.setDate(format.format(new Date()));
        order.setClient(clientDAO.findByMail(client.getEmail()));
        order.setPayment(orderDTO.getPayment());
        order.setShipping(orderDTO.getShipping());
        order.setStatus("new");

        orderDAO.addOrder(order);


        for (CartItemDTO item: cart.getCartItems()
             ) {
            ProductOrdered productOrdered=new ProductOrdered();

            productOrdered.setOrder(order);
            productOrdered.setPrice(item.getAmount());
            productOrdered.setQuantity(item.getQuantity());
            productOrdered.setProduct(productDAO.getById(item.getProduct().getId()));

            orderDAO.addProductOrdered(productOrdered);
        }

        cart.deleteAll();
        return order.getId();
    }
}
