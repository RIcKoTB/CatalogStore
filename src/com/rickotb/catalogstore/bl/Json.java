package com.rickotb.catalogstore.bl;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.rickotb.catalogstore.da.entity.Category;
import com.rickotb.catalogstore.da.entity.Product;
import com.rickotb.catalogstore.da.entity.User;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Клас роботи з Json
 */
public class Json {
    static Gson gson = new Gson();

    /**
     * Зчитування json
     * @param path шлях до файлу
     * @return Зчитаний файл
     * @throws IOException Ігнорування помилки при роботі з файлами
     */
    public static String readString(Path path) throws IOException {
        return Files.readString(path);
    }

    /**
     * Запис у файл
     * @param path Шлях до файлу
     * @param data Строка яка записується
     * @throws IOException Ігнорування помилки при роботі з файлами
     */
    public static void writeString(Path path, String data) throws IOException {
        Files.writeString(path, data, StandardOpenOption.TRUNCATE_EXISTING,
                StandardOpenOption.WRITE, StandardOpenOption.CREATE);
    }

    /**
     * Запис файлу початковими даними
     * @return Повертає файл з записаним продуктом
     */
    private static String productToJson(){
        Product product = new Product("Morshinska",12, 20, "Weather");
        Gson gson = new Gson();
        List<Product> productList = new ArrayList<>();
        productList.add(product);
        return gson.toJson(productList);
    }

    /**
     * Запис файлу початковими даними
     * @return Повертає файл з записаним категоріями
     */
    private static String categoryToJson(){
        Category category = new Category("Weather");
        Gson gson = new Gson();
        List<Category> categoryList = new ArrayList<>();
        categoryList.add(category);
        return gson.toJson(categoryList);
    }

    /**
     * Отримання списку продуктів
     * @return Список продуктів
     */
    public static List<Product> jsonProductList(){
        Path pathToFile = Path.of("resource", "ListProduct.json");
        String jsonToArray = null;
        try {
            if(readString(pathToFile).length() == 0){
                writeString(pathToFile, productToJson());
            }
            jsonToArray = readString(pathToFile);
        }
        catch (IOException e){
            System.out.println("Error is: " + e.getMessage());
        }

        return gson.fromJson(jsonToArray, new TypeToken<List<Product>>() {}.getType());
    }

    /**
     * Отримання списку категорій
     * @return Список категорій
     */
    public static List<Category> jsonCategoryList(){
        Path pathToFile = Path.of("resource", "Category.json");
        String jsonToArray = null;
        try {
            if(readString(pathToFile).length() == 0){
                writeString(pathToFile, categoryToJson());
            }
            jsonToArray = readString(pathToFile);
        }
        catch (IOException e){
            System.out.println("Error is: " + e.getMessage());
        }
        Category[] categories = gson.fromJson(jsonToArray, Category[].class);
        return new ArrayList<>(Arrays.asList(categories));
    }

    /**
     * Отримання списку користувачів
     * @return Список корисутвачів
     */
    public static List<User> jsonUserList(){
        Path pathToFile = Path.of("resource", "User.json");
        String jsonToArray = null;
        try {
            if(readString(pathToFile).length() == 0){
                writeString(pathToFile, productToJson());
            }
            jsonToArray = readString(pathToFile);
        }
        catch (IOException e){
            System.out.println("Error is: " + e.getMessage());
        }
        User[] users = gson.fromJson(jsonToArray, User[].class);
        return new ArrayList<>(Arrays.asList(users));
    }

    /**
     * Додавання користувачів
     * @param userList Колекція користувачів
     */
    public static void userAdd(List<User> userList){
        Path pathToFile = Path.of("resource", "User.json");
        Gson gson = new Gson();
        String usersToStr = gson.toJson(userList);
        try {
            writeString(pathToFile, usersToStr);
        }
        catch (IOException e){
            System.out.println("Помилка" + e.getMessage());
        }
    }

    /**
     * Оновлення продуктів
     * @param productList Колекція продуктів
     */
    public static void productUpdate(List<Product> productList){
        Path pathToFile = Path.of("resource", "ListProduct.json");
        Gson gson = new Gson();
        String productToStr = gson.toJson(productList);
        try {
            writeString(pathToFile, productToStr);
        }
        catch (IOException e){
            System.out.println("Помилка" + e.getMessage());
        }
    }
}
