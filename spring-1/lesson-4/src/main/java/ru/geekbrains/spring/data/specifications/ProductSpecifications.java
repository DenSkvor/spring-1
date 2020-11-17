package ru.geekbrains.spring.data.specifications;

import org.springframework.data.jpa.domain.Specification;
import ru.geekbrains.spring.data.models.Product;



public class ProductSpecifications {

    public static Specification<Product> priceGTE(Integer minPrice){
        return (Specification<Product>) (root, criteriaQuery, criteriaBuilder) -> criteriaBuilder.greaterThanOrEqualTo(root.get("price"), minPrice);
    }

    public static Specification<Product> priceLTE(Integer maxPrice){
        return (Specification<Product>) (root, criteriaQuery, criteriaBuilder) -> criteriaBuilder.lessThanOrEqualTo(root.get("price"), maxPrice);
    }

}
