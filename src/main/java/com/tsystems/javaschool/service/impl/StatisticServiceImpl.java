package com.tsystems.javaschool.service.impl;

import com.tsystems.javaschool.dao.StatisticDAO;
import com.tsystems.javaschool.dto.OrderDTO;
import com.tsystems.javaschool.dto.ProductDTO;
import com.tsystems.javaschool.dto.ProductOrderedDTO;
import com.tsystems.javaschool.entity.Order;
import com.tsystems.javaschool.entity.product.Product;
import com.tsystems.javaschool.service.OrderService;
import com.tsystems.javaschool.service.ProductService;
import com.tsystems.javaschool.service.StatisticService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class StatisticServiceImpl implements StatisticService {
    private final StatisticDAO statisticDAO;
    private final OrderService orderService;
    private final ProductService productService;
    private double proceeds;
    private long productCount;

    public StatisticServiceImpl(StatisticDAO statisticDAO, OrderService orderService, ProductService productService) {
        this.statisticDAO = statisticDAO;
        this.orderService = orderService;
        this.productService = productService;
    }

    @Override
    public List<ProductOrderedDTO> topProductOrdered(String status, String sort, int pageSize) {
        return statisticDAO.topProductOrdered(status, sort, pageSize).stream().map(orderService::mapToProductOrderedDTO).collect(Collectors.toList());
    }

    @Override
    public List<OrderDTO> topClient(String status, String sort, int pageSize) {
        return statisticDAO.topClient(status, sort, pageSize).stream().map(orderService::mapToOrderDTO).collect(Collectors.toList());
    }

    @Override
    public List<OrderDTO> ordersCompletedByDate(String from, String to) {
        List<OrderDTO> list = new ArrayList<>();
        proceeds=0;
        for (Order order : statisticDAO.ordersCompletedByDate(from, to)) {
            OrderDTO orderDTO = orderService.mapToOrderDTO(order);
            list.add(orderDTO);
            proceeds+=order.getAmount();
        }
        return list;
    }

    @Override
    public double getProceeds() {
        return proceeds;
    }

    @Override
    public List<ProductDTO> topProduct(String sort, int pageSize) {
        List<ProductDTO> list = new ArrayList<>();
        productCount=0;
        for (Product product : statisticDAO.topProduct(sort, pageSize)) {
            ProductDTO productDTO = productService.mapToProductDTO(product);
            list.add(productDTO);
            productCount+=product.getQuantity();
        }
        return list;
    }

    @Override
    public double getProductCount() {
        return productCount;
    }
}
