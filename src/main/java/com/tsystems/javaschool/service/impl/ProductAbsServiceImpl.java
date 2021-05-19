package com.tsystems.javaschool.service.impl;

import com.tsystems.javaschool.dao.CategoryDAO;
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
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;


@Service
@Transactional
public class ProductAbsServiceImpl implements ProductAbsService {
    private final ProductAbsDAO productAbsDAO;
    private final ProductService productService;
    private final SizeService sizeService;
    private final ColourService colourService;
    private final CategoryDAO categoryDAO;

    public ProductAbsServiceImpl(ProductAbsDAO productAbsDAO,
                                 ProductService productService,
                                 SizeService sizeService,
                                 ColourService colourService,
                                 CategoryDAO categoryDAO) {
        this.productAbsDAO = productAbsDAO;
        this.productService = productService;
        this.sizeService = sizeService;
        this.colourService = colourService;
        this.categoryDAO = categoryDAO;
    }


    @Override
    public ProductAbsDTO getProductAbsDTO(int id) {
        return addParams(mapToProductAbsDTO(productAbsDAO.getById(id)));
    }

    @Override
    public List<ProductAbsDTO> allProductsByCategoryWithFSP(int idCategory, int page, String sort) {
        return productAbsDAO.allProductsByCategoryWithFSP(idCategory, page, sort).stream()
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
        return productAbsDTO;
    }

    @Override
    public ProductAbsDTO addParams(ProductAbsDTO productAbsDTO) {
        productAbsDTO.setSizes(sizeService.allByProductAbs(productAbsDTO.getId()));
        productAbsDTO.setColours(colourService.allByProductAbs(productAbsDTO.getId()));
        return productAbsDTO;
    }

    @Override
    public int getTotalPages() {
        return productAbsDAO.getTotalPages();
    }

    @Override
    public int add(ProductAbsDTO productAbsDTO) {
        ProductAbs productAbs = new ProductAbs();
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
        productAbs.setOutdated(false);

        productAbsDAO.add(productAbs);

        int idProductAbs = productAbs.getId();

        for (SizeDTO sizeDTO : productAbsDTO.getSizes()) {
            sizeService.addWeightVolume(sizeDTO, idProductAbs);
        }

        for (ColourDTO colourDTO : productAbsDTO.getColours()) {
            colourService.addPhotoLink(colourDTO, idProductAbs);
        }

        for (ProductDTO productDTO : productAbsDTO.getProducts()) {
            productService.add(productDTO, idProductAbs);
        }
        productAbsDTO.clearSizesAndColours();
        return productAbs.getId();
    }

    @Override
    public List<ProductAbsDTO> allByCategory(int idCategory) {
        return productAbsDAO.allByCategory(idCategory).stream().map(this::mapToProductAbsDTO).collect(Collectors.toList());
    }

    @Override
    public void updateCategory(int idProductAbs, int idCategory) {
        ProductAbs productAbs = productAbsDAO.getById(idProductAbs);
        Category category = categoryDAO.getById(idCategory);
        productAbs.setCategory(category);
        productAbsDAO.update(productAbs);
    }
}
