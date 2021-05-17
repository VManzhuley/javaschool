package com.tsystems.javaschool.service.impl;

import com.tsystems.javaschool.dao.CategoryDAO;
import com.tsystems.javaschool.dao.ProductAbsDAO;
import com.tsystems.javaschool.dto.CategoryDTO;
import com.tsystems.javaschool.entity.product.Category;
import com.tsystems.javaschool.service.CategoryService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;


@Service
@Transactional
public class CategoryServiceImpl implements CategoryService {
    private final CategoryDAO categoryDAO;
    private final ProductAbsDAO productAbsDAO;


    public CategoryServiceImpl(CategoryDAO categoryDAO, ProductAbsDAO productAbsDAO) {
        this.categoryDAO = categoryDAO;
        this.productAbsDAO = productAbsDAO;
    }

    @Override
    public List<Category> findAll() {
        return categoryDAO.findAll();
    }

    @Override
    public List<CategoryDTO> findAllDTO() {
        return categoryDAO.findAll().stream().map(this::mapToCategoryDTO).collect(Collectors.toList());
    }

    @Override
    public CategoryDTO mapToCategoryDTO(Category category) {
        CategoryDTO categoryDTO = new CategoryDTO();
        categoryDTO.setIdCategory(category.getId());
        if (category.getCategoryParent() == null) {
            categoryDTO.setName(category.getName());
        } else {
            categoryDTO.setName(category.getCategoryParent().getName() + "/" + category.getName());
        }
        return categoryDTO;
    }

    @Override
    public void add(String name) {
        if (!name.isEmpty()) {
            Category category = new Category();
            category.setName(name);
            categoryDAO.add(category);
        }
    }

    /*return true if this category have child category*/
    @Override
    public boolean checkChild(int idCategory) {
        return !categoryDAO.getById(idCategory).getCategoriesChild().isEmpty();
    }

    @Override
    public List<CategoryDTO> getAllWithoutChild() {
        return categoryDAO.getAllWithoutChild().stream().map(this::mapToCategoryDTO).collect(Collectors.toList());
    }

    @Override
    public List<CategoryDTO> getAllWithoutParentAndProducts() {
        return categoryDAO.getAllWithoutParent().stream().
                filter(category -> productAbsDAO.allByCategory(category.getId()).isEmpty()).
                map(this::mapToCategoryDTO).collect(Collectors.toList());
    }

    @Override
    public void updateParent(int idCategory, int idCategoryParent) {
        if (idCategory != idCategoryParent) {
            Category category = categoryDAO.getById(idCategory);
            Category categoryParent = categoryDAO.getById(idCategoryParent);
            category.setCategoryParent(categoryParent);

            categoryDAO.update(category);
        }
    }

}
