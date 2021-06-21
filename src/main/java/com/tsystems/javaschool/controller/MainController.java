package com.tsystems.javaschool.controller;


import com.tsystems.javaschool.dto.CartDTO;
import com.tsystems.javaschool.dto.ClientDTO;
import com.tsystems.javaschool.dto.OrderDTO;
import com.tsystems.javaschool.entity.PaymentType;
import com.tsystems.javaschool.entity.ShippingType;
import com.tsystems.javaschool.error.BusinessLogicException;
import com.tsystems.javaschool.error.WrongParameterException;
import com.tsystems.javaschool.service.*;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.security.Principal;

@Controller
@SessionAttributes("cart")
public class MainController {
    private static final String URL_REDIRECT_TO_CART = "redirect:/cart";
    private final ProductAbsService productAbsService;
    private final CategoryService categoryService;
    private final OrderService orderService;
    private final ClientService clientService;
    private final CartService cartService;

    private final ParametersService parametersService;


    public MainController(ProductAbsService productAbsService,
                          CategoryService categoryService,
                          OrderService orderService,
                          ClientService clientService,
                          CartService cartService,
                          ParametersService parametersService) {
        this.productAbsService = productAbsService;
        this.categoryService = categoryService;
        this.orderService = orderService;
        this.clientService = clientService;
        this.cartService = cartService;

        this.parametersService = parametersService;
    }


    @ModelAttribute("cart")
    public CartDTO cartDTO(Principal principal) {
        if (principal != null) {
            CartDTO cartDTO = new CartDTO();
            cartDTO.setUserName(principal.getName());

            return cartDTO;
        }
        return new CartDTO();
    }


    @RequestMapping(value = "/")
    public ModelAndView mainPage() {

        return new ModelAndView("index");
    }

    @GetMapping(value = "/shop")
    public ModelAndView shop(@RequestParam long category,
                             @RequestParam(defaultValue = "1") int page,
                             @RequestParam(defaultValue = "id") String sort,
                             @RequestParam(defaultValue = "0") long composition,
                             @RequestParam(defaultValue = "0") long description) {
        ModelAndView modelAndView = new ModelAndView("shop");


        modelAndView.addObject("descriptions", parametersService.getAllDescriptionByCategory(category));
        modelAndView.addObject("compositions", parametersService.gelAllCompositionByCategory(category));
        modelAndView.addObject("categoryList", categoryService.getAllWithoutParent());
        modelAndView.addObject("productAbsList", productAbsService.getAllByCategoryWithFSP(category, page, sort, composition, description));
        modelAndView.addObject("totalPages", productAbsService.getTotalPages());

        return modelAndView;
    }

    @GetMapping(value = "/product")
    public ModelAndView product(@RequestParam long id) {
        ModelAndView modelAndView = new ModelAndView("product");

        modelAndView.addObject("productAbs", productAbsService.getProductAbsDTO(id));

        return modelAndView;
    }

    @PostMapping(value = "/product")
    public ModelAndView addToCart(@ModelAttribute("cart") CartDTO cart,
                                  @RequestParam long id,
                                  HttpServletRequest request) {
        cartService.addCartItem(cart, id, request);

        ModelAndView modelAndView = new ModelAndView("product");
        modelAndView.addObject("productAbs", productAbsService.getProductAbsDTO(id));

        return modelAndView;
    }

    @GetMapping("/cart")
    public ModelAndView cart(@ModelAttribute("cart") CartDTO cart) {
        cartService.checkAvailability(cart);

        return new ModelAndView();
    }

    @PostMapping("/cart")
    public ModelAndView updateCartItem(@ModelAttribute("cart") CartDTO cart,
                                       HttpServletRequest request) {

        cartService.updateCartItem(cart, request);
        cartService.checkAvailability(cart);

        return new ModelAndView();
    }

    @RequestMapping("/cart/delete")
    public ModelAndView deleteCartItem(@ModelAttribute("cart") CartDTO cart,
                                       @RequestParam long id) {
        ModelAndView modelAndView = new ModelAndView();

        cartService.removeCartItem(cart, id);

        modelAndView.setViewName(URL_REDIRECT_TO_CART);
        return modelAndView;
    }

    @ModelAttribute("client")
    public ClientDTO clientDTO(Principal principal) {
        if (principal != null) {
            return clientService.getByUserName(principal.getName());
        } else {
            return new ClientDTO();
        }

    }


    @GetMapping("/order/add")
    public ModelAndView order(@ModelAttribute("client") ClientDTO client,
                              @ModelAttribute("order") OrderDTO order,
                              String message) {
        ModelAndView modelAndView = new ModelAndView("orderConfirm");

        modelAndView.addObject("shippingType", ShippingType.values());
        modelAndView.addObject("paymentType", PaymentType.values());
        modelAndView.addObject("client", client);
        modelAndView.addObject("message", message);

        return modelAndView;
    }


    @PostMapping("/order/add")
    public ModelAndView orderConfirm(@ModelAttribute("cart") CartDTO cartDTO,
                                     @ModelAttribute("client") @Valid ClientDTO clientDTO,
                                     BindingResult bindingResult,
                                     @ModelAttribute("order") OrderDTO orderDTO) {


        if (bindingResult.hasFieldErrors("name") ||
                bindingResult.hasFieldErrors("lastname") ||
                bindingResult.hasFieldErrors("email") ||
                bindingResult.hasFieldErrors("phone")) {
            return order(clientDTO, orderDTO, null);
        }

        long id;
        try {
            id = orderService.createOrder(cartDTO, clientDTO, orderDTO);
        } catch (BusinessLogicException businessLogicException) {
            return new ModelAndView(URL_REDIRECT_TO_CART);
        } catch (WrongParameterException wrongParameterException) {
            return order(clientDTO, orderDTO, wrongParameterException.getMessage());
        }

        return new ModelAndView("redirect:/order?id=" + id);
    }

    @GetMapping("/order")
    public ModelAndView orderFinish(@RequestParam long id,
                                    SessionStatus status) {
        status.setComplete();

        return new ModelAndView("orderFinish");
    }


    @GetMapping("/login")
    public ModelAndView login() {
        return new ModelAndView();
    }

    @GetMapping("/login/admin")
    public ModelAndView loginAdmin(@ModelAttribute("cart") CartDTO cart,
                              Principal principal) {

        cartService.mergeCart(cart, principal);

        return new ModelAndView("redirect:/admin/orders");
    }
    @GetMapping("/login/user")
    public ModelAndView loginUser(@ModelAttribute("cart") CartDTO cart,
                              Principal principal) {

        cartService.mergeCart(cart, principal);

        return new ModelAndView(URL_REDIRECT_TO_CART);
    }

    @GetMapping(value = "/user/order/repeat")
    public ModelAndView repeatOrder(@RequestParam long id,
                                    @ModelAttribute("cart") CartDTO cart,
                                    Principal principal) {

        orderService.repeatOrder(id, principal, cart);

        return new ModelAndView(URL_REDIRECT_TO_CART);
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

        if (bindingResult.hasErrors()) {
            return registration(client, null);
        }
        try {
            clientService.registerClient(client);
        } catch (WrongParameterException wrongParameterException) {
            return registration(client, wrongParameterException.getMessage());
        }

        return new ModelAndView("redirect:/");
    }


    @GetMapping(value = "/error403")
    public ModelAndView error403() {
        ModelAndView modelAndView = new ModelAndView("error");

        modelAndView.addObject("errorCode", 403);

        return modelAndView;
    }
}
