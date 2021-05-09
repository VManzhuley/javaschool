package com.tsystems.javaschool.dao;

import com.tsystems.javaschool.entity.Order;
import com.tsystems.javaschool.entity.ProductOrdered;

public interface OrderDAO {
    void addOrder(Order order);
    void addProductOrdered(ProductOrdered productOrdered);
}
