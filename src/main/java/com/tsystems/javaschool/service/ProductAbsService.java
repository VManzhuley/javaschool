package com.tsystems.javaschool.service;

import com.tsystems.javaschool.dto.ProductAbsDTO;
import com.tsystems.javaschool.entity.product.ProductAbs;

import java.util.List;

public interface ProductAbsService {

    ProductAbsDTO getProductAbsDTO(int id);

    List<ProductAbsDTO> allProductsByCategoryWithFSP(int idCategory, int page, String sort);

    ProductAbsDTO mapToProductAbsDTO(ProductAbs productAbs);

    ProductAbsDTO addParams(ProductAbsDTO productAbsDTO);

    int getTotalPages();

    int add(ProductAbsDTO productAbsDTO);

    List<ProductAbsDTO> allByCategory(int idCategory);

    void updateCategory(int idProductAbs, int idCategory);


}


