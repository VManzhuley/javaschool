package com.tsystems.javaschool.dao;


import com.tsystems.javaschool.entity.product.ProductAbs;

import java.util.List;

public interface ProductAbsDAO {

    ProductAbs getProductAbs(int id);

    List<ProductAbs> allProductsByCategoryWithFSP(int idCategory, int page, String sort);

    int getTotalPages();

}
