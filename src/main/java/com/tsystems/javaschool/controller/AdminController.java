package com.tsystems.javaschool.controller;

import com.tsystems.javaschool.dto.*;
import com.tsystems.javaschool.entity.Status;
import com.tsystems.javaschool.entity.product.Colour;
import com.tsystems.javaschool.entity.product.Composition;
import com.tsystems.javaschool.entity.product.Description;
import com.tsystems.javaschool.entity.product.Size;
import com.tsystems.javaschool.service.OrderService;
import com.tsystems.javaschool.service.ParametersService;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.List;

@Controller
@SessionAttributes("productAbs")
@RequestMapping(value = "/admin")
public class AdminController {
    private final OrderService orderService;
    private final ParametersService parametersService;


    public AdminController(OrderService orderService, ParametersService parametersService) {
        this.orderService = orderService;

        this.parametersService = parametersService;


    }

    @GetMapping(value = "/orders")
    public ModelAndView orderList(@RequestParam(defaultValue = "1") int page) {
        ModelAndView modelAndView = new ModelAndView();

        List<OrderDTO> orderDTOList = orderService.allByPage(page);
        long totalPages = orderService.getTotalPagesToAdmin();

        modelAndView.setViewName("orderList");
        modelAndView.addObject("page", page);
        modelAndView.addObject("orderList", orderDTOList);
        modelAndView.addObject("totalPages", totalPages);


        return modelAndView;
    }

    @GetMapping(value = "/order")
    public ModelAndView orderDetails(@RequestParam int id) {
        ModelAndView modelAndView = new ModelAndView();

        OrderDTO orderDTO = orderService.getById(id);
        modelAndView.setViewName("orderDetails");
        modelAndView.addObject("order", orderDTO);
        modelAndView.addObject("status", Status.values());

        return modelAndView;
    }

    @PostMapping(value = "/order")
    public ModelAndView updateOrderDetails(@RequestParam int id, HttpServletRequest request) {
        ModelAndView modelAndView = new ModelAndView();

        orderService.updateStatus(id, request.getParameter("statusType"));
        OrderDTO orderDTO = orderService.getById(id);
        modelAndView.setViewName("orderDetails");
        modelAndView.addObject("order", orderDTO);
        modelAndView.addObject("status", Status.values());

        return modelAndView;
    }

    @ModelAttribute("productAbs")
    public ProductAbsDTO productAbsDTO() {
        return new ProductAbsDTO();
    }

    @GetMapping("/productAdd")
    public ModelAndView productAdd(@ModelAttribute("productAbs") ProductAbsDTO productAbsDTO,
                                   @ModelAttribute("colour") ColourDTO colourDTO,
                                   @ModelAttribute("size") SizeDTO sizeDTO) {
        ModelAndView modelAndView = new ModelAndView();
        List<CategoryDTO> categories = parametersService.getAllCategoryWithoutChild();
        List<Description> descriptions = parametersService.getAllDescription();
        List<Colour> colours = parametersService.getAllColour();
        List<Size> sizes = parametersService.getAllSize();
        List<Composition> compositions = parametersService.gelAllComposition();


        modelAndView.setViewName("productAdd");
        modelAndView.addObject("compositions", compositions);
        modelAndView.addObject("descriptions", descriptions);
        modelAndView.addObject("colours", colours);
        modelAndView.addObject("sizes", sizes);
        modelAndView.addObject("categories", categories);
        modelAndView.addObject("productAbs", productAbsDTO);
        modelAndView.addObject("colour", colourDTO);
        modelAndView.addObject("size", sizeDTO);


        return modelAndView;
    }

    @PostMapping("/productAdd")
    public ModelAndView product(@ModelAttribute("productAbs") @Valid ProductAbsDTO productAbsDTO,
                                BindingResult bindingResult,
                                @ModelAttribute("colour") ColourDTO colourDTO,
                                @ModelAttribute("size") SizeDTO sizeDTO) {
        ModelAndView modelAndView = new ModelAndView();

        productAbsDTO.addSize(sizeDTO);
        productAbsDTO.addColour(colourDTO);

        if (bindingResult.hasErrors()) {
            return productAdd(productAbsDTO, colourDTO, sizeDTO);
        }
        modelAndView.setViewName("productAdd");
        List<CategoryDTO> categories = parametersService.getAllCategoryWithoutChild();
        List<Description> descriptions = parametersService.getAllDescription();
        List<Colour> colours = parametersService.getAllColour();
        List<Size> sizes = parametersService.getAllSize();
        List<Composition> compositions = parametersService.gelAllComposition();


        modelAndView.addObject("compositions", compositions);
        modelAndView.addObject("descriptions", descriptions);
        modelAndView.addObject("colours", colours);
        modelAndView.addObject("sizes", sizes);
        modelAndView.addObject("categories", categories);
        modelAndView.addObject("productAbs", productAbsDTO);
        modelAndView.addObject("colour", colourDTO);
        modelAndView.addObject("size", sizeDTO);

        return modelAndView;
    }


}
