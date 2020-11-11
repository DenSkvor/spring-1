package ru.geekbrains.hibernate.test;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "client_tbl")
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "client_id")
    private long id;

    @Column(name = "client_name")
    private String name;

    @OneToMany(mappedBy = "client")
    private List<Purchase> purchases;


    public Client() {
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public List<Purchase> getPurchases() {
        return purchases;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPurchases(List<Purchase> purchases) {
        this.purchases = purchases;
    }

    @Override
    public String toString() {
        return "Client{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
