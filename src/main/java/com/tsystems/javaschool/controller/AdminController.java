package com.tsystems.javaschool.controller;

import com.tsystems.javaschool.dto.*;
import com.tsystems.javaschool.entity.Status;
import com.tsystems.javaschool.service.*;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping(value = "/admin")
@SessionAttributes("productAbs")
public class AdminController {
    private final OrderService orderService;
    private final ParametersService parametersService;
    private final StatisticService statisticService;
    private final ProductAbsService productAbsService;
    private final CategoryService categoryService;

    public AdminController(OrderService orderService,
                           ParametersService parametersService,
                           StatisticService statisticService,
                           ProductAbsService productAbsService,
                           CategoryService categoryService) {
        this.orderService = orderService;
        this.parametersService = parametersService;
        this.statisticService = statisticService;
        this.productAbsService = productAbsService;
        this.categoryService = categoryService;
    }

    @GetMapping(value = "/orders")
    public ModelAndView orderList(@RequestParam(defaultValue = "1") int page) {
        ModelAndView modelAndView = new ModelAndView("orderList");

        modelAndView.addObject("page", page);
        modelAndView.addObject("orderList", orderService.getAllByPage(page));
        modelAndView.addObject("totalPages", orderService.getTotalPagesToAdmin());

        return modelAndView;
    }

    @GetMapping(value = "/order")
    public ModelAndView orderDetails(@RequestParam long id) {
        ModelAndView modelAndView = new ModelAndView("orderDetails");

        modelAndView.addObject("order", orderService.getById(id));
        modelAndView.addObject("status", Status.values());

        return modelAndView;
    }

    @PostMapping(value = "/order")
    public ModelAndView updateOrderDetails(@RequestParam long id,
                                           HttpServletRequest request) {
        ModelAndView modelAndView = new ModelAndView("orderDetails");

        orderService.updateStatus(id, request.getParameter("statusType"));

        modelAndView.addObject("order", orderService.getById(id));
        modelAndView.addObject("status", Status.values());

        return modelAndView;
    }

    @ModelAttribute("productAbs")
    public ProductAbsDTO productAbsDTO(@PathVariable(required = false) Long idProduct) {
        if (idProduct == null) {
            return new ProductAbsDTO();
        } else {
            return productAbsService.addProducts(productAbsService.getProductAbsDTO(idProduct));
        }
    }

    public ModelAndView mvForCreateOrUpdateProductAbs(ProductAbsDTO productAbsDTO) {
        ModelAndView modelAndView = new ModelAndView();

        modelAndView.setViewName("admin/productEdit");
        modelAndView.addObject("compositions", parametersService.gelAllComposition());
        modelAndView.addObject("descriptions", parametersService.getAllDescription());
        modelAndView.addObject("colours", parametersService.getAllColour());
        modelAndView.addObject("sizes", parametersService.getAllSize());
        modelAndView.addObject("categories", categoryService.getAllWithoutChild());
        modelAndView.addObject("categoriesForSidebar", categoryService.getAllWithoutParent());
        modelAndView.addObject("productAbs", productAbsDTO);
        modelAndView.addObject("colour", new ColourDTO());
        modelAndView.addObject("size", new SizeDTO());

        return modelAndView;
    }


    @GetMapping(value = "/products/add")
    public ModelAndView productAbsAdd(@ModelAttribute("productAbs") ProductAbsDTO productAbsDTO,
                                      SessionStatus status) {
        if (productAbsDTO.getId() != 0) {
            status.setComplete();
            return new ModelAndView("redirect:/admin/products/add");
        }

        return mvForCreateOrUpdateProductAbs(productAbsDTO);
    }

    @PostMapping(value = "/products/add")
    public ModelAndView productAbsAdd(@ModelAttribute("productAbs") ProductAbsDTO productAbsDTO,
                                      @ModelAttribute("colour") ColourDTO colourDTO,
                                      @ModelAttribute("size") SizeDTO sizeDTO,
                                      HttpServletRequest request,
                                      SessionStatus status) {

        if (request.getParameter("submit") != null) {
            long id = productAbsService.createOrUpdate(productAbsDTO);
            status.setComplete();
            return new ModelAndView("redirect:/admin/products/" + id + "/details");
        }

        productAbsDTO.addSize(sizeDTO);
        productAbsDTO.addColour(colourDTO);

        return mvForCreateOrUpdateProductAbs(productAbsDTO);
    }

    @GetMapping(value = "/products/deleteColour")
    public ModelAndView deleteColour(@ModelAttribute("productAbs") ProductAbsDTO productAbsDTO,
                                     @RequestParam int n) {
        productAbsDTO.deleteColour(n);
        if (productAbsDTO.getId() != 0) {
            return new ModelAndView("redirect:/admin/products/" + productAbsDTO.getId());
        }
        return new ModelAndView("redirect:/admin/products/add");
    }

    @GetMapping(value = "/products/deleteSize")
    public ModelAndView deleteSize(@ModelAttribute("productAbs") ProductAbsDTO productAbsDTO,
                                   @RequestParam int n) {
        productAbsDTO.deleteSize(n);
        if (productAbsDTO.getId() != 0) {
            return new ModelAndView("redirect:/admin/products/" + productAbsDTO.getId());
        }
        return new ModelAndView("redirect:/admin/products/add");
    }

    @GetMapping(value = "/products/{idProduct}")
    public ModelAndView productAbsUpdate(@PathVariable long idProduct,
                                         @ModelAttribute("productAbs") ProductAbsDTO productAbsDTO,
                                         SessionStatus status) {
        if (productAbsDTO.getId() != idProduct) {
            status.setComplete();
            return new ModelAndView("redirect:/admin/products/" + idProduct);
        }

        return mvForCreateOrUpdateProductAbs(productAbsDTO);
    }

