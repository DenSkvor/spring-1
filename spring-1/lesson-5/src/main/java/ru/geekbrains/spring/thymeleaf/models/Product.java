package ru.geekbrains.spring.thymeleaf.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;


@Entity
@Table(name = "product_tbl")
@Data
@NoArgsConstructor
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_id")
    private Long id;

    @Column(name = "product_title")
    private String title;

    @Column(name = "product_price")
    private Integer price;

    @ManyToOne
    @JoinColumn(name = "product_category")
    private Category category;

    @JsonIgnore
    @OneToMany(mappedBy = "product")
    List<OrderItem> orderItems;

}