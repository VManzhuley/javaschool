package com.tsystems.javaschool.dao;

import com.tsystems.javaschool.entity.product.Colour;
import com.tsystems.javaschool.entity.product.Composition;
import com.tsystems.javaschool.entity.product.Description;
import com.tsystems.javaschool.entity.product.Size;

import java.util.List;

public interface ParametersDAO {
    List<Composition> gelAllComposition();

    List<Description> getAllDescription();

    List<Size> getAllSize();

    List<Colour> getAllColour();

    Colour getColourByName(String name);

    Size getSizeByName(String name);

    void createComposition(Composition composition);

    void createDescription(Description description);

    List<Composition> getAllCompositionByCategory(long id);

    List<Description> getAllDescriptionByCategory(long id);
}
