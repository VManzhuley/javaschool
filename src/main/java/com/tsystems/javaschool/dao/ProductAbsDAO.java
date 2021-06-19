package com.tsystems.javaschool.dao;


import com.tsystems.javaschool.entity.product.ProductAbs;

import java.util.List;

public interface ProductAbsDAO {
    ProductAbs getById(long id);

    List<ProductAbs> getAllProductsByCategoryWithFSP(long idCategory, int page, String sort, long idComposition, long idDescription);

    int getTotalPages();

    void create(ProductAbs productAbs);

    List<ProductAbs> getAllByCategory(long idCategory);

    void update(ProductAbs productAbs);

}
