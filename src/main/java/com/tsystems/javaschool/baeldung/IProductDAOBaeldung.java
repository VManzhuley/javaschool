package com.tsystems.javaschool.baeldung;

import com.tsystems.javaschool.entity.product.ProductAbs;

import java.util.List;

public interface IProductDAOBaeldung {
    List<ProductAbs> searchProduct(List<SearchCriteria> params);

}
