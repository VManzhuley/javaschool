package com.tsystems.javaschool.dao;


import com.tsystems.javaschool.entity.product.Product;
import com.tsystems.javaschool.entity.product.ProductAbs;
import com.tsystems.javaschool.entity.product.Size;

import java.util.List;

public interface ProductAbsDAO {
    List<ProductAbs> allProductAbs();
    ProductAbs getProductAbs(int id);
    List<Size> allSizes(int idProductAbs);
    List<Product> allProducts(int idProductAbs);
    List<ProductAbs> allProductsByCategory(int idCategory);
    List<ProductAbs> allProductsByCategoryWithFSP(int idCategory, int page, String sort);

}
