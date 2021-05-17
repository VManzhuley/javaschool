package com.tsystems.javaschool.controller;

import com.tsystems.javaschool.dto.CartDTO;
import com.tsystems.javaschool.dto.ClientDTO;
import com.tsystems.javaschool.dto.OrderDTO;
import com.tsystems.javaschool.dto.ProductAbsDTO;
import com.tsystems.javaschool.entity.PaymentType;
import com.tsystems.javaschool.entity.ShippingType;
import com.tsystems.javaschool.entity.product.Category;
import com.tsystems.javaschool.error.BusinessLogicException;
import com.tsystems.javaschool.error.WrongParameterException;
import com.tsystems.javaschool.service.*;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.security.Principal;
import java.util.List;

@Controller
@SessionAttributes("cart")
public class MainController {

    private final ProductAbsService productAbsService;
    private final CategoryService categoryService;
    private final OrderService orderService;
    private final ClientService clientService;
    private final CartService cartService;


    public MainController(ProductAbsService productAbsService,
                          CategoryService categoryService,
                          OrderService orderService,
                          ClientService clientService,
                          CartService cartService) {
        this.productAbsService = productAbsService;
        this.categoryService = categoryService;
        this.orderService = orderService;
        this.clientService = clientService;
        this.cartService = cartService;

    }

    @ModelAttribute("cart")
    public CartDTO cartDTO() {
        return new CartDTO();
    }


    @RequestMapping(value = "/")
    public ModelAndView mainPage() {
        List<Category> categoryList = categoryService.findAll();
        ModelAndView modelAndView = new ModelAndView();


        modelAndView.setViewName("index");
        modelAndView.addObject("categoryList", categoryList);


        return modelAndView;
    }

    @GetMapping(value = "/shop")
    public ModelAndView shop(@RequestParam int category,
                             @RequestParam(defaultValue = "1") int page,
                             @RequestParam(defaultValue = "id") String sort) {
        List<Category> categoryList = categoryService.findAll();
        List<ProductAbsDTO> productAbsList = productAbsService.allProductsByCategoryWithFSP(category, page, sort);
        int totalPages = productAbsService.getTotalPages();
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("shop");
        modelAndView.addObject("categoryList", categoryList);
        modelAndView.addObject("totalPages", totalPages);
        modelAndView.addObject("productAbsList", productAbsList);

        return modelAndView;
    }

    @GetMapping(value = "/product")
    public ModelAndView product(@RequestParam int id) {
        ProductAbsDTO productAbs = productAbsService.getProductAbsDTO(id);
        ModelAndView modelAndView = new ModelAndView();

        modelAndView.setViewName("product");

        modelAndView.addObject("productAbs", productAbs);

        return modelAndView;
    }

    @PostMapping(value = "/product")
    public ModelAndView addToCart(@ModelAttribute("cart") CartDTO cart,
                                  @RequestParam int id,
                                  HttpServletRequest request,
                                  Principal principal) {
        ProductAbsDTO productAbs = productAbsService.getProductAbsDTO(id);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("product");
        modelAndView.addObject("productAbs", productAbs);

        cartService.addCartItem(cart, id, request, principal);

        return modelAndView;
    }

    @GetMapping("/cart")
    public ModelAndView cart(@ModelAttribute("cart") CartDTO cart) {
        cartService.checkAvailability(cart);

        return new ModelAndView();
    }

    @PostMapping("/cart")
    public ModelAndView updateCartItem(@ModelAttribute("cart") CartDTO cart,
                                       HttpServletRequest request,
                                       Principal principal) {

        cartService.updateCartItem(cart, request, principal);
        cartService.checkAvailability(cart);

        return new ModelAndView();
    }

    @RequestMapping("/deleteCartItem")
    public ModelAndView deleteCartItem(@ModelAttribute("cart") CartDTO cart,
                                       @RequestParam int id,
                                       Principal principal) {
        ModelAndView modelAndView = new ModelAndView();

        cartService.removeCartItem(cart, id, principal);

        modelAndView.setViewName("redirect:/cart");
        return modelAndView;
    }

