package com.tsystems.javaschool.dto;

import com.tsystems.javaschool.entity.product.ProductAbs;
import lombok.Data;
import org.springframework.stereotype.Component;

@Data
@Component
public class ProductDTO {
    private int id;
    private ProductAbs productAbs;
    private String name;
    private String article;
    private String size;
    private ColourDTO colour;
    private int price;
}
