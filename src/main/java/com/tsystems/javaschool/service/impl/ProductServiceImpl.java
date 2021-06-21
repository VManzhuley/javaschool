package com.tsystems.javaschool.service.impl;

import com.tsystems.javaschool.dao.ParametersDAO;
import com.tsystems.javaschool.dao.ProductDAO;
import com.tsystems.javaschool.dao.StatisticDAO;
import com.tsystems.javaschool.dto.ProductDTO;
import com.tsystems.javaschool.entity.ProductOrdered;
import com.tsystems.javaschool.entity.product.Colour;
import com.tsystems.javaschool.entity.product.Product;
import com.tsystems.javaschool.entity.product.ProductAbs;
import com.tsystems.javaschool.entity.product.Size;
import com.tsystems.javaschool.service.ColourService;
import com.tsystems.javaschool.service.ProductService;
import com.tsystems.javaschool.service.SizeService;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@Log4j2
public class ProductServiceImpl implements ProductService {
    private final ProductDAO productDAO;
    private final ColourService colourService;
    private final SizeService sizeService;
    private final ParametersDAO parametersDAO;
    private final StatisticDAO statisticDAO;


    public ProductServiceImpl(ProductDAO productDAO, ColourService colourService, SizeService sizeService, ParametersDAO parametersDAO, StatisticDAO statisticDAO) {
        this.productDAO = productDAO;
        this.colourService = colourService;
        this.sizeService = sizeService;
        this.parametersDAO = parametersDAO;
        this.statisticDAO = statisticDAO;
    }


    @Override
    public ProductDTO mapToProductDTO(Product product) {
        ProductDTO productDTO = new ProductDTO();
        productDTO.setId(product.getId());
        productDTO.setProductAbsName(product.getProductAbs().getName());
        productDTO.setSize(sizeService.getSize(product.getId()));
        productDTO.setColour(colourService.getColourByIdProduct(product.getId()));

        productDTO.setArticle(String.join(" ", product.getProductAbs().getArticle(),
                colourService.getColourByIdProduct(product.getId()).getArticle(),
                productDTO.getSize().getSize()));


        productDTO.setName(String.join(", ", product.getProductAbs().getName(),
                product.getProductAbs().getDescription().getName(),
                productDTO.getColour().getName(),
                productDTO.getSize().getSize()));

        productDTO.setOutdated(product.getProductAbs().isOutdated());
        productDTO.setPrice(product.getProductAbs().getPrice());
        productDTO.setQuantity(product.getQuantity());

        return productDTO;
    }


    @Override
    public ProductDTO getProductByProductABSColourMainColourSecSize(long idProductAbs,
                                                                    long idColourMain,
                                                                    long idColourSec,
                                                                    long idSize) {

        return mapToProductDTO(productDAO.getProductByProductABSColourMainColourSecSize(
                idProductAbs,
                idColourMain,
                idColourSec,
                idSize));
    }

    @Override
    public void create(ProductDTO productDTO, long idProductAbs) {

        ProductAbs productAbs = new ProductAbs();
        productAbs.setId(idProductAbs);

        Product product = mapToProduct(productDTO);
        product.setProductAbs(productAbs);

        log.info("Product for abstract product: {} with size: {} and colours: {}/{} added to base",
                idProductAbs, productDTO.getSize().getSize(),
                productDTO.getColour().getColourMain(), productDTO.getColour().getColourSec());
        productDAO.create(product);

    }

    @Override
    public Product mapToProduct(ProductDTO productDTO) {
        Product product = new Product();

        Size size = parametersDAO.getSizeByName(productDTO.getSize().getSize());
        Colour colourMain = parametersDAO.getColourByName(productDTO.getColour().getColourMain());

        if (!productDTO.getColour().getColourSec().isEmpty()) {
            Colour colourSec = parametersDAO.getColourByName(productDTO.getColour().getColourSec());
            product.setColourSec(colourSec);
        }

        product.setSize(size);
        product.setColourMain(colourMain);

        return product;
    }

    @Override
    public void updateQuantity(ProductDTO productDTO) {

        log.info("Quantity of product: {} will be updated by {}", productDTO.getName(), productDTO.getQuantity());
        productDAO.decreaseQuantity(productDTO.getId(), -productDTO.getQuantity());

    }

    @Override
    public List<ProductDTO> topProducts() {
        return statisticDAO.topProductOrdered("ALL", "quantity", 10).stream()
                .map(ProductOrdered::getProduct)
                .map(this::mapToProductDTO)
                .collect(Collectors.toList());
    }

}
