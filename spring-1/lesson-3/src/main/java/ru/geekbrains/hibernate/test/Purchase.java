package ru.geekbrains.hibernate.test;

import javax.persistence.*;

@Entity
@Table(name = "purchase_tbl")
public class Purchase {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "purchase_id")
    private long id;

    @ManyToOne
    @JoinColumn(name = "client_id")
    private Client client;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

    @Column(name = "fix_price")//цена, фиксируемая на момент покупки
    private int fixPrice;

    public Purchase() {
    }

    public long getId() {
        return id;
    }

    public Client getClient() {
        return client;
    }

    public Product getProduct() {
        return product;
    }

    public int getFixPrice() {
        return fixPrice;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public void setFixPrice(int fixPrice) {
        this.fixPrice = fixPrice;
    }
}
