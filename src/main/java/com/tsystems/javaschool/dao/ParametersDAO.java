package com.tsystems.javaschool.dao;

import com.tsystems.javaschool.entity.product.*;

import java.util.List;

public interface ParametersDAO {
    List<Composition> gelAllComposition();
    List<Description> getAllDescription();
    List<Size> getAllSize();
    List<Colour> getAllColour();
    List<Category> getAllCategoryWithoutChild();
    Colour getColourById(int id);
    Size getSizeById(int id);
}
