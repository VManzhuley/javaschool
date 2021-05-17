package com.tsystems.javaschool.service;

import com.tsystems.javaschool.dto.ProductDTO;
import com.tsystems.javaschool.entity.product.Product;

public interface ProductService {

    ProductDTO mapToProductDTO(Product product);

    ProductDTO getProductByProductABSColourMainColourSecSize(int idProductAbs, int idColourMain, int idColourSec, String size);

    void add(ProductDTO productDTO, int idProductAbs);
}
