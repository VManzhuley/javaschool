package com.tsystems.javaschool.service;

import com.tsystems.javaschool.dto.ProductDTO;
import com.tsystems.javaschool.entity.product.Product;

import java.util.List;

public interface ProductService {

    List<ProductDTO> allProducts(int idProductAbs);

    ProductDTO mapToProductDTO(Product product);
}