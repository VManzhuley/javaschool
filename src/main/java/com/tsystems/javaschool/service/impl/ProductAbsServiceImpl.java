package com.tsystems.javaschool.service.impl;

import com.tsystems.javaschool.dao.ProductAbsDAO;
import com.tsystems.javaschool.dto.ProductAbsDTO;
import com.tsystems.javaschool.dto.ProductDTO;
import com.tsystems.javaschool.entity.product.ProductAbs;
import com.tsystems.javaschool.model.ProductAbsInfo;
import com.tsystems.javaschool.service.ProductAbsService;
import com.tsystems.javaschool.service.ProductService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;


@Service
@Transactional
public class ProductAbsServiceImpl implements ProductAbsService {
    private final ProductAbsDAO productAbsDAO;
    private final ProductService productService;

    public ProductAbsServiceImpl(ProductAbsDAO productAbsDAO, ProductService productService) {
        this.productAbsDAO = productAbsDAO;

        this.productService = productService;
    }

    @Override
    public List<ProductAbs> allProductAbs() {
        return productAbsDAO.allProductAbs();
    }

    @Override
    public ProductAbsInfo getProductAbsInfo(int id) {
        return new ProductAbsInfo(id, productAbsDAO.getProductAbs(id).getName(), productAbsDAO.allSizes(id),
                /*colourService.productColors(id)*/null, productAbsDAO.allProducts(id));
    }

    @Override
    public List<ProductAbsDTO> allProductsByCategoryWithFSP(int idCategory, int page, String sort) {
        return productAbsDAO.allProductsByCategoryWithFSP(idCategory,page,sort).stream()
                .map(this::mapToProductAbsDTO)
                .collect(Collectors.toList());
    }

    @Override
    public ProductAbsDTO mapToProductAbsDTO(ProductAbs productAbs) {
        ProductAbsDTO productAbsDTO = new ProductAbsDTO();
        productAbsDTO.setId(productAbs.getId());
        productAbsDTO.setArticle(productAbs.getArticle());
        productAbsDTO.setName(productAbs.getName());
        productAbsDTO.setPrice(productAbs.getPrice());
        productAbsDTO.setPhotoLink(productAbs.getPhoto());
        productAbsDTO.setDescription(productAbs.getDescription().getName());
        productAbsDTO.setComposition(productAbs.getComposition().getName());
        productAbsDTO.setSizes(productService.allProducts(productAbs.getId())
                .stream().map(ProductDTO::getSize).collect(Collectors.toSet()));
        productAbsDTO.setColours(productService.allProducts(productAbs.getId())
                .stream().map(ProductDTO::getColour).collect(Collectors.toSet()));
        productAbsDTO.setPhotoLinks(productService.allProducts(productAbs.getId())
                .stream().map(ProductDTO::getPhotoLink).collect(Collectors.toSet()));
        return productAbsDTO;
    }
}
