package com.tsystems.javaschool.dto;

import com.tsystems.javaschool.entity.product.ProductAbs;
import lombok.Data;

@Data
public class ProductDTO {
    private int id;
    private ProductAbs productAbs;
    private String name;
    private String article;
    private SizeDTO size;
    private ColourDTO colour;
    private int price;

}
