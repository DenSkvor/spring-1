package ru.geekbrains.spring.thymeleaf;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;
import org.springframework.web.context.WebApplicationContext;
import ru.geekbrains.spring.thymeleaf.models.OrderItem;
import ru.geekbrains.spring.thymeleaf.models.Product;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


@Component
@Scope(value = WebApplicationContext.SCOPE_SESSION, proxyMode = ScopedProxyMode.TARGET_CLASS)
@Data
@NoArgsConstructor
public class Cart {

    private List<OrderItem> container;

    private Integer totalCost;

    @PostConstruct
    private void init(){
        container = new ArrayList<>();
    }

    public void add(Product p){
        for (OrderItem oi : container) {
            if(oi.getProduct().getId().equals(p.getId())){
                oi.incrementQuantity();
                calcTotalCost();
                return;
            }
        }
        container.add(new OrderItem(p));
        calcTotalCost();
    }

    public void add(Long id){
        for (OrderItem oi : container) {
            if(oi.getProduct().getId().equals(id)){
                oi.incrementQuantity();
                calcTotalCost();
                return;
            }
        }
    }

    public void remove(Long id){
        Iterator<OrderItem> iter = container.iterator();
        while (iter.hasNext()){
            OrderItem item = iter.next();
            if(item.getProduct().getId().equals(id)) {
                item.decrementQuantity();
                if(item.getQuantity() == 0) iter.remove();
                calcTotalCost();
                return;
            }
        }
    }

    public void delete(Long id){
        Iterator<OrderItem> iter = container.iterator();
        while (iter.hasNext()){
            OrderItem item = iter.next();
            if(item.getProduct().getId().equals(id)) {
                iter.remove();
                return;
            }
        }
    }

    public void calcTotalCost(){
        totalCost = 0;
        for (OrderItem oi : container) {
            totalCost += oi.getTotalCost();
        }
    }

    //Реализация инкремента и декремента количества продуктов внутри одной позиции в корзине (OrderItem).
    //Для того, чтобы можно было делать инкремент без лишнего обращения к базе, используется пергрузка метода add(id).
    public void changeQuantity(String[] editParam) {
        Long productID = Long.valueOf(editParam[0]);
        String editMethod = editParam[1];

        if(editMethod.equals("++")) add(productID);
        if(editMethod.equals("--")) remove(productID);
    }
}
