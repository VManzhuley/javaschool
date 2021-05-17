package com.tsystems.javaschool.service;

import com.tsystems.javaschool.entity.product.Colour;
import com.tsystems.javaschool.entity.product.Composition;
import com.tsystems.javaschool.entity.product.Description;
import com.tsystems.javaschool.entity.product.Size;

import java.util.List;

public interface ParametersService {
    List<Composition> gelAllComposition();
    List<Description> getAllDescription();
    List<Size> getAllSize();
    List<Colour> getAllColour();


}
