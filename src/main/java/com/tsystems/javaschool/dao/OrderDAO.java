package com.tsystems.javaschool.dao;

import com.tsystems.javaschool.entity.Order;
import com.tsystems.javaschool.entity.ProductOrdered;

import java.util.List;

public interface OrderDAO {
    void addOrder(Order order);
    void addProductOrdered(ProductOrdered productOrdered);
    List<Order> allByPage(int page);
    long getTotalPagesToAdmin();
    Order getById(long id);
    void update(Order order);
    List<Order> allByClientAndPage(int idClient, int page);
    long getTotalPagesToUser(int idClient);
}
