package com.rickotb.catalogstore.da.entity;

/**
 * Клас сутності продуктів
 */
public class Product {

    private String name;
    private int count;
    private double price;

    private String category;

    /**
     * Конструктор класа сутності продуктів
     * @param name Назва продукта
     * @param count Кількість товара на складі
     * @param price Ціна товара
     * @param category Назва категорії товара
     */
    public Product(String name, int count, double price, String category) {
        this.name = name;
        this.count = count;
        this.price = price;
        this.category = category;
    }

    public String getName() {
        return name;
    }

    public int getCount() {
        return count;
    }

    public double getPrice() {
        return price;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

}
