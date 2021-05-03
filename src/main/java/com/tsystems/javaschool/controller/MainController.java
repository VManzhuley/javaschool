package com.tsystems.javaschool.controller;

import com.tsystems.javaschool.baeldung.IProductDAOBaeldung;
import com.tsystems.javaschool.dao.PhotoDAO;
import com.tsystems.javaschool.dao.ProductAbsDAO;
import com.tsystems.javaschool.dto.ProductAbsDTO;
import com.tsystems.javaschool.entity.product.Category;
import com.tsystems.javaschool.service.CategoryService;
import com.tsystems.javaschool.service.ProductAbsService;
import com.tsystems.javaschool.service.ProductService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class MainController {

    private final ProductAbsService productAbsService;
    private final ProductAbsDAO productAbsDAO;
    private final IProductDAOBaeldung iProductDAOBaeldung;
    private final CategoryService categoryService;
    private final PhotoDAO photoDAO;
    private final ProductService productService;

    public MainController(ProductAbsService productAbsService, ProductAbsDAO productAbsDAO, IProductDAOBaeldung iProductDAOBaeldung, CategoryService categoryService, PhotoDAO photoDAO, ProductService productService) {
        this.productAbsService = productAbsService;
        this.productAbsDAO = productAbsDAO;

        this.iProductDAOBaeldung = iProductDAOBaeldung;
        this.categoryService = categoryService;
        this.photoDAO = photoDAO;
        this.productService = productService;
    }

    @RequestMapping(value = "/")
    public ModelAndView mainPage() {
        List<Category> categoryList = categoryService.findAll();
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("index");
        modelAndView.addObject("categoryList", categoryList);

        return modelAndView;
    }

    @RequestMapping(value = "product", method = RequestMethod.GET)
    public ModelAndView product(@RequestParam int id) {
        ProductAbsDTO productAbs = productAbsService.getProductAbsDTO(id);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("product");
        modelAndView.addObject("productAbs", productAbs);

        return modelAndView;
    }

    @RequestMapping(value = "shop", method = RequestMethod.GET)
    public ModelAndView shop(@RequestParam int category, @RequestParam(defaultValue = "1") int page, @RequestParam(defaultValue = "id") String sort){
        List<Category> categoryList = categoryService.findAll();
        List<ProductAbsDTO> productAbsList = productAbsService.allProductsByCategoryWithFSP(category,page,sort);
        int totalPages=productAbsService.getTotalPages();
        ModelAndView modelAndView=new ModelAndView();
        modelAndView.setViewName("shop");
        modelAndView.addObject("categoryList", categoryList);
        modelAndView.addObject("totalPages", totalPages);
        modelAndView.addObject("productAbsList", productAbsList);

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
