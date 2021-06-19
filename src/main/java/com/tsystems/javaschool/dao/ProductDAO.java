package com.tsystems.javaschool.dao;

import com.tsystems.javaschool.entity.product.Product;

public interface ProductDAO {
    Product getById(long id);

    Product getProductByProductABSColourMainColourSecSize(long idProductAbs, long idColourMain, long idColourSec, long idSize);

    void update(Product product);

    void create(Product product);


}
