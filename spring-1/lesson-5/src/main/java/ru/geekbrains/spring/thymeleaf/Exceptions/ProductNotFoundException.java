package ru.geekbrains.spring.thymeleaf.Exceptions;

public class ProductNotFoundException extends Exception {
    public ProductNotFoundException(String message) {
        super(message);
    }

    @Override
    public String toString() {
        return "ProductNotFoundException. Не удалось найти продукт в базе. Проверьте ID.";
    }
}
