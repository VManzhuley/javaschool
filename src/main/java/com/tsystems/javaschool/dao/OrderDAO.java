package com.tsystems.javaschool.dao;

import com.tsystems.javaschool.entity.Order;
import com.tsystems.javaschool.entity.ProductOrdered;

import java.util.List;

public interface OrderDAO {
    void addOrder(Order order);
    void addProductOrdered(ProductOrdered productOrdered);
    List<Order> allByPage(int page);
    long getTotalPagesToAdmin();
    long getAmount(int idOrder);
    Order getById(int id);
    void update(Order order);
    List<Order> allByClientAndPage(String email, int page);
    long getTotalPagesToUser(String email);
}
