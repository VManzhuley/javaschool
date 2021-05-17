package com.tsystems.javaschool.dao;

import com.tsystems.javaschool.entity.Order;
import com.tsystems.javaschool.entity.ProductOrdered;
import com.tsystems.javaschool.entity.product.Product;

import java.util.List;

public interface StatisticDAO {
    List<ProductOrdered> topProductOrdered(String status, String sort, int pageSize);

    List<Order> topClient(String status, String sort, int pageSize);

    List<Order> ordersCompletedByDate(String from, String to);

    List<Product> topProduct(String sort, int pageSize);

}

