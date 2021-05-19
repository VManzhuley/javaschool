package com.tsystems.javaschool.controller;

import com.tsystems.javaschool.dto.*;
import com.tsystems.javaschool.entity.Status;
import com.tsystems.javaschool.entity.product.Colour;
import com.tsystems.javaschool.entity.product.Composition;
import com.tsystems.javaschool.entity.product.Description;
import com.tsystems.javaschool.entity.product.Size;
import com.tsystems.javaschool.service.*;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Controller
@SessionAttributes("productAbs")
@RequestMapping(value = "/admin")
public class AdminController {
    private final OrderService orderService;
    private final ParametersService parametersService;
    private final StatisticService statisticService;
    private final ProductAbsService productAbsService;
    private final CategoryService categoryService;


    public AdminController(OrderService orderService, ParametersService parametersService, StatisticService statisticService, ProductAbsService productAbsService, CategoryService categoryService) {
        this.orderService = orderService;

        this.parametersService = parametersService;


        this.statisticService = statisticService;
        this.productAbsService = productAbsService;
        this.categoryService = categoryService;
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

    @RequestMapping(value = "/product-add", method = {RequestMethod.GET, RequestMethod.POST})
    public ModelAndView productAbsAdd(@ModelAttribute("productAbs") ProductAbsDTO productAbsDTO,
                                      @ModelAttribute("colour") ColourDTO colourDTO,
                                      @ModelAttribute("size") SizeDTO sizeDTO,
                                      HttpServletRequest request) {
        ModelAndView modelAndView = new ModelAndView();

        productAbsDTO.addSize(sizeDTO);
        productAbsDTO.addColour(colourDTO);

        if (request.getParameter("submit") != null) {
            modelAndView.setViewName("redirect:/admin/product-add-confirm");
            return modelAndView;
        }

        List<CategoryDTO> categories = categoryService.getAllWithoutChild();
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


    @GetMapping("/product-add-confirm")
    public ModelAndView productAddConfirm(@ModelAttribute("productAbs") ProductAbsDTO productAbsDTO) {
        ModelAndView modelAndView = new ModelAndView();

        productAbsDTO.addProducts();

        modelAndView.setViewName("productAddConfirm");
        modelAndView.addObject("productAbs", productAbsDTO);
        return modelAndView;
    }

    @PostMapping("/product-add-confirm")
    public ModelAndView productAddFinish(@ModelAttribute("productAbs") ProductAbsDTO productAbsDTO) {
        ModelAndView modelAndView = new ModelAndView();
        int id = productAbsService.add(productAbsDTO);

        modelAndView.setViewName("redirect:/product?id=" + id);

        return modelAndView;
    }


    @GetMapping("/statistic")
    public ModelAndView statistic() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("statistic");
        return modelAndView;
    }

    @RequestMapping(value = "/statistic/products", method = {RequestMethod.GET, RequestMethod.POST})
    public ModelAndView productStatistic(@RequestParam(defaultValue = "10") int pageSize,
                                         @RequestParam(defaultValue = "ALL") String status,
                                         @RequestParam(defaultValue = "quantity") String sort) {
        ModelAndView modelAndView = new ModelAndView();

        List<ProductOrderedDTO> list = statisticService.topProductOrdered(status, sort, pageSize);


        modelAndView.setViewName("statisticProducts");
        modelAndView.addObject("status", Status.values());
        modelAndView.addObject("pPageSize", pageSize);
        modelAndView.addObject("pStatus", status);
        modelAndView.addObject("pSort", sort);
        modelAndView.addObject("products", list);
        return modelAndView;

    }

    @RequestMapping(value = "statistic/clients", method = {RequestMethod.GET, RequestMethod.POST})
    public ModelAndView clientStatistic(@RequestParam(defaultValue = "10") int pageSize,
                                        @RequestParam(defaultValue = "ALL") String status,
                                        @RequestParam(defaultValue = "total") String sort) {
        ModelAndView modelAndView = new ModelAndView();

        List<OrderDTO> list = statisticService.topClient(status, sort, pageSize);

        modelAndView.setViewName("statisticClients");
        modelAndView.addObject("status", Status.values());
        modelAndView.addObject("pStatus", status);
        modelAndView.addObject("pPageSize", pageSize);
        modelAndView.addObject("pSort", sort);
        modelAndView.addObject("orders", list);
        return modelAndView;

    }


    @GetMapping(value = "statistic/proceeds")
    public ModelAndView proceed() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("statisticProceeds");
        return modelAndView;
    }


