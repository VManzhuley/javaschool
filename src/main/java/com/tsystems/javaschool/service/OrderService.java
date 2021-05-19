package com.tsystems.javaschool.service;

import com.tsystems.javaschool.dto.CartDTO;
import com.tsystems.javaschool.dto.ClientDTO;
import com.tsystems.javaschool.dto.OrderDTO;
import com.tsystems.javaschool.dto.ProductOrderedDTO;
import com.tsystems.javaschool.entity.Order;
import com.tsystems.javaschool.entity.ProductOrdered;

import java.security.Principal;
import java.util.List;


public interface OrderService {

    long addOrder(CartDTO cart, ClientDTO clientDTO, OrderDTO order, Principal principal);

    OrderDTO mapToOrderDTO(Order order);

    List<OrderDTO> allByPage(int page);

    long getTotalPagesToAdmin();

    OrderDTO getById(int id);

    ProductOrderedDTO mapToProductOrderedDTO(ProductOrdered productOrdered);

    void updateStatus(int idOrder, String status);

    List<OrderDTO> allByClientAndPage(Principal principal, int page);

    long getTotalPagesToUser(Principal principal);

    CartDTO repeatOrder(int id, Principal principal, CartDTO cartDTO);

    void returnProduct(Order order);


}
