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

    long createOrder(CartDTO cart, ClientDTO clientDTO, OrderDTO order);

    OrderDTO mapToOrderDTO(Order order);

    List<OrderDTO> getAllByPage(int page);

    long getTotalPagesToAdmin();

    OrderDTO getById(long id);

    ProductOrderedDTO mapToProductOrderedDTO(ProductOrdered productOrdered);

    void updateStatus(long idOrder, String status);

    List<OrderDTO> getAllByClientAndPage(Principal principal, int page);

    long getTotalPagesToUser(Principal principal);

    void repeatOrder(long idOrder, Principal principal, CartDTO cartDTO);

    void returnProduct(Order order);


}
