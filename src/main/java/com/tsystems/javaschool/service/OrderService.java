package com.tsystems.javaschool.service;

import com.tsystems.javaschool.dto.CartDTO;
import com.tsystems.javaschool.dto.ClientDTO;
import com.tsystems.javaschool.dto.OrderDTO;


public interface OrderService {
    int addOrder(CartDTO cart, ClientDTO client, OrderDTO order);
}
