package com.tsystems.javaschool.model;

import com.tsystems.javaschool.dto.ColourDTO;
import com.tsystems.javaschool.entity.product.ProductAbs;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Product {
    private int id;
    private ProductAbs productAbs;
    private String article;
    private String name;
    private ColourDTO productColour;
    private String size;
    private String photoLink;
}
