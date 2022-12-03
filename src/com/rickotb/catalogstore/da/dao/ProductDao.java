package com.rickotb.catalogstore.da.dao;

import com.google.gson.Gson;
import com.rickotb.catalogstore.bl.Json;
import com.rickotb.catalogstore.da.entity.Product;

import java.io.IOException;
import java.nio.file.Path;
import java.util.List;
import java.util.Optional;

/**
 * Клас роботи з продуктами
 */
public class ProductDao implements Dao<Product> {

    private List<Product> productList;

    public ProductDao(){
        productList = Json.jsonProductList();
    }

    @Override
    public Optional<Product> get(int id) {
        return Optional.ofNullable(productList.get((int) id));
    }

    @Override
    public List<Product> getAll() {
        return productList;
    }

    Path path = Path.of("resource","ListProduct.json");

    /**
     * Збереження продуктів
     * @param product Екземпляр класа продуктів
     */
    public void saveProduct(Product product){
        productList.add(product);
        Gson gson = new Gson();
        String jsonForProduct = gson.toJson(productList);
        try{
            Json.writeString(path, jsonForProduct);
        }
        catch (IOException e){
            System.out.println("Помилка: " + e.getMessage());
        }
    }

    /**
     * Метод обновлення продуктів
     * @param product Екземпляр класа продуктів
     * @param userChoice Продукт який вибирається по id
     */
    public void updateProduct(Product product, int userChoice){
        productList.set(userChoice, product);
        Gson gson = new Gson();
        String jsonForProduct = gson.toJson(productList);
        try{
            Json.writeString(path, jsonForProduct);
        }
        catch (IOException e){
            System.out.println("Помилка: " + e.getMessage());
        }
    }

    /**
     * Метод видалення продукта
     * @param userChoice Продукт по id який треба видаляти
     */
    public void deleteProduct(int userChoice) {
        Path path = Path.of("resource","ListProduct.json");
        productList.remove(userChoice);
        Gson gson = new Gson();
        String jsonForProduct = gson.toJson(productList);
        try{
            Json.writeString(path, jsonForProduct);
        }
        catch (IOException e){
            System.out.println("Помилка: " + e.getMessage());
        }
    }
}
