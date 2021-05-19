package com.tsystems.javaschool.service;

import com.tsystems.javaschool.dto.CategoryDTO;
import com.tsystems.javaschool.entity.product.Category;

import java.util.List;

public interface CategoryService {

    List<Category> findAll();

    List<CategoryDTO> findAllDTO();

    CategoryDTO mapToCategoryDTO(Category category);

    void add(String name);

    boolean checkChild(int idCategory);

    List<CategoryDTO> getAllWithoutChild();

    List<CategoryDTO> getAllWithoutParentAndProducts();

    void updateParent(int idCategory, int idCategoryParent);
}
