package ru.geekbrains.spring.mvc;

public class ProductNotFoundException extends Exception {

    @Override
    public String toString() {
        return "ProductNotFoundException. Продукт не найден в базе. Проверьте ID.";
    }

}
