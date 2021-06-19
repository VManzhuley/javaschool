package com.tsystems.javaschool.service.impl;

import com.tsystems.javaschool.dao.ProductAbsDAO;
import com.tsystems.javaschool.dto.ColourDTO;
import com.tsystems.javaschool.dto.ProductAbsDTO;
import com.tsystems.javaschool.dto.ProductDTO;
import com.tsystems.javaschool.dto.SizeDTO;
import com.tsystems.javaschool.entity.product.Category;
import com.tsystems.javaschool.entity.product.Composition;
import com.tsystems.javaschool.entity.product.Description;
import com.tsystems.javaschool.entity.product.ProductAbs;
import com.tsystems.javaschool.service.ColourService;
import com.tsystems.javaschool.service.ProductAbsService;
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
public class ProductAbsServiceImpl implements ProductAbsService {

    private final ProductAbsDAO productAbsDAO;
    private final ProductService productService;
    private final SizeService sizeService;
    private final ColourService colourService;


    public ProductAbsServiceImpl(ProductAbsDAO productAbsDAO,
                                 ProductService productService,
                                 SizeService sizeService,
                                 ColourService colourService) {
        this.productAbsDAO = productAbsDAO;
        this.productService = productService;
        this.sizeService = sizeService;
        this.colourService = colourService;
    }

    @Override
    public ProductAbsDTO getProductAbsDTO(long id) {
        return addParams(mapToProductAbsDTO(productAbsDAO.getById(id)));
    }

    @Override
    public List<ProductAbsDTO> getAllByCategoryWithFSP(long idCategory, int page, String sort, long idComposition, long idDescription) {
        return productAbsDAO.getAllProductsByCategoryWithFSP(idCategory, page, sort, idComposition, idDescription).stream()
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
        productAbsDTO.setIdCategory(productAbs.getCategory().getId());
        productAbsDTO.setIdComposition(productAbs.getComposition().getId());
        productAbsDTO.setIdDescription(productAbs.getDescription().getId());

        productAbsDTO.setOutdated(productAbs.isOutdated());
        return productAbsDTO;
    }

    @Override
    public ProductAbsDTO addParams(ProductAbsDTO productAbsDTO) {
        productAbsDTO.setSizes(sizeService.getAllByProductAbs(productAbsDTO.getId()));
        productAbsDTO.setColours(colourService.getAllByProductAbs(productAbsDTO.getId()));
        return productAbsDTO;
    }

    @Override
    public ProductAbsDTO addProducts(ProductAbsDTO productAbsDTO) {
        for (ColourDTO colour : productAbsDTO.getColours()
        ) {
            for (SizeDTO size : productAbsDTO.getSizes()
            ) {
                ProductDTO productDTO = productService.getProductByProductABSColourMainColourSecSize(
                        productAbsDTO.getId(), colour.getIdColourMain(), colour.getIdColourSec(), size.getIdSize());
                productAbsDTO.addProduct(productDTO);
            }
        }

        return productAbsDTO;
    }

    @Override
    public int getTotalPages() {
        return productAbsDAO.getTotalPages();
    }

    @Override
    public long createOrUpdate(ProductAbsDTO productAbsDTO) {
        ProductAbs productAbs = new ProductAbs();
        productAbs.setOutdated(true);

        if (productAbsDTO.getId() != 0) {
            productAbs = productAbsDAO.getById(productAbsDTO.getId());
        }

        Description description = new Description();
        Composition composition = new Composition();
        Category category = new Category();

        description.setId(productAbsDTO.getIdDescription());
        composition.setId(productAbsDTO.getIdComposition());
        category.setId(productAbsDTO.getIdCategory());

        productAbs.setName(productAbsDTO.getName());
        productAbs.setArticle(productAbsDTO.getArticle());
        productAbs.setPrice(productAbsDTO.getPrice());
        productAbs.setPhoto(productAbsDTO.getPhotoLink());
        productAbs.setComposition(composition);
        productAbs.setDescription(description);
        productAbs.setCategory(category);


        if (productAbsDTO.getId() == 0) {
            productAbsDAO.create(productAbs);
            log.info("Abstract product: {} added to base", productAbsDTO.getName());
        } else {
            productAbsDAO.update(productAbs);
            log.info("Abstract product: {} updated in base", productAbsDTO.getName());
        }
        long idProductAbs = productAbs.getId();

        for (SizeDTO sizeDTO : productAbsDTO.getSizes()) {
            if (sizeDTO.getIdWV() == 0) {
                sizeService.createWeightVolume(sizeDTO, idProductAbs);
            }
        }

        for (ColourDTO colourDTO : productAbsDTO.getColours()) {
            if (colourDTO.getIdPhoto() == 0) {
                colourService.createPhotoLink(colourDTO, idProductAbs);

                for (SizeDTO sizeDTO : productAbsDTO.getSizes()) {
                    ProductDTO productDTO = new ProductDTO();
                    productDTO.setSize(sizeDTO);
                    productDTO.setColour(colourDTO);

                    productService.create(productDTO, idProductAbs);
                }
            } else {

                for (SizeDTO sizeDTO : productAbsDTO.getSizes()) {
                    if (sizeDTO.getIdWV() == 0) {
                        ProductDTO productDTO = new ProductDTO();
                        productDTO.setSize(sizeDTO);
                        productDTO.setColour(colourDTO);

                        productService.create(productDTO, idProductAbs);
                    }
                }
            }

        }

        return productAbs.getId();
    }

    @Override
    public void updateProductsPhotoWV(ProductAbsDTO productAbsDTO) {
        for (SizeDTO sizeDTO : productAbsDTO.getSizes()) {
            sizeService.updateWeightVolume(sizeDTO);
        }

        for (ColourDTO colourDTO : productAbsDTO.getColours()) {
            colourService.updatePhoto(colourDTO);
        }

        for (ProductDTO productDTO : productAbsDTO.getProducts()
        ) {
            productService.update(productDTO);
        }
    }

    @Override
    public void inverseOutdated(ProductAbsDTO productAbsDTO) {
        ProductAbs productAbs = productAbsDAO.getById(productAbsDTO.getId());
        productAbs.setOutdated(!productAbs.isOutdated());

        productAbsDAO.update(productAbs);
        log.info("Outdated for {} changed to {}", productAbsDTO.getName(), productAbs.isOutdated());
    }

    @Override
    public List<ProductAbs> getAllByCategory(long id) {
        return productAbsDAO.getAllByCategory(id);
    }

}
