package com.tsystems.javaschool.service;

import com.tsystems.javaschool.dto.CartDTO;
import com.tsystems.javaschool.dto.ClientDTO;
import com.tsystems.javaschool.dto.OrderDTO;
import com.tsystems.javaschool.dto.ProductOrderedDTO;
import com.tsystems.javaschool.entity.Order;
import com.tsystems.javaschool.entity.ProductOrdered;

import java.util.List;


public interface OrderService {
    int addOrder(CartDTO cart, ClientDTO client, OrderDTO order);
    OrderDTO mapToOrderDTO(Order order);
    List<OrderDTO> allByPage(int page);
    int getTotalPages();
    OrderDTO getById(int id);
    ProductOrderedDTO mapToProductOrderedDTO(ProductOrdered productOrdered);
    void updateStatus(OrderDTO orderDTO,String status);
}
