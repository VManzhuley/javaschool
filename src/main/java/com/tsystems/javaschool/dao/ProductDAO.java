package com.tsystems.javaschool.dao;

import com.tsystems.javaschool.entity.product.Product;

public interface ProductDAO {
    Product getById(int id);

    Product getProductByProductABSColourMainColourSecSize(int idProductAbs, int idColourMain, int idColourSec, String size);

    void update(Product product);

    void add(Product product);

}
