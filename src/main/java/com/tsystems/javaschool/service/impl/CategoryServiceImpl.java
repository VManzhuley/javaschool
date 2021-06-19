package com.tsystems.javaschool.service.impl;

import com.tsystems.javaschool.dao.CategoryDAO;
import com.tsystems.javaschool.dao.ProductAbsDAO;
import com.tsystems.javaschool.dto.CategoryDTO;
import com.tsystems.javaschool.entity.product.Category;
import com.tsystems.javaschool.service.CategoryService;
import com.tsystems.javaschool.service.ProductAbsService;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;


@Service
@Transactional
@Log4j2
public class CategoryServiceImpl implements CategoryService {

    private final CategoryDAO categoryDAO;
    private final ProductAbsDAO productAbsDAO;
    private final ProductAbsService productAbsService;


    public CategoryServiceImpl(CategoryDAO categoryDAO, ProductAbsDAO productAbsDAO, ProductAbsService productAbsService) {
        this.categoryDAO = categoryDAO;
        this.productAbsDAO = productAbsDAO;
        this.productAbsService = productAbsService;
    }


    @Override
    public CategoryDTO mapToCategoryDTO(Category category) {
        CategoryDTO categoryDTO = new CategoryDTO();
        categoryDTO.setIdCategory(category.getId());
        categoryDTO.setName(category.getName());
        categoryDTO.setFullName(category.getCategoryParent() == null ? category.getName() : category.getCategoryParent().getName() + "/" + category.getName());
        if (!category.getCategoriesChild().isEmpty()) {
            categoryDTO.setCategoriesChild(category.getCategoriesChild().stream().map(this::mapToCategoryDTO).collect(Collectors.toList()));
        }
        if (!category.getProductAbsList().isEmpty()) {
            categoryDTO.setProductsAbs(category.getProductAbsList().stream().map(productAbsService::mapToProductAbsDTO).collect(Collectors.toList()));
        }
        return categoryDTO;
    }

    @Override
    public void create(String name) {
        if (!name.isEmpty()) {
            Category category = new Category();
            category.setName(name);

            log.info("Category: {} added to base", name);
            categoryDAO.create(category);
        }
    }

    @Override
    public List<CategoryDTO> getAllWithoutChild() {
        return categoryDAO.getAllWithoutChild().stream().map(this::mapToCategoryDTO).collect(Collectors.toList());
    }

    @Override
    public List<CategoryDTO> getAllWithoutParentAndProducts() {
        return categoryDAO.getAllWithoutParent().stream().
                filter(category -> productAbsDAO.getAllByCategory(category.getId()).isEmpty()).
                map(this::mapToCategoryDTO).collect(Collectors.toList());
    }

    @Override
    public void updateParent(long idCategory, long idCategoryParent) {
        if (idCategory != idCategoryParent) {
            Category category = categoryDAO.getById(idCategory);
            Category categoryParent = categoryDAO.getById(idCategoryParent);
            category.setCategoryParent(categoryParent);

            log.info("Category: {} updated parent category: {}", category.getName(), categoryParent.getName());
            categoryDAO.update(category);
        }
    }

    @Override
    public List<CategoryDTO> getAllWithoutParent() {
        return categoryDAO.getAllWithoutParent().stream().map(this::mapToCategoryDTO).collect(Collectors.toList());
    }

}
