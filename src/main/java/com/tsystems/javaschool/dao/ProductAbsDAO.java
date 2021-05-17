package com.tsystems.javaschool.dao;


import com.tsystems.javaschool.entity.product.ProductAbs;

import java.util.List;

public interface ProductAbsDAO {

    ProductAbs getById(int id);

    List<ProductAbs> allProductsByCategoryWithFSP(int idCategory, int page, String sort);

    int getTotalPages();

    void add(ProductAbs productAbs);

    List<ProductAbs> allByCategory(int idCategory);

    void update(ProductAbs productAbs);

}
