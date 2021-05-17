package com.tsystems.javaschool.service.impl;

import com.tsystems.javaschool.dao.CartDAO;
import com.tsystems.javaschool.dao.ClientDAO;
import com.tsystems.javaschool.dao.ProductDAO;
import com.tsystems.javaschool.dto.CartDTO;
import com.tsystems.javaschool.dto.CartItemDTO;
import com.tsystems.javaschool.dto.ProductDTO;
import com.tsystems.javaschool.entity.Cart;
import com.tsystems.javaschool.entity.product.Product;
import com.tsystems.javaschool.service.CartService;
import com.tsystems.javaschool.service.ClientService;
import com.tsystems.javaschool.service.ProductService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;
import java.util.ArrayList;

@Service
@Transactional
public class CartServiceImpl implements CartService {
    private final CartDAO cartDAO;
    private final ProductService productService;
    private final ClientService clientService;
    private final ProductDAO productDAO;
    private final ClientDAO clientDAO;

    public CartServiceImpl(CartDAO cartDAO, ProductService productService, ClientService clientService, ProductDAO productDAO, ClientDAO clientDAO) {
        this.cartDAO = cartDAO;
        this.productService = productService;
        this.clientService = clientService;
        this.productDAO = productDAO;
        this.clientDAO = clientDAO;
    }

    @Override
    public void mergeCart(CartDTO cartDTO, Principal principal) {

        cartDTO.setUserName(principal.getName());

        for (Cart cart : cartDAO.findByClient(principal.getName())
        ) {
            addCartItem(cartDTO, productService.mapToProductDTO(cart.getProduct()), cart.getQuantity());
        }

        cartDAO.removeAll(clientDAO.findByUserName(principal.getName()).getId());

        for (CartItemDTO item : cartDTO.getCartItems()
        ) {
            Cart cart = new Cart();
            cart.setQuantity(item.getQuantity());
            cart.setClient(clientDAO.findByUserName(cartDTO.getUserName()));

            Product product = new Product();
            product.setId(item.getProduct().getId());
            cart.setProduct(product);

            cartDAO.add(cart);
        }


    }

    @Override
    public void addCartItem(CartDTO cartDTO, int idProductAbs, HttpServletRequest request, Principal principal) {
        String size = request.getParameter("size");
        String colourMain = request.getParameter("colourMain");
        String colourSec = request.getParameter("colourSec");
        String quantity = request.getParameter("quantity");

        if (!(colourMain.isEmpty() || size.isEmpty() || quantity.isEmpty())) {

            ProductDTO productDTO = productService.getProductByProductABSColourMainColourSecSize(
                    idProductAbs,
                    Integer.parseInt(colourMain),
                    Integer.parseInt(colourSec),
                    size);

            addCartItem(cartDTO, productDTO, Integer.parseInt(quantity));
        }
    }

    private void addCartItem(CartDTO cartDTO, ProductDTO productDTO, long quantity) {
        CartItemDTO item = cartDTO.findItemByIdProduct(productDTO.getId());

        if (item == null) {
            item = new CartItemDTO();
            item.setProduct(productDTO);
            item.setQuantity(quantity);

            cartDTO.addCartItem(item);

            if (cartDTO.getUserName() != null) {
                Cart cart = new Cart();
                cart.setQuantity(quantity);
                cart.setClient(clientDAO.findByUserName(cartDTO.getUserName()));

                Product product = new Product();
                product.setId(productDTO.getId());
                cart.setProduct(product);

                cartDAO.add(cart);
            }
        } else {

            item.setQuantity(item.getQuantity() + quantity);

            if (cartDTO.getUserName() != null) {
                Cart cart = cartDAO.getByClientAndProduct(cartDTO.getUserName(), productDTO.getId());
                cart.setQuantity(cart.getQuantity() + quantity);

                cartDAO.update(cart);
            }
        }
    }


    @Override
    public void updateCartItem(CartDTO cartDTO, HttpServletRequest request, Principal principal) {

        int idProduct = Integer.parseInt(request.getParameter("idProduct"));
        int quantity = Integer.parseInt(request.getParameter("quantity"));

        CartItemDTO item = cartDTO.findItemByIdProduct(idProduct);

        if (quantity <= 0) {
            cartDTO.removeCartItem(item);

            if (principal != null) {
                Cart cart = cartDAO.getByClientAndProduct(principal.getName(), idProduct);
                cartDAO.remove(cart);
            }
        } else {
            item.setQuantity(quantity);

            if (principal != null) {
                Cart cart = cartDAO.getByClientAndProduct(principal.getName(), idProduct);
                cart.setQuantity(quantity);
                cartDAO.update(cart);
            }
        }

    }

    @Override
    public void removeCartItem(CartDTO cartDTO, int idProduct, Principal principal) {
        CartItemDTO item = cartDTO.findItemByIdProduct(idProduct);

        if (item != null) {
            cartDTO.removeCartItem(item);

            if (principal != null) {
                Cart cart = cartDAO.getByClientAndProduct(principal.getName(), idProduct);
                cartDAO.remove(cart);
            }
        }
    }

    @Override
    public void removeAll(CartDTO cartDTO, Principal principal) {
        cartDTO.setCartItems(new ArrayList<>());

        if (principal != null) {
            cartDAO.removeAll(clientDAO.findByUserName(principal.getName()).getId());
        }
    }

    @Override
    public void checkAvailability(CartDTO cartDTO) {
        for (CartItemDTO item : cartDTO.getCartItems()
        ) {
            long quantityProduct = productDAO.getById(item.getProduct().getId()).getQuantity();
            item.setMissQuantity(0);
            if (item.getQuantity() > quantityProduct) {
                item.setMissQuantity(item.getQuantity() - quantityProduct);
            }
        }
    }


}
