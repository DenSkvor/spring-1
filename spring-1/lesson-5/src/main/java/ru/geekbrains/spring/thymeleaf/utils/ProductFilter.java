package ru.geekbrains.spring.thymeleaf.utils;

import lombok.Getter;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import ru.geekbrains.spring.thymeleaf.models.Product;
import ru.geekbrains.spring.thymeleaf.repositories.Specifications.ProductSpecification;

import java.util.Map;

@Getter
public class ProductFilter {

    private Specification<Product> spec;

    private String filter;

    public ProductFilter(Map<String, String> params){
        spec = Specification.where(null);
        StringBuilder tempFilter = new StringBuilder();
        //по-хорошему вместо проверки !isEmpty нужна проверка !isBlank, но у меня устаовлена 8 версия джавы, поэтому увы.
        if(params.containsKey("minPrice") && !params.get("minPrice").isEmpty()) {
            spec = spec.and(ProductSpecification.findByPriceGTE(Integer.parseInt(params.get("minPrice"))));
            tempFilter.append("&minPrice=" + Integer.parseInt(params.get("minPrice")));

        }
        if(params.containsKey("maxPrice") && !params.get("maxPrice").isEmpty()) {
            spec = spec.and(ProductSpecification.findByPriceLTE(Integer.parseInt(params.get("maxPrice"))));
            tempFilter.append("&maxPrice=" + Integer.parseInt(params.get("maxPrice")));
        }
        if(params.containsKey("title") && !params.get("title").isEmpty()) {
            spec = spec.and(ProductSpecification.findByTitleLike(params.get("title")));
            tempFilter.append("&title=" + params.get("title"));
        }
        filter = tempFilter.toString();
    }
}
