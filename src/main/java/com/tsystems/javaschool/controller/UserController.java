package com.tsystems.javaschool.controller;


import com.tsystems.javaschool.dto.ClientDTO;
import com.tsystems.javaschool.dto.OrderDTO;
import com.tsystems.javaschool.entity.Status;
import com.tsystems.javaschool.error.WrongParameterException;
import com.tsystems.javaschool.service.ClientService;
import com.tsystems.javaschool.service.OrderService;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.security.Principal;
import java.util.ArrayList;
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

    @ModelAttribute("client")
    public ClientDTO clientDTO(Principal principal) {
        return clientService.getByUserName(principal.getName());
    }

    @GetMapping(value = "/orders")
    public ModelAndView orderList(@RequestParam(defaultValue = "1") int page,
                                  Principal principal) {
        ModelAndView modelAndView = new ModelAndView();


        List<OrderDTO> orderDTOList = orderService.getAllByClientAndPage(principal, page);
        long totalPages = orderService.getTotalPagesToUser(principal);

        modelAndView.setViewName("orderList");
        modelAndView.addObject("page", page);
        modelAndView.addObject("orderList", orderDTOList);
        modelAndView.addObject("totalPages", totalPages);


        return modelAndView;
    }

    @GetMapping(value = "/order")
    public ModelAndView orderDetails(@RequestParam long id,
                                     Principal principal) {
        ModelAndView modelAndView = new ModelAndView();

        OrderDTO orderDTO = orderService.getById(id);
        if (orderDTO.getClient().getEmail().equals(principal.getName()) ||
                orderDTO.getClient().getUserNameParent().equals(principal.getName())) {
            modelAndView.setViewName("orderDetails");
            modelAndView.addObject("order", orderDTO);
            modelAndView.addObject("status", Status.values());
        } else {
            modelAndView.setViewName("redirect:/user/orders");
        }

        return modelAndView;
    }

    @GetMapping(value = "/order/cancel")
    public ModelAndView updateOrderDetails(@RequestParam long id,
                                           Principal principal) {

        OrderDTO orderDTO = orderService.getById(id);

        if (orderDTO.getClient().getEmail().equals(principal.getName()) ||
                orderDTO.getClient().getUserNameParent().equals(principal.getName())) {
            orderService.updateStatus(id, Status.CANCELED.name());
        }



        return new ModelAndView("redirect:/user/orders");
    }

    @GetMapping(value = "/account")
    public ModelAndView account(@ModelAttribute("client") ClientDTO clientDTO,
                                String message) {
        ModelAndView modelAndView = new ModelAndView("registration");

        modelAndView.addObject("client", clientDTO);
        modelAndView.addObject("message", message);
        return modelAndView;
    }

    @PostMapping(value = "/account")
    public ModelAndView updateAccount(@ModelAttribute("client") @Valid ClientDTO clientDTO,
                                      BindingResult bindingResult,
                                      Principal principal) {


        if (bindingResult.hasFieldErrors("name") ||
                bindingResult.hasFieldErrors("lastname") ||
                bindingResult.hasFieldErrors("phone") ||
                bindingResult.hasFieldErrors("email")) {

            return account(clientDTO, null);
        }
        if (!clientDTO.getPassword().equals("") && bindingResult.hasErrors()) {
            return account(clientDTO, null);
        }
        try {
            clientService.update(clientDTO, principal);
        } catch (WrongParameterException wrongParameterException) {
            return account(clientDTO, wrongParameterException.getMessage());
        }
        List<String> messages = new ArrayList<>();
        messages.add("Data updated successfully");
        if (!clientDTO.getEmail().equals(principal.getName())) {

            return new ModelAndView("redirect:/logout");
        }

        ModelAndView modelAndView = new ModelAndView("registration");
        modelAndView.addObject("client", clientDTO);
        modelAndView.addObject("messageUpdate", messages);

        return modelAndView;
    }

}
