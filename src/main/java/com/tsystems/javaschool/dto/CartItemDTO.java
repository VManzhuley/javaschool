package com.tsystems.javaschool.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CartItemDTO {
    private ProductDTO product;
    private long quantity;
    private long missQuantity;

    public double getAmount() {
        return this.product.getPrice() * (double) this.quantity;
    }
}
