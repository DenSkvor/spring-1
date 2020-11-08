package ru.geekbrains.spring.mvc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class ProductController {

    private ProductService productService;
    @Autowired()
    private void setProductService(ProductService productService){
        this.productService = productService;
    }

    @GetMapping("/products")
    public String showProducts(Model model){
        List<Product> productList = productService.getProductList();
        model.addAttribute("productList", productList);
        return "product_list";
    }
/*
    @PostMapping("/add_product")
    public String addProduct(@RequestParam String title, @RequestParam int cost){
        productService.addProduct(title, cost);
        return "redirect:/products";
    }
*/
    @PostMapping("/add_product")
    public String addProduct(@ModelAttribute Product p){
        productService.addProduct(p.getTitle(), p.getCost());
        return "redirect:/products";
    }

    @GetMapping("/rmv_product/{id}")
    public String rmvProduct(@PathVariable int id){
        try {
            productService.removeProduct(id);
        } catch (ProductNotFoundException e) {
            e.printStackTrace();
        }
        return "redirect:/products";
    }
}
