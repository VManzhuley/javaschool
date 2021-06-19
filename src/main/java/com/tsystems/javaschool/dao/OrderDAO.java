package com.tsystems.javaschool.dao;

import com.tsystems.javaschool.entity.Order;
import com.tsystems.javaschool.entity.ProductOrdered;

import java.util.List;

public interface OrderDAO {
    void createOrder(Order order);

    void createProductOrdered(ProductOrdered productOrdered);

    List<Order> getAllByPage(int page);

    long getTotalPagesToAdmin();

    Order getById(long id);

    void update(Order order);

    List<Order> getAllByClientAndPage(long idClient, int page);

    long getTotalPagesToUser(long idClient);
}