    @PostMapping(value = "statistic/proceeds")
    public ModelAndView productStatistic(@RequestParam String period,
                                         @RequestParam String week,
                                         @RequestParam String month,
                                         @RequestParam String dateFrom,
                                         @RequestParam String dateTo) {
        ModelAndView modelAndView = new ModelAndView();
        LocalDate fromLD;
        LocalDate toLD;

        switch (period) {
            case "week":
                if (week.equals("")) {
                    fromLD = LocalDate.now().with(DayOfWeek.MONDAY);
                    toLD = fromLD.plusWeeks(1).minusDays(1);
                    break;
                }
                fromLD = LocalDate.from(DateTimeFormatter.ISO_WEEK_DATE.parse(week.concat("-1")));
                toLD = fromLD.plusWeeks(1).minusDays(1);
                break;

            case "month":
                if (month.equals("")) {
                    fromLD = LocalDate.now().withDayOfMonth(1);
                    toLD = fromLD.plusMonths(1).minusDays(1);
                    break;
                }
                fromLD = LocalDate.from(DateTimeFormatter.ISO_LOCAL_DATE.parse(month.concat("-01")));
                toLD = fromLD.plusMonths(1).minusDays(1);
                break;

            default:
                if (dateFrom.equals("") || dateTo.equals("")) {
                    if (dateFrom.length() < dateTo.length()) {
                        fromLD = LocalDate.now().minusYears(10);
                        toLD = LocalDate.from(DateTimeFormatter.ISO_LOCAL_DATE.parse(dateTo));
                        break;
                    }
                    if (dateFrom.length() > dateTo.length()) {
                        fromLD = LocalDate.from(DateTimeFormatter.ISO_LOCAL_DATE.parse(dateFrom));
                        toLD = LocalDate.now();
                        break;
                    }
                    fromLD = LocalDate.now().minusYears(10);
                    toLD = LocalDate.now();
                    break;
                }

                fromLD = LocalDate.from(DateTimeFormatter.ISO_LOCAL_DATE.parse(dateFrom));
                toLD = LocalDate.from(DateTimeFormatter.ISO_LOCAL_DATE.parse(dateTo));
                break;

        }
        List<OrderDTO> list = new ArrayList<>();
        double proceeds = 0;
        if (fromLD.isBefore(toLD)) {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            String from = fromLD.format(formatter).concat(" 00:00:00");
            String to = toLD.format(formatter).concat(" 23:59:59");
            list = statisticService.ordersCompletedByDate(from, to);
            proceeds = statisticService.getProceeds();
        }
        modelAndView.setViewName("statisticProceeds");
        modelAndView.addObject("pPeriod", period);
        modelAndView.addObject("pWeek", week);
        modelAndView.addObject("pMonth", month);
        modelAndView.addObject("pDateFrom", dateFrom);
        modelAndView.addObject("pDateTo", dateTo);
        modelAndView.addObject("orders", list);
        modelAndView.addObject("proceeds", proceeds);


        return modelAndView;

    }

    @RequestMapping(value = "statistic/warehouse", method = {RequestMethod.GET, RequestMethod.POST})
    public ModelAndView productStatistic(@RequestParam(defaultValue = "10") int pageSize,
                                         @RequestParam(defaultValue = "desc") String sort) {
        ModelAndView modelAndView = new ModelAndView();

        List<ProductDTO> list = statisticService.topProduct(sort, pageSize);


        modelAndView.setViewName("statisticWarehouse");
        modelAndView.addObject("status", Status.values());
        modelAndView.addObject("pPageSize", pageSize);
        modelAndView.addObject("pSort", sort);
        modelAndView.addObject("products", list);
        modelAndView.addObject("productCount", statisticService.getProductCount());
        return modelAndView;

    }

    @GetMapping(value = "/category")
    public ModelAndView category(String message) {
        ModelAndView modelAndView = new ModelAndView();

        List<CategoryDTO> listForStep1 = categoryService.findAllDTO();

        modelAndView.setViewName("category");
        modelAndView.addObject("listStep1", listForStep1);
        modelAndView.addObject("message", message);

        return modelAndView;
    }

    @PostMapping(value = "/category")
    public ModelAndView category(@RequestParam int step1,
                                 @RequestParam int step2,
                                 @RequestParam int step3,
                                 HttpServletRequest request) {
        ModelAndView modelAndView = new ModelAndView();

        if (request.getParameter("new") != null) {
            categoryService.add(request.getParameter("categoryNew"));
            modelAndView.setViewName("redirect:/admin/category");
            return modelAndView;
        }
        if (categoryService.checkChild(step1)) {
            return category("This category cannot be moved, it has children!");
        }

        if (step3 != 0) {
            if (step2 == 0) {
                categoryService.updateParent(step1, step3);
            } else {
                productAbsService.updateCategory(step2, step3);
            }
            return category("Success!");
        }

        List<CategoryDTO> listForStep1 = categoryService.findAllDTO();
        List<ProductAbsDTO> listForStep2 = productAbsService.allByCategory(step1);
        List<CategoryDTO> listForStep3 = categoryService.getAllWithoutChild();

        if (listForStep2.isEmpty()) {
            listForStep3 = categoryService.getAllWithoutParentAndProducts();
        }
        modelAndView.setViewName("category");
        modelAndView.addObject("listStep1", listForStep1);
        modelAndView.addObject("listStep2", listForStep2);
        modelAndView.addObject("listStep3", listForStep3);
        modelAndView.addObject("step1", step1);

        return modelAndView;
    }


}
