package com.tsystems.javaschool.dto;

import lombok.Data;

@Data
public class CartItemDTO {
    private ProductDTO product;
    private long quantity;
    private long missQuantity;

    public double getAmount() {
        return this.product.getPrice() * (double) this.quantity;
    }
}
