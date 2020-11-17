package ru.geekbrains.spring.data.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import ru.geekbrains.spring.data.models.Product;
import ru.geekbrains.spring.data.repositories.ProductRepository;
import ru.geekbrains.spring.data.specifications.ProductSpecifications;

import java.util.List;

@Service
public class ProductService {

    private ProductRepository productRepository;
    @Autowired
    public void setProductRepository(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }


    public Page<Product> getAllProducts(int pageNum, int pageSize, String sort){
        if(sort.equals("priceAsc")) return productRepository.findAll(PageRequest.of(pageNum, pageSize, Sort.by("price").ascending()));
        if(sort.equals("priceDesc")) return productRepository.findAll(PageRequest.of(pageNum, pageSize, Sort.by("price").descending()));
        return productRepository.findAll(PageRequest.of(pageNum, pageSize));
    }


    public List<Product> getProductsByPriceGTE(Integer minPrice, Integer pageNum, Integer pageSize, String sort){
        if(sort.equals("priceAsc")) return productRepository.findAllByPriceGreaterThanEqual(minPrice, PageRequest.of(pageNum, pageSize, Sort.by("price").ascending()));
        if(sort.equals("priceDesc")) return productRepository.findAllByPriceGreaterThanEqual(minPrice, PageRequest.of(pageNum, pageSize, Sort.by("price").descending()));
        return productRepository.findAllByPriceGreaterThanEqual(minPrice, PageRequest.of(pageNum, pageSize));
    }


    public List<Product> getProductsByPriceLTE(Integer maxPrice, Integer pageNum, Integer pageSize, String sort){
        if(sort.equals("priceAsc")) return productRepository.findAllByPriceLessThanEqual(maxPrice, PageRequest.of(pageNum, pageSize, Sort.by("price").ascending()));
        if(sort.equals("priceDesc")) return productRepository.findAllByPriceLessThanEqual(maxPrice, PageRequest.of(pageNum, pageSize, Sort.by("price").descending()));
        return productRepository.findAllByPriceLessThanEqual(maxPrice, PageRequest.of(pageNum, pageSize));
    }

    //здесь ради практики сделал сортировку через методы, предоставляеые репозиторием, а не через page
    public List<Product> getProductsByPriceBetween(Integer minPrice, Integer maxPrice, Integer pageNum, Integer pageSize, String sort){
        if(sort.equals("priceAsc")) return productRepository.findAllByPriceBetweenOrderByPriceAsc(minPrice, maxPrice, PageRequest.of(pageNum, pageSize));
        if(sort.equals("priceDesc")) return productRepository.findAllByPriceBetweenOrderByPriceDesc(minPrice, maxPrice, PageRequest.of(pageNum, pageSize));
        return productRepository.findAllByPriceBetween(minPrice, maxPrice, PageRequest.of(pageNum, pageSize));
    }


// через спецификации
    public Page<Product> getProducts(Integer minPrice, Integer maxPrice, Integer pageNum, Integer pageSize, String sort){
        Specification<Product> spec = Specification.where(null);
        if(minPrice != null) spec = spec.and(ProductSpecifications.priceGTE(minPrice));
        if(maxPrice != null) spec = spec.and(ProductSpecifications.priceLTE(maxPrice));

        if(sort.equals("priceAsc")) return productRepository.findAll(spec, PageRequest.of(pageNum, pageSize, Sort.by("price").ascending()));
        if(sort.equals("priceDesc")) return productRepository.findAll(spec, PageRequest.of(pageNum, pageSize, Sort.by("price").descending()));
        return productRepository.findAll(spec, PageRequest.of(pageNum, pageSize));
    }

}
