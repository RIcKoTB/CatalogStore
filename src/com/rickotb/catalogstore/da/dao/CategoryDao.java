package com.rickotb.catalogstore.da.dao;

import com.google.gson.Gson;
import com.rickotb.catalogstore.bl.Json;
import com.rickotb.catalogstore.da.entity.Category;

import java.io.IOException;
import java.nio.file.Path;
import java.util.List;
import java.util.Optional;

/**
 * Клас роботи з категоріями
 */
public class CategoryDao implements DaoForCategory<Category>{

    private List<Category> categoryList;

    public CategoryDao(){
        categoryList = Json.jsonCategoryList();
    }

    @Override
    public Optional<Category> get(int id) {
        return Optional.empty();
    }
    @Override
    public List<Category> getAll() {
        return categoryList;
    }

    Path path = Path.of("resource","Category.json");

    /**
     * Метод збереження категорій
     * @param category Екземпляр класа категорій
     */
    public void saveCategory(Category category){
        categoryList.add(category);
        Gson gson = new Gson();
        String jsonForCategory = gson.toJson(categoryList);
        try{
            Json.writeString(path, jsonForCategory);
        }
        catch (IOException e){
            System.out.println("Помилка: " + e.getMessage());
        }
    }

    /**
     * Метод оновлення категорій
     * @param category Екземпляр класа категорій
     * @param userChoice Вибір користувача по id категорії
     */
    public void updateCategory(Category category, int userChoice){
        categoryList.set(userChoice, category);
        Gson gson = new Gson();
        String jsonForCategory = gson.toJson(categoryList);
        try{
            Json.writeString(path, jsonForCategory);
        }
        catch (IOException e){
            System.out.println("Помилка: " + e.getMessage());
        }
    }

    /**
     * Метод видалення категорій
     * @param userChoice Вибір категорії яка видалиться по id
     */
    public void deleteCategory(int userChoice) {
        Path path = Path.of("resource","Category.json");
        categoryList.remove(userChoice);
        Gson gson = new Gson();
        String jsonForCategory = gson.toJson(categoryList);
        try{
            Json.writeString(path, jsonForCategory);
        }
        catch (IOException e){
            System.out.println("Помилка: " + e.getMessage());
        }
    }
}
