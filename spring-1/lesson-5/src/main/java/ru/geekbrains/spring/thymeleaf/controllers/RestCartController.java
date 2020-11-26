package ru.geekbrains.spring.thymeleaf.controllers;


import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.geekbrains.spring.thymeleaf.Cart;
import ru.geekbrains.spring.thymeleaf.exceptions.ProductNotFoundException;
import ru.geekbrains.spring.thymeleaf.models.OrderItem;
import ru.geekbrains.spring.thymeleaf.services.ProductService;

import java.util.List;

@RestController
@RequestMapping("/api/v1/cart")
@AllArgsConstructor
public class RestCartController {

    private ProductService productService;
    private Cart cart;

    @GetMapping
    public List<OrderItem> showCart(){
        return cart.getContainer();
    }

    @PostMapping("/{id}")
    public List<OrderItem> addProduct(@PathVariable Long id) throws ProductNotFoundException {
        cart.add(productService.getProductById(id));
        return cart.getContainer();
    }

    @PutMapping()
    public List<OrderItem> changeProductQuantity(@RequestBody String[] editParam) {
        //ожидаем с фронта массив вида [productId, "++ либо --"]
        cart.changeQuantity(editParam);
        return cart.getContainer();
    }

    @DeleteMapping("/{id}")
    public List<OrderItem> delProduct(@PathVariable Long id){
        cart.delete(id);
        return cart.getContainer();
    }

}
