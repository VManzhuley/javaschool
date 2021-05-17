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
}
