package com.tsystems.javaschool.service;

import com.tsystems.javaschool.dto.ProductAbsDTO;
import com.tsystems.javaschool.entity.product.ProductAbs;

import java.util.List;

public interface ProductAbsService {

    ProductAbsDTO getProductAbsDTO(long id);

    List<ProductAbsDTO> getAllByCategoryWithFSP(long idCategory, int page, String sort, long idComposition, long idDescription);

    ProductAbsDTO mapToProductAbsDTO(ProductAbs productAbs);

    ProductAbsDTO addParams(ProductAbsDTO productAbsDTO);

    ProductAbsDTO addProducts(ProductAbsDTO productAbsDTO);

    int getTotalPages();

    long createOrUpdate(ProductAbsDTO productAbsDTO);

    void updateProductsPhotoWV(ProductAbsDTO productAbsDTO);

    void inverseOutdated(ProductAbsDTO productAbsDTO);

    List<ProductAbs> getAllByCategory(long id);


}


