package com.tsystems.javaschool.dto;

import com.tsystems.javaschool.entity.Client;
import lombok.Data;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@Data
public class CartDTO {

    private Client client;
    private List<CartItemDTO> cartItems = new ArrayList<>();

    private CartItemDTO findItemByIdProduct(int idProduct) {
        for (CartItemDTO item : this.cartItems
        ) {
            if (item.getProduct().getId() == idProduct) return item;
        }
        return null;
    }

    public void addCartItem(ProductDTO product, int quantity) {
        CartItemDTO item = this.findItemByIdProduct(product.getId());

        if (item == null) {
            item = new CartItemDTO();
            item.setProduct(product);
            item.setQuantity(quantity);
            this.cartItems.add(item);
        } else {
            item.setQuantity(item.getQuantity() + quantity);
        }

    }

    public int getAmountTotal() {
        int total = 0;
        for (CartItemDTO item : this.cartItems) {
            total += item.getAmount();
        }
        return total;
    }

}
