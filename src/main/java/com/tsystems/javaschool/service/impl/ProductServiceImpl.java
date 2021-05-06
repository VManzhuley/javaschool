package com.tsystems.javaschool.service.impl;

import com.tsystems.javaschool.dao.PhotoDAO;
import com.tsystems.javaschool.dao.ProductDAO;
import com.tsystems.javaschool.dto.ProductDTO;
import com.tsystems.javaschool.entity.product.Product;
import com.tsystems.javaschool.service.ColourService;
import com.tsystems.javaschool.service.ProductService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class ProductServiceImpl implements ProductService {
    private final ProductDAO productDAO;
    private final ColourService colourService;
    private final PhotoDAO photoDAO;

    public ProductServiceImpl(ProductDAO productDAO, ColourService colourService, PhotoDAO photoDAO) {
        this.productDAO = productDAO;
        this.colourService = colourService;
        this.photoDAO = photoDAO;
    }

    @Override
    public List<ProductDTO> allProducts(int idProductAbs) {
        return productDAO.allProducts(idProductAbs).stream()
                .map(this::mapToProductDTO)
                .collect(Collectors.toList());
    }

    @Override
    public ProductDTO mapToProductDTO(Product product) {
        ProductDTO productDTO = new ProductDTO();
        productDTO.setId(product.getId());

        productDTO.setSize(product.getSize().getName());
        productDTO.setColour(colourService.getColour(product.getId()));


        productDTO.setArticle(product.getProductAbs().getArticle() + " " +
                colourService.getColour(product.getId()).getArticle() + " " +
                productDTO.getSize());

        productDTO.setName(product.getProductAbs().getName() + ", " +
                product.getProductAbs().getDescription().getName() + ", " +
                productDTO.getColour().getName() + ", " +
                productDTO.getSize());
        productDTO.setProductAbs(product.getProductAbs());
        productDTO.setPrice(product.getProductAbs().getPrice());

        return productDTO;
    }

    @Override
    public ProductDTO getProductByProductABSColourMainColourSecSize(int idProductAbs, int idColourMain, int idColourSec, String size) {
        return mapToProductDTO(productDAO.getProductByProductABSColourMainColourSecSize(idProductAbs, idColourMain, idColourSec, size));
    }

}
