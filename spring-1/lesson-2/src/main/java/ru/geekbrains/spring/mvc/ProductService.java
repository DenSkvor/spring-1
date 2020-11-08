package ru.geekbrains.spring.mvc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    private ProductRepository productRepository;

    @Autowired
    private void setProductRepository(ProductRepository productRepository){
        this.productRepository = productRepository;
    }

    public List<Product> getProductList(){
        return productRepository.getProductList();
    }

    public void addProduct(String title, int cost){
        productRepository.addProduct(title, cost);
    }

    public void removeProduct(int id) throws ProductNotFoundException {
        productRepository.removeProduct(id);
    }
}
