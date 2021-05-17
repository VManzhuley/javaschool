package com.tsystems.javaschool.service;

import com.tsystems.javaschool.dto.OrderDTO;
import com.tsystems.javaschool.dto.ProductDTO;
import com.tsystems.javaschool.dto.ProductOrderedDTO;

import java.util.List;

public interface StatisticService {
    List<ProductOrderedDTO> topProductOrdered(String status, String sort, int pageSize);
    List<OrderDTO> topClient(String status, String sort, int pageSize);
    List<OrderDTO> ordersCompletedByDate(String from, String to);
    double getProceeds();
    List<ProductDTO> topProduct(String sort, int pageSize);
    double getProductCount();

}
