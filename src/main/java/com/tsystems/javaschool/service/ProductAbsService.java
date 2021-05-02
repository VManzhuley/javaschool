package com.tsystems.javaschool.service;

import com.tsystems.javaschool.dto.ProductAbsDTO;
import com.tsystems.javaschool.entity.product.ProductAbs;
import com.tsystems.javaschool.model.ProductAbsInfo;

import java.util.List;

public interface ProductAbsService {
    List<ProductAbs> allProductAbs();
    ProductAbsInfo getProductAbsInfo(int id);
    List<ProductAbsDTO> allProductsByCategoryWithFSP(int idCategory, int page, String sort);


    ProductAbsDTO mapToProductAbsDTO(ProductAbs productAbs);


}


