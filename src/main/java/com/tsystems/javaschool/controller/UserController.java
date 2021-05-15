package com.tsystems.javaschool.controller;


import com.tsystems.javaschool.dto.ClientDTO;
import com.tsystems.javaschool.dto.OrderDTO;
import com.tsystems.javaschool.entity.Status;
import com.tsystems.javaschool.service.ClientService;
import com.tsystems.javaschool.service.OrderService;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.security.Principal;
import java.util.List;


@Controller
@RequestMapping(value = "/user")
public class UserController {
    private final OrderService orderService;
    private final ClientService clientService;

    public UserController(OrderService orderService, ClientService clientService) {
        this.orderService = orderService;
        this.clientService = clientService;
    }


    @GetMapping(value = "/orders")
    public ModelAndView orderList(@RequestParam(defaultValue = "1") int page,
                                  Principal principal) {
        ModelAndView modelAndView = new ModelAndView();

        List<OrderDTO> orderDTOList = orderService.allByClientAndPage(principal, page);
        long totalPages = orderService.getTotalPagesToUser(principal);

        modelAndView.setViewName("orderList");
        modelAndView.addObject("page", page);
        modelAndView.addObject("orderList", orderDTOList);
        modelAndView.addObject("totalPages", totalPages);


        return modelAndView;
    }

    @GetMapping(value = "/order")
    public ModelAndView orderDetails(@RequestParam int id,
                                     Principal principal) {
        ModelAndView modelAndView = new ModelAndView();

        OrderDTO orderDTO = orderService.getById(id);
        if (orderDTO.getClient().getEmail().equals(principal.getName())) {
            modelAndView.setViewName("orderDetails");
            modelAndView.addObject("order", orderDTO);
            modelAndView.addObject("status", Status.values());
        } else {
            modelAndView.setViewName("redirect:/user/orders");
        }

        return modelAndView;
    }

    @GetMapping(value = "/orderCancel")
    public ModelAndView updateOrderDetails(@RequestParam int id,
                                           Principal principal) {
        ModelAndView modelAndView = new ModelAndView();

        if (orderService.getById(id).getClient().getEmail().equals(principal.getName())) {
            orderService.updateStatus(id, Status.CANCELED.name());
        }

        modelAndView.setViewName("redirect:/user/orders");

        return modelAndView;
    }

    @ModelAttribute("client")
    public ClientDTO clientDTO(Principal principal) {
        return clientService.findByUserName(principal.getName());
    }

    @GetMapping(value = "/account")
    public ModelAndView account(@ModelAttribute("client") ClientDTO clientDTO) {
        ModelAndView modelAndView = new ModelAndView();

        modelAndView.setViewName("registration");

        modelAndView.addObject("client", clientDTO);
        return modelAndView;
    }

    @PostMapping(value = "/account")
    public ModelAndView updateAccount(@ModelAttribute("client") @Valid ClientDTO clientDTO,
                                      BindingResult bindingResult) {
        if (bindingResult.hasFieldErrors("name") ||
                bindingResult.hasFieldErrors("lastname") ||
                bindingResult.hasFieldErrors("phone")) {
            bindingResult.recordSuppressedField("password");


            return account(clientDTO);
        }
        if (!clientDTO.getPassword().equals("") && bindingResult.hasErrors()) {
            return account(clientDTO);
        }

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("client", clientDTO);
        return modelAndView;
    }

}
