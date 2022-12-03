package com.rickotb.catalogstore.da;

import com.rickotb.catalogstore.bl.Json;
import com.rickotb.catalogstore.da.dao.CategoryDao;
import com.rickotb.catalogstore.da.dao.ProductDao;
import com.rickotb.catalogstore.da.entity.Category;
import com.rickotb.catalogstore.da.entity.Product;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;

/**
 * Клас реалізації CRUD для роботи з категоріями
 */
public class CategoryFunctionality {

    static CategoryDao categoryDao = new CategoryDao();
    static ProductDao productDao = new ProductDao();

    /**
     * Метод для виводу всіх категорій
     */
    public static void viewCatalogCategory(){
        List<Category> categoryList = categoryDao.getAll();
        try{
            if(categoryList.isEmpty()){
                throw new IOException();
            }
            for (int i = 0; i < categoryList.size(); i++){
                System.out.println("[" + i + "]" + ". Назва категорiї: " + categoryList.get(i).getName());
            }
        }catch (IOException e){
            System.out.println("Виникла помилка список пустий");
        }
    }

    /**
     * Метод додавання категорій
     */
    public static void createCategory(){
        List<Category> categoryList = categoryDao.getAll();

        try {
            System.out.println("Введiть назву категорiї: ");
            Scanner userChooseName = new Scanner(System.in,
                    Charset.forName( System.getProperty("os.name")
                            .toLowerCase().startsWith("win") ? "Windows-1251" : "UTF-8"));
            String nameCategory = userChooseName.nextLine();

            for(Category category: categoryList){
                if(category.getName().equals(nameCategory)){
                    System.out.println("Така категорiя вже iснує");
                    break;
                }else {
                    Category categoryAdd = new Category(nameCategory);
                    categoryDao.saveCategory(categoryAdd);
                    System.out.println("Категорiя додана");
                    break;
                }
            }

        }catch (Exception e){
            System.out.println("Виникла помилка");
        }
    }

    /**
     * Метод редагування категорій
     */
    public static void editCategory() {
        while (true) {
            try {
                viewCatalogCategory();
            } catch (Exception e) {
                System.out.println("Файл не знайдено");
                break;
            }
            try {
                System.out.println("Оберiть номер категорiї: ");
                Scanner chooseInputCategory = new Scanner(System.in);
                int userChoose = chooseInputCategory.nextInt();

                System.out.println("Введiть нову назву каталога: ");
                Scanner userChooseName = new Scanner(System.in,
                        Charset.forName( System.getProperty("os.name")
                                .toLowerCase().startsWith("win") ? "Windows-1251" : "UTF-8"));
                String nameCategory = userChooseName.nextLine();

                String oldName = Json.jsonCategoryList().get(userChoose).getName();

                Category category = new Category(nameCategory);
                categoryDao.updateCategory(category, userChoose);

                List<Product> productList = productDao.getAll();

                for (Product product: productList){
                    String categoryChange = product.getCategory();

                    if(Objects.equals(categoryChange, oldName)){
                        product.setCategory(nameCategory);
                    }
                }

                Json.productUpdate(productList);
                System.out.println("Категорiя оновлена");
                break;

            }catch (Exception e){
                System.out.println("Помилка, спробуйте ще раз");
                break;
            }
        }
    }

    /**
     * Метод видалення категорій
     */
    public static void deleteCategory(){
        while (true) {
            try {
                viewCatalogCategory();
            }
            catch (Exception e){
                System.out.println("Помилка, файл пустий");
                break;
            }
            System.out.print("Оберiть номер категорiї: ");
            Scanner userInput = new Scanner(System.in);
            try {
                int userChoice = userInput.nextInt();
                categoryDao.deleteCategory(userChoice);
                System.out.println("Категорiя видалена");
                break;
            } catch (Exception e) {
                System.out.println("Помилка, спробуйте ще раз");
                break;
            }
        }
    }
}
