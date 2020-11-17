package ru.geekbrains.spring.data.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.geekbrains.spring.data.services.ProductService;

@Controller
@RequestMapping("/products")
public class ProductController {

    private ProductService productService;
    @Autowired
    public void setProductService(ProductService productService) {
        this.productService = productService;
    }


    @GetMapping("/{sort}")
    public String showAllProducts(Model model,
                                  @PathVariable() String sort,
                                  @RequestParam(defaultValue = "1") Integer pageNum,
                                  @RequestParam(defaultValue = "10") Integer pageSize){
        if (pageNum < 0) pageNum = 0;
        if (pageSize < 1) pageSize = 1;

        model.addAttribute("productList", productService.getAllProducts(pageNum - 1, pageSize, sort));

        return "products";
    }

    @GetMapping("/{sort}/filter_price")
    public String showProductsWithPriceFilters(Model model,
                                               @PathVariable() String sort,
                                               @RequestParam(required = false) Integer minPrice,
                                               @RequestParam(required = false) Integer maxPrice,
                                               @RequestParam(defaultValue = "1") Integer pageNum,
                                               @RequestParam(defaultValue = "10") Integer pageSize){
        if (pageNum < 0) pageNum = 0;
        if (pageSize < 1) pageSize = 1;

        if (minPrice != null && maxPrice == null) {
            model.addAttribute("productList", productService.getProductsByPriceGTE(minPrice, pageNum - 1, pageSize, sort));
        }
        if (minPrice == null && maxPrice != null) {
            model.addAttribute("productList", productService.getProductsByPriceLTE(maxPrice, pageNum - 1, pageSize, sort));
        }
        if (minPrice != null && maxPrice != null) {
            model.addAttribute("productList", productService.getProductsByPriceBetween(minPrice, maxPrice, pageNum - 1, pageSize, sort));
        }
        if (minPrice == null && maxPrice == null)
            return "redirect:/products/" + sort + "?pageNum=" + pageNum + "&pageSize=" + pageSize;

        return "products";
    }

//через спецификации
    @GetMapping("/spec/{sort}")
    public String showProductsWithPriceFiltersSpec(Model model,
                                               @PathVariable() String sort,
                                               @RequestParam(required = false) Integer minPrice,
                                               @RequestParam(required = false) Integer maxPrice,
                                               @RequestParam(defaultValue = "1") Integer pageNum,
                                               @RequestParam(defaultValue = "10") Integer pageSize){
        if (pageNum < 0) pageNum = 0;
        if (pageSize < 1) pageSize = 1;

        model.addAttribute("productList", productService.getProducts(minPrice, maxPrice, pageNum - 1, pageSize, sort));
        return "products";
    }

}
