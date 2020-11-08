package ru.geekbrains.spring.mvc;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Repository
public class ProductRepository {

    @Value("1")
    private int productID;

    private List<Integer> freeID;//временное хранилище айдишников удаленных товаров для дальнейшего использования при добавлении новых товаров

    private List<Product> productList;

    @PostConstruct
    private void setup() {
        freeID = new ArrayList<>();
        productList = new ArrayList<>();
        addProduct("Клавиатура", 2000);
        addProduct("Мышь", 1500);
        addProduct("Коврик", 1000);
    }

    public void addProduct(String title, int cost) {
        if(freeID.isEmpty()) {
            productList.add(new Product(productID, title, cost));
            productID++;
        } else {
            productList.add(new Product(freeID.get(0), title, cost));
            freeID.remove(0);
        }
    }

    public void removeProduct(int id) throws ProductNotFoundException {
        for (Product p : productList) {
            if (p.getId() == id) {
                productList.remove(p);
                if(id == productID - 1) productID--;
                else {
                    freeID.add(id);
                    freeID.sort((id1, id2) -> (id1 - id2));
                }
                return;
            }
        }
        throw new ProductNotFoundException();
    }

    public List<Product> getProductList() {
        productList.sort((p1, p2) -> (p1.getId() - p2.getId()));
        return Collections.unmodifiableList(productList);
    }
}
