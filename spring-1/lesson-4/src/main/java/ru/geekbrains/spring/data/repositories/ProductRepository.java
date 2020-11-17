package ru.geekbrains.spring.data.repositories;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.geekbrains.spring.data.models.Product;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long>, JpaSpecificationExecutor<Product> {

    List<Product> findAllByPriceGreaterThanEqual(Integer minPrice, Pageable pageable);

    List<Product> findAllByPriceLessThanEqual(Integer maxPrice, Pageable pageable);

    List<Product> findAllByPriceBetween(Integer minPrice, Integer maxPrice, Pageable pageable);
    List<Product> findAllByPriceBetweenOrderByPriceAsc(Integer minPrice, Integer maxPrice, Pageable pageable);
    List<Product> findAllByPriceBetweenOrderByPriceDesc(Integer minPrice, Integer maxPrice, Pageable pageable);
}
