package com.tsystems.javaschool.service;

import com.tsystems.javaschool.dto.ProductDTO;
import com.tsystems.javaschool.entity.product.Product;

import java.util.List;

public interface ProductService {

    ProductDTO mapToProductDTO(Product product);

    ProductDTO getProductByProductABSColourMainColourSecSize(long idProductAbs, long idColourMain, long idColourSec, long idSize);

    void create(ProductDTO productDTO, long idProductAbs);

    Product mapToProduct(ProductDTO productDTO);

    void update(ProductDTO productDTO);

    List<ProductDTO> topProducts();
}
