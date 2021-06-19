package com.tsystems.javaschool.controller;

import com.tsystems.javaschool.dto.ProductDTO;
import com.tsystems.javaschool.service.ProductService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ApiController {
    private final ProductService productService;

    public ApiController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping(value = "/api/products/top10")
    public List<ProductDTO> topProducts(){
        return productService.topProducts();
    }
}
