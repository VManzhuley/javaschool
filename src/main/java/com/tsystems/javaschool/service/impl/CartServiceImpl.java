package com.tsystems.javaschool.service.impl;

import com.tsystems.javaschool.dao.CartDAO;
import com.tsystems.javaschool.dto.CartDTO;
import com.tsystems.javaschool.dto.CartItemDTO;
import com.tsystems.javaschool.entity.Cart;
import com.tsystems.javaschool.service.CartService;
import com.tsystems.javaschool.service.ClientService;
import com.tsystems.javaschool.service.ProductService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CartServiceImpl implements CartService {
    private final CartDAO cartDAO;
    private final ProductService productService;
    private final ClientService clientService;

    public CartServiceImpl(CartDAO cartDAO, ProductService productService, ClientService clientService) {
        this.cartDAO = cartDAO;

        this.productService = productService;
        this.clientService = clientService;
    }

    @Override
    public CartDTO findByClient(int id) {
        CartDTO cartDTO=new CartDTO();
        cartDTO.setClient(clientService.findById(id));
        List<CartItemDTO> list = new ArrayList<>();
        for (Cart cart : cartDAO.findByClient(id)) {
            CartItemDTO cartItemDTO = mapToCartItemDTO(cart);
            list.add(cartItemDTO);
        }
        cartDTO.setCartItems(list);
        return cartDTO;
    }

    @Override
    public CartItemDTO mapToCartItemDTO(Cart cart) {
        CartItemDTO cartItem = new CartItemDTO();
        cartItem.setQuantity(cart.getQuantity());
        cartItem.setProduct(productService.mapToProductDTO(cart.getProduct()));
        return cartItem;
    }
}
