package com.tsystems.javaschool.service;

import com.tsystems.javaschool.dto.CategoryDTO;
import com.tsystems.javaschool.entity.product.Category;

import java.util.List;

public interface CategoryService {

    CategoryDTO mapToCategoryDTO(Category category);

    void create(String name);

    List<CategoryDTO> getAllWithoutChild();

    List<CategoryDTO> getAllWithoutParentAndProducts();

    void updateParent(long idCategory, long idCategoryParent);

    List<CategoryDTO> getAllWithoutParent();
}
