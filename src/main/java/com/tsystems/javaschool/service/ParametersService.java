package com.tsystems.javaschool.service;

import com.tsystems.javaschool.dto.CategoryDTO;
import com.tsystems.javaschool.entity.product.*;

import java.util.List;

public interface ParametersService {
    List<Composition> gelAllComposition();
    List<Description> getAllDescription();
    List<Size> getAllSize();
    List<Colour> getAllColour();
    List<CategoryDTO> getAllCategoryWithoutChild();

    CategoryDTO mapToCategoryDTO(Category category);
    Colour getColourById(int id);
    Size getSizeById(int id);
}
