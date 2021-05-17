package com.tsystems.javaschool.dto;

import lombok.Data;

@Data
public class ProductOrderedDTO {

    private ProductDTO product;
    private long quantity;
    private long amount;

    public long getPrice(){
        return amount/quantity;
    }

}
