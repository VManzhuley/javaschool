package com.tsystems.javaschool.controller;

import com.tsystems.javaschool.dto.CartDTO;
import com.tsystems.javaschool.dto.ProductAbsDTO;
import com.tsystems.javaschool.dto.ProductDTO;
import com.tsystems.javaschool.entity.product.Category;
import com.tsystems.javaschool.service.CartService;
import com.tsystems.javaschool.service.CategoryService;
import com.tsystems.javaschool.service.ProductAbsService;
import com.tsystems.javaschool.service.ProductService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@SessionAttributes("cart")
public class MainController {

    private final ProductAbsService productAbsService;
    private final CategoryService categoryService;
    private final CartService cartService;
    private final ProductService productService;


    public MainController(ProductAbsService productAbsService, CategoryService categoryService, CartService cartService, ProductService productService) {
        this.productAbsService = productAbsService;
        this.categoryService = categoryService;
        this.cartService = cartService;
        this.productService = productService;
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
    public ModelAndView shop(@RequestParam int category, @RequestParam(defaultValue = "1") int page, @RequestParam(defaultValue = "id") String sort) {
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


    @PostMapping(value = "product")
    public ModelAndView addToCart(@ModelAttribute("cart") CartDTO cart, @RequestParam int id, HttpServletRequest request) {
        ProductAbsDTO productAbs = productAbsService.getProductAbsDTO(id);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("product");
        modelAndView.addObject("productAbs", productAbs);

        if (!(request.getParameter("colourMain").isEmpty() || request.getParameter("size").isEmpty() || request.getParameter("quantity").isEmpty())) {
            ProductDTO productDTO = productService.getProductByProductABSColourMainColourSecSize(
                    Integer.parseInt(request.getParameter("idProductAbs")),
                    Integer.parseInt(request.getParameter("colourMain")),
                    Integer.parseInt(request.getParameter("colourSec")),
                    request.getParameter("size"));

            cart.addCartItem(productDTO, Integer.parseInt(request.getParameter("quantity")));

            modelAndView.addObject("cart", cart);
        }

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




























/*    @RequestMapping(method = RequestMethod.GET,value = "/products")
    @ResponseBody
    public List<ProductAbs> findAll(@RequestParam(value = "search", required = false) String search){
        List<SearchCriteria> params = new ArrayList<SearchCriteria>();
        if (search != null) {
            Pattern pattern = Pattern.compile("(\\w+?)(:|<|>)(\\w+?),");
            Matcher matcher = pattern.matcher(search + ",");
            while (matcher.find()) {
                params.add(new SearchCriteria(matcher.group(1),
                        matcher.group(2), matcher.group(3)));
            }
        }
        return iProductDAOBaeldung.searchProduct(params);
    }*/

}
