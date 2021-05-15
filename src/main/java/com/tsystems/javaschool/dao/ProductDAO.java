package com.tsystems.javaschool.dao;

import com.tsystems.javaschool.entity.product.Product;

import java.util.List;

public interface ProductDAO {
    Product getById(int id);
    List<Product> allProducts(int idProductAbs);
    Product getProductByProductABSColourMainColourSecSize(int idProductAbs, int idColourMain, int idColourSec, String size);

    void update(Product product);

}