    @ModelAttribute("client")
    public ClientDTO clientDTO(Principal principal) {
        if (principal != null) {
            return clientService.findByUserName(principal.getName());
        } else {
            return new ClientDTO();
        }

    }


    @GetMapping("/orderConfirm")
    public ModelAndView order(@ModelAttribute("client") ClientDTO client,
                              @ModelAttribute("order") OrderDTO order,
                              String message) {
        ModelAndView modelAndView = new ModelAndView();

        modelAndView.addObject("shippingType", ShippingType.values());
        modelAndView.addObject("paymentType", PaymentType.values());
        modelAndView.addObject("client", client);
        modelAndView.addObject("message", message);

        return modelAndView;
    }


    @PostMapping("/orderConfirm")
    public ModelAndView orderConfirm(@ModelAttribute("cart") CartDTO cart,
                                     @ModelAttribute("client") @Valid ClientDTO clientDTO,
                                     BindingResult bindingResult,
                                     @ModelAttribute("order") OrderDTO order,
                                     Principal principal) {


        if (bindingResult.hasFieldErrors("name") ||
                bindingResult.hasFieldErrors("lastname") ||
                bindingResult.hasFieldErrors("email") ||
                bindingResult.hasFieldErrors("phone")) {
            return order(clientDTO, order, null);
        }

        ModelAndView modelAndView = new ModelAndView();
        long id = 0;
        try {
            id = orderService.addOrder(cart, clientDTO, order, principal);
        } catch (BusinessLogicException businessLogicException) {
            modelAndView.setViewName("redirect:/cart");
            return modelAndView;
        } catch (WrongParameterException wrongParameterException) {
            return order(clientDTO, order, wrongParameterException.getMessage());
        }
        modelAndView.addObject("client", clientDTO);
        modelAndView.addObject("order", order);
        modelAndView.setViewName("redirect:/orderFinish?id=" + id);

        return modelAndView;
    }

    @GetMapping("/orderFinish")
    public ModelAndView orderFinish(@RequestParam int id) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("id", id);

        return modelAndView;
    }


    @GetMapping("/login")
    public ModelAndView login() {
        return new ModelAndView();
    }

    @GetMapping("/loginSuccess")
    public ModelAndView login(@ModelAttribute("cart") CartDTO cart,
                              Principal principal) {

        cartService.mergeCart(cart, principal);

        ModelAndView modelAndView = new ModelAndView();

        modelAndView.setViewName("redirect:/");

        return modelAndView;
    }

    @GetMapping(value = "/user/repeatOrder")
    public ModelAndView repeatOrder(@RequestParam int id,
                                    @ModelAttribute("cart") CartDTO cart,
                                    Principal principal) {
        ModelAndView modelAndView = new ModelAndView();
        orderService.repeatOrder(id, principal, cart);

        modelAndView.setViewName("redirect:/cart");

        return modelAndView;
    }

    @GetMapping(value = "/registration")
    public ModelAndView registration(@ModelAttribute("client") ClientDTO client,
                                     String message) {
        ModelAndView modelAndView = new ModelAndView();


        modelAndView.addObject("client", client);
        modelAndView.addObject("message", message);

        return modelAndView;
    }

    @PostMapping(value = "/registration")
    public ModelAndView registrationConfirm(@ModelAttribute("client") @Valid ClientDTO client,
                                            BindingResult bindingResult) {
        ModelAndView modelAndView = new ModelAndView();


        if (bindingResult.hasErrors()) {
            return registration(client, null);
        }

        try {
            clientService.registerNewClient(client);
        } catch (WrongParameterException wrongParameterException) {
            return registration(client, wrongParameterException.getMessage());
        }

        modelAndView.setViewName("redirect:/");


        return modelAndView;
    }

}
