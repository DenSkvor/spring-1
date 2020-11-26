package ru.geekbrains.spring.thymeleaf.models;

import lombok.Data;
import lombok.NoArgsConstructor;


import javax.persistence.*;

@Entity
@Table(name = "order_item_tbl")
@Data
@NoArgsConstructor
public class OrderItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_item_id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "order_item_product")
    private Product product;

    @Column(name = "order_item_quantity")
    Integer quantity;

    @Column(name = "order_item_product_price")
    Integer productPrice;

    @Column(name = "order_item_total_cost")
    Integer totalCost;

    public OrderItem(Product product){
        this.product = product;
        quantity = 1;
        this.productPrice = product.getPrice();
        totalCost = productPrice;
    }


    public void incrementQuantity() {
        quantity++;
        totalCost = quantity * productPrice;
    }

    public void decrementQuantity() {
        quantity--;
        totalCost = quantity * productPrice;
    }
}
