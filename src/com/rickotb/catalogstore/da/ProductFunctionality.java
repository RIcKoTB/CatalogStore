package com.rickotb.catalogstore.da;

import com.rickotb.catalogstore.bl.Json;
import com.rickotb.catalogstore.da.dao.ProductDao;
import com.rickotb.catalogstore.da.entity.Product;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.List;
import java.util.Scanner;

/**
 *  Клас реалізації CRUD для роботи з продуктами
 */
public class ProductFunctionality {

     static ProductDao productDao = new ProductDao();

    /**
     * Метод перегляду продуктів
     */
    public static void viewCatalogProduct() {
        List<Product> productList = productDao.getAll();
        try {
            if(productList.isEmpty()) {
                throw new IOException();
            }
            for (int i = 0; i < productList.size(); i++) {
                System.out.println("[" + i + "]" + ". Назва: " + productList.get(i).getName()
                        + "\n Цiна: " + productList.get(i).getPrice() + " $"
                        + "\n Кiлькiсть на складi: " + productList.get(i).getCount()
                        + "\n Категорiя: " + productList.get(i).getCategory());
                System.out.println();
            }
        }
        catch (IOException e){
            System.out.println("Виникла помилка список пустий");
        }
    }

    /**
     * Метод додавання продуктів
     */
    public static void createProduct(){
        List<Product> productList = productDao.getAll();

        try {
            System.out.println("Введiть назву: ");
            Scanner chooseNameGoods = new Scanner(System.in,
                    Charset.forName( System.getProperty("os.name")
                            .toLowerCase().startsWith("win") ? "Windows-1251" : "UTF-8"));
            String nameGoods = chooseNameGoods.nextLine();

            for(Product product: productList){
                if(product.getName().equals(nameGoods)){
                    System.out.println("Такий продукт вже iснує");
                    break;
                }else {
                    System.out.println("Введiть кiлькiсть яку додате на склад: ");
                    Scanner chooseCountGoods = new Scanner(System.in);
                    int countGoods = chooseCountGoods.nextInt();

                    System.out.println("Введiть цiну за одиницю: ");
                    Scanner choosePriceGoods = new Scanner(System.in);
                    double priceGoods = choosePriceGoods.nextDouble();

                    try {
                        CategoryFunctionality.viewCatalogCategory();
                    } catch (Exception e) {
                        System.out.println("Файл не знайдено");
                        return;
                    }

                    System.out.print("Оберiть категорiю: ");
                    Scanner chooseCategory = new Scanner(System.in);
                    int categoryChoice = chooseCategory.nextInt();

                    Product productAdd = new Product(nameGoods, countGoods, priceGoods, Json.jsonCategoryList().get(categoryChoice).getName());
                    productDao.saveProduct(productAdd);
                    System.out.println("Товар додано");
                    break;
                }
            }
        }catch (Exception e){
            System.out.println("Виникла помилка");
        }
    }

    /**
     * Метод редагування продуктів
     */
    public static void editProduct(){
        while (true) {
            try {
                viewCatalogProduct();
            }
            catch (Exception e){
                System.out.println("Файл не знайдено");
                break;
            }
            try {
                System.out.print("Оберiть номер товару: ");
                Scanner userInputGoodsNumber = new Scanner(System.in);
                int userChoiceNumber = userInputGoodsNumber.nextInt();

                System.out.print("Введiть нову назву: ");
                Scanner chooseNameGoods = new Scanner(System.in,
                        Charset.forName( System.getProperty("os.name")
                                .toLowerCase().startsWith("win") ? "Windows-1251" : "UTF-8"));
                String nameGoods = chooseNameGoods.nextLine();

                System.out.print("Введiть кiлькiсть яку додате на склад: ");
                Scanner chooseCountGoods = new Scanner(System.in);
                int countGoods = chooseCountGoods.nextInt();

                System.out.print("Введiть цiну одиницi: ");
                Scanner choosePriceGoods = new Scanner(System.in);
                int priceGoods = choosePriceGoods.nextInt();

                try {
                    CategoryFunctionality.viewCatalogCategory();
                } catch (Exception e) {
                    System.out.println("Файл не знайдено");
                    return;
                }

                System.out.print("Оберiть категорiю: ");
                Scanner chooseCategory = new Scanner(System.in,
                        Charset.forName( System.getProperty("os.name")
                                .toLowerCase().startsWith("win") ? "Windows-1251" : "UTF-8"));
                int categoryChoice = chooseCategory.nextInt();

                Product product = new Product(nameGoods, countGoods, priceGoods, Json.jsonCategoryList().get(categoryChoice).getName());
                productDao.updateProduct(product, userChoiceNumber);
                System.out.println("Товар оновлено");
                break;
            } catch (Exception e) {
                System.out.println("Помилка, спробуйте ще раз");
                break;
            }
        }
    }

    /**
     * Метод видалення продуктів
     */
    public static void deleteProduct(){
        while (true) {
            try {
                viewCatalogProduct();
            }
            catch (Exception e){
                System.out.println("Помилка, файл пустий");
                break;
            }
            System.out.print("Оберiть номер товару: ");
            Scanner userInput = new Scanner(System.in);
            try {
                int userChoice = userInput.nextInt();
                productDao.deleteProduct(userChoice);
                System.out.println("Товар видалено");
                break;
            } catch (Exception e) {
                System.out.println("Помилка, спробуйте ще раз");
                break;
            }
        }
    }
}
