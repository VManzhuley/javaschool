package com.tsystems.javaschool.service;

import com.tsystems.javaschool.dto.CartDTO;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;

public interface CartService {

    void mergeCart(CartDTO cartDTO, Principal principal);

    void addCartItem(CartDTO cartDTO, int idProductAbs, HttpServletRequest request, Principal principal);

    void updateCartItem(CartDTO cartDTO, HttpServletRequest request, Principal principal);

    void removeCartItem(CartDTO cartDTO, int idProduct, Principal principal);

    void removeAll(CartDTO cartDTO, Principal principal);

    void checkAvailability(CartDTO cartDTO);


}
