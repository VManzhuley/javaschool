package com.tsystems.javaschool.dto;


import lombok.Data;

import java.util.ArrayList;
import java.util.List;


@Data
public class CartDTO {

    private ClientDTO client;
    private List<CartItemDTO> cartItems = new ArrayList<>();

    public CartItemDTO findItemByIdProduct(int idProduct) {
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

    public void deleteCartItem(int idProduct) {
        CartItemDTO item = this.findItemByIdProduct(idProduct);

        if (item != null) {
            this.cartItems.remove(item);
        }
    }

    public void updateCartItem(int idProduct, int quantity) {
        CartItemDTO item = this.findItemByIdProduct(idProduct);

        if (item !=null){
            if (quantity<=0){
                this.cartItems.remove(item);
            } else {
                item.setQuantity(quantity);
            }
        }
    }


    public int getAmountTotal() {
        int total = 0;
        for (CartItemDTO item : this.cartItems) {
            total += item.getAmount();
        }
        return total;
    }

    public void deleteAll(){
        cartItems = new ArrayList<>();
    }


}
