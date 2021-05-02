package com.tsystems.javaschool.controller;

import com.tsystems.javaschool.baeldung.IProductDAOBaeldung;
import com.tsystems.javaschool.dao.ProductAbsDAO;
import com.tsystems.javaschool.dto.ProductAbsDTO;
import com.tsystems.javaschool.entity.product.Category;
import com.tsystems.javaschool.model.ProductAbsInfo;
import com.tsystems.javaschool.service.CategoryService;
import com.tsystems.javaschool.service.ProductAbsService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class MainController {
    private final ProductAbsService productAbsService;
    private final ProductAbsDAO productAbsDAO;
    private final IProductDAOBaeldung iProductDAOBaeldung;
    private final CategoryService categoryService;

    public MainController(ProductAbsService productAbsService, ProductAbsDAO productAbsDAO, IProductDAOBaeldung iProductDAOBaeldung, CategoryService categoryService) {
        this.productAbsService = productAbsService;
        this.productAbsDAO = productAbsDAO;

        this.iProductDAOBaeldung = iProductDAOBaeldung;
        this.categoryService = categoryService;
    }

    @GetMapping(value = "/")
    public ModelAndView allProducts() {
        List<Category> categoryList = categoryService.findAll();
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("index");
        modelAndView.addObject("categoryList", categoryList);

        return modelAndView;
    }

    @RequestMapping(value = "/product/{id}", method = RequestMethod.GET)
    public ModelAndView product(@PathVariable("id") int id) {
        ProductAbsInfo productAbsInfo = productAbsService.getProductAbsInfo(id);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("good");

        modelAndView.addObject("productAbsInfo", productAbsInfo);
        return modelAndView;
    }

    @RequestMapping(value = "category/{id}", method = RequestMethod.GET)
    public ModelAndView category(@PathVariable("id") int id, @RequestParam(defaultValue = "1") int page, @RequestParam(defaultValue = "id") String sort){
        List<Category> categoryList = categoryService.findAll();
        List<ProductAbsDTO> productAbsList = productAbsService.allProductsByCategoryWithFSP(id,page,sort);
        ModelAndView modelAndView=new ModelAndView();
        modelAndView.setViewName("category");
        modelAndView.addObject("categoryList", categoryList);
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
