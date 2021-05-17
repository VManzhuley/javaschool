package com.tsystems.javaschool.service.impl;

import com.tsystems.javaschool.dao.ParametersDAO;
import com.tsystems.javaschool.dao.ProductDAO;
import com.tsystems.javaschool.dto.ProductDTO;
import com.tsystems.javaschool.entity.product.Colour;
import com.tsystems.javaschool.entity.product.Product;
import com.tsystems.javaschool.entity.product.ProductAbs;
import com.tsystems.javaschool.entity.product.Size;
import com.tsystems.javaschool.service.ColourService;
import com.tsystems.javaschool.service.ProductService;
import com.tsystems.javaschool.service.SizeService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class ProductServiceImpl implements ProductService {
    private final ProductDAO productDAO;
    private final ColourService colourService;
    private final SizeService sizeService;
    private final ParametersDAO parametersDAO;


    public ProductServiceImpl(ProductDAO productDAO, ColourService colourService, SizeService sizeService, ParametersDAO parametersDAO) {
        this.productDAO = productDAO;
        this.colourService = colourService;
        this.sizeService = sizeService;
        this.parametersDAO = parametersDAO;
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
    public ProductDTO getProductByProductABSColourMainColourSecSize(int idProductAbs, int idColourMain, int idColourSec, String size) {
        return mapToProductDTO(productDAO.getProductByProductABSColourMainColourSecSize(idProductAbs, idColourMain, idColourSec, size));
    }

    @Override
    public void add(ProductDTO productDTO, int idProductAbs) {
        Product product = new Product();
        ProductAbs productAbs = new ProductAbs();
        Size size = parametersDAO.getSizeByName(productDTO.getSize().getSize());
        Colour colourMain = parametersDAO.getColourByName(productDTO.getColour().getColourMain());

        if (!productDTO.getColour().getColourSec().isEmpty()) {
            Colour colourSec = parametersDAO.getColourByName(productDTO.getColour().getColourSec());
            product.setColourSec(colourSec);
        }

        productAbs.setId(idProductAbs);
        product.setProductAbs(productAbs);
        product.setSize(size);
        product.setColourMain(colourMain);
        product.setQuantity(productDTO.getQuantity());

        productDAO.add(product);

    }

}