    @PostMapping(value = "/products/{idProduct}")
    public ModelAndView productAbsUpdate(@PathVariable long idProduct,
                                         @ModelAttribute("productAbs") ProductAbsDTO productAbsDTO,
                                         @ModelAttribute("colour") ColourDTO colourDTO,
                                         @ModelAttribute("size") SizeDTO sizeDTO,
                                         HttpServletRequest request,
                                         SessionStatus status) {

        if (request.getParameter("submit") != null) {
            productAbsService.createOrUpdate(productAbsDTO);
            status.setComplete();
            return new ModelAndView("redirect:/admin/products/" + idProduct + "/details");
        }

        if (request.getParameter("createCategoryNew") != null) {
            categoryService.create(request.getParameter("categoryNameNew"));
        }

        if (request.getParameter("createDescriptionNew") != null) {
            parametersService.createDescription(request.getParameter("descriptionNameNew"));
        }
        if (request.getParameter("createCompositionNew") != null) {
            parametersService.createComposition(request.getParameter("compositionNameNew"));
        }

        productAbsDTO.addSize(sizeDTO);
        productAbsDTO.addColour(colourDTO);

        return mvForCreateOrUpdateProductAbs(productAbsDTO);

    }


    @GetMapping("/products/{idProduct}/details")
    public ModelAndView productAddConfirm(@PathVariable long idProduct,
                                          @ModelAttribute("productAbs") ProductAbsDTO productAbsDTO) {
        ModelAndView modelAndView = new ModelAndView("admin/productEditDetails");

        modelAndView.addObject("productAbs", productAbsDTO);
        modelAndView.addObject("categoriesForSidebar", categoryService.getAllWithoutParent());

        return modelAndView;
    }

    @PostMapping("/products/{idProduct}/details")
    public ModelAndView productAddFinish(@PathVariable long idProduct,
                                         @ModelAttribute("productAbs") ProductAbsDTO productAbsDTO,
                                         HttpServletRequest request,
                                         SessionStatus status) {
        productAbsService.updateProductsPhotoWV(productAbsDTO);

        if (request.getParameter("ChangePublish") != null) {
            productAbsService.inverseOutdated(productAbsDTO);
        }
        status.setComplete();

        return new ModelAndView("redirect:/product?id=" + idProduct);
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
        ModelAndView modelAndView = new ModelAndView("admin/statisticProducts");

        modelAndView.addObject("status", Status.values());
        modelAndView.addObject("products", statisticService.topProductOrdered(status, sort, pageSize));

        return modelAndView;

    }

    @RequestMapping(value = "statistic/clients", method = {RequestMethod.GET, RequestMethod.POST})
    public ModelAndView clientStatistic(@RequestParam(defaultValue = "10") int pageSize,
                                        @RequestParam(defaultValue = "ALL") String status,
                                        @RequestParam(defaultValue = "total") String sort) {
        ModelAndView modelAndView = new ModelAndView("admin/statisticClients");

        modelAndView.addObject("status", Status.values());
        modelAndView.addObject("orders", statisticService.topClient(status, sort, pageSize));

        return modelAndView;

    }


    @GetMapping(value = "statistic/proceeds")
    public ModelAndView proceed() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("admin/statisticProceeds");
        return modelAndView;
    }


    @PostMapping(value = "statistic/proceeds")
    public ModelAndView productStatistic(@RequestParam String period,
                                         @RequestParam String week,
                                         @RequestParam String month,
                                         @RequestParam String dateFrom,
                                         @RequestParam String dateTo) {
        ModelAndView modelAndView = new ModelAndView("admin/statisticProceeds");
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

        modelAndView.addObject("orders", list);
        modelAndView.addObject("proceeds", proceeds);


        return modelAndView;

    }

    @RequestMapping(value = "statistic/warehouse", method = {RequestMethod.GET, RequestMethod.POST})
    public ModelAndView productStatistic(@RequestParam(defaultValue = "10") int pageSize,
                                         @RequestParam(defaultValue = "desc") String sort) {
        ModelAndView modelAndView = new ModelAndView();

        List<ProductDTO> list = statisticService.topProduct(sort, pageSize);


        modelAndView.setViewName("admin/statisticWarehouse");
        modelAndView.addObject("status", Status.values());
        modelAndView.addObject("products", list);
        modelAndView.addObject("productCount", statisticService.getProductCount());

        return modelAndView;

    }

    @GetMapping(value = "/category")
    public ModelAndView category(String message) {
        ModelAndView modelAndView = new ModelAndView("admin/category");

        modelAndView.addObject("categories", categoryService.getAllWithoutChild());
        modelAndView.addObject("categoriesParent", categoryService.getAllWithoutParentAndProducts());
        modelAndView.addObject("categoriesForSidebar", categoryService.getAllWithoutParent());
        modelAndView.addObject("message", message);

        return modelAndView;
    }

    @PostMapping(value = "/category")
    public ModelAndView category(@RequestParam long category,
                                 @RequestParam long newParentCategory,
                                 HttpServletRequest request) {
        ModelAndView modelAndView = new ModelAndView("redirect:/admin/category");

        if (request.getParameter("new") != null) {
            categoryService.create(request.getParameter("categoryNew"));
            return modelAndView;
        }
        categoryService.updateParent(category, newParentCategory);

        return modelAndView;
    }

    @GetMapping(value = "/products")
    public ModelAndView products() {
        ModelAndView modelAndView = new ModelAndView("/admin/productsAdmin");

        modelAndView.addObject("categoriesForSidebar", categoryService.getAllWithoutParent());

        return modelAndView;
    }
}
