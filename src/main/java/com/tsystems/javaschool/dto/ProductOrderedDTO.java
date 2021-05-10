package com.tsystems.javaschool.dto;

import lombok.Data;

@Data
public class ProductOrderedDTO {
    private ProductDTO product;
    private int quantity;
    private int amount;

    public int getPrice(){
        return amount/quantity;
    }

}
