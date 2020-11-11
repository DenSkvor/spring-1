package ru.geekbrains.hibernate.test;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.List;

public class MainApp {

    public static void main(String[] args) {
        doHomeTasks();
    }


    public static void doHomeTasks(){
        try (SessionFactory sf = new Configuration().configure("hibernate.cfg.xml").buildSessionFactory()) {
            printClientPurchases(sf, 1L);
            System.out.println("-------------");
            printProductBuyers(sf, 2L);
            System.out.println("-------------");
            removeProduct(sf, 6L);
            System.out.println("-------------");
            removeClient(sf, 5L);
        }
    }

    public static void printClientPurchases(SessionFactory sf, long clientID){
        Session session = sf.getCurrentSession();

        session.beginTransaction();
        Client client = session.get(Client.class, clientID);
        try {
            System.out.println("Клиент:\n" + client.getName());
        }catch (NullPointerException e){
            System.out.println("Клиент с таким ID не найден.");
            session.close();
            return;
        }
        List<Purchase> clientPurchases = client.getPurchases();
        System.out.println("Покупки:");
        for (Purchase p : clientPurchases) {
            System.out.printf("Товар - %s; Цена нынешняя - %d; Цена на момент покупки - %d %n", p.getProduct().getTitle(), p.getProduct().getPrice(), p.getFixPrice());
        }
        session.getTransaction().commit();
    }

    public static void printProductBuyers(SessionFactory sf, long productID){
        Session session = sf.getCurrentSession();

        session.beginTransaction();
        Product product = session.get(Product.class, productID);
        try {
            System.out.printf("Товар - %s; Нынешнаяя цена - %d %n", product.getTitle(), product.getPrice());
        }catch (NullPointerException e){
            System.out.println("Товар с таким ID не найден.");
            session.close();
            return;
        }
        List<Purchase> purchases = product.getPurchases();
        System.out.println("Покупатели:");
        for (Purchase p : purchases) {
            System.out.printf("Имя - %s; Цена товара на момент покупки - %d %n", p.getClient().getName(), p.getFixPrice());
        }
        session.getTransaction().commit();
    }

    public static void removeProduct(SessionFactory sf, long productID){
        Session session = sf.getCurrentSession();

        session.beginTransaction();
        Product productToDel = session.get(Product.class, productID);
        try{
            System.out.printf("Товар на удаление: id - %d; наименование - %s; цена - %d %n", productToDel.getId(), productToDel.getTitle(), productToDel.getPrice());
        }catch (NullPointerException e){
            System.out.println("Товар с таким ID не найден.");
            session.close();
            return;
        }
        session.remove(productToDel);
        System.out.println("Товар удален из базы.");
        session.getTransaction().commit();
    }

    public static void removeClient(SessionFactory sf, long clientID){
        Session session = sf.getCurrentSession();

        session.beginTransaction();
        Client clientToDel = session.get(Client.class, clientID);
        try{
            System.out.printf("Клиент на удаление: id - %d; имя - %s %n", clientToDel.getId(), clientToDel.getName());
        }catch (NullPointerException e){
            System.out.println("Клиент с таким ID не найден.");
            session.close();
            return;
        }
        session.remove(clientToDel);
        System.out.println("Клиент удален из базы.");
        session.getTransaction().commit();
    }

}
