package com.tsystems.javaschool.service.impl;

import com.tsystems.javaschool.dao.ProductDAO;
import com.tsystems.javaschool.dto.ProductDTO;
import com.tsystems.javaschool.entity.product.Product;
import com.tsystems.javaschool.service.ColourService;
import com.tsystems.javaschool.service.ProductService;
import com.tsystems.javaschool.service.SizeService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class ProductServiceImpl implements ProductService {
    private final ProductDAO productDAO;
    private final ColourService colourService;
    private final SizeService sizeService;


    public ProductServiceImpl(ProductDAO productDAO, ColourService colourService, SizeService sizeService) {
        this.productDAO = productDAO;
        this.colourService = colourService;
        this.sizeService = sizeService;
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

        productDTO.setSize(sizeService.getSize(product.getId()));
        productDTO.setColour(colourService.getColourByIdProduct(product.getId()));

        productDTO.setArticle(String.join(" ", product.getProductAbs().getArticle(),
                colourService.getColourByIdProduct(product.getId()).getArticle(),
                productDTO.getSize().getSize()));


        productDTO.setName(String.join(", ", product.getProductAbs().getName(),
                product.getProductAbs().getDescription().getName(),
                productDTO.getColour().getName(),
                productDTO.getSize().getSize()));

        productDTO.setProductAbs(product.getProductAbs());
        productDTO.setPrice(product.getProductAbs().getPrice());
        productDTO.setQuantity(product.getQuantity());

        return productDTO;
    }

    @Override
    public Product mapToProduct(ProductDTO productDTO) {
        Product product = new Product();
        product.setId(productDTO.getId());


        return product;
    }

    @Override
    public ProductDTO getProductByProductABSColourMainColourSecSize(int idProductAbs, int idColourMain, int idColourSec, String size) {
        return mapToProductDTO(productDAO.getProductByProductABSColourMainColourSecSize(idProductAbs, idColourMain, idColourSec, size));
    }

}
