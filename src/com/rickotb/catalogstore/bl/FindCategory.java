package com.rickotb.catalogstore.bl;

import com.rickotb.catalogstore.da.CategoryFunctionality;
import com.rickotb.catalogstore.da.dao.ProductDao;
import com.rickotb.catalogstore.da.entity.Product;
import com.rickotb.catalogstore.ui.Menu;

import java.nio.charset.Charset;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;

/**
 * Клас пошуку категорій
 */
public class FindCategory {

    static ProductDao productDao = new ProductDao();

    /**
     * Пошук категорії
     * @param userName Ім'я категорії
     */
    public static void findCategory(String userName) {

        List<Product> productList = productDao.getAll();

        try {
            CategoryFunctionality.viewCatalogCategory();
        } catch (Exception e) {
            System.out.println("Файл не знайдено");
            return;
        }

        try {
            System.out.println("Виберiть категорiю: ");
            Scanner idCategory = new Scanner(System.in);
            int categoryChoose = idCategory.nextInt();
            String nameCategory = Json.jsonCategoryList().get(categoryChoose).getName();

                for (Product product : productList) {
                    if (Objects.equals(product.getCategory(), nameCategory)) {
                        System.out.println("Назва категорiї: " + product.getName() + "\n" +
                                "Кiлькiсть на складi: " + product.getCount() + "\n" +
                                "Цiна: " + product.getPrice() + " $" + "\n" +
                                "Категорiя: " + product.getCategory());
                        System.out.println();
                    }
                }

            System.out.println("[1] Повернутись в меню");
            System.out.println("[2] Вибрати iншу категорiю");
            Scanner userChoose = new Scanner(System.in);
            int choose = userChoose.nextInt();
            if (choose == 1) {
                Menu.menuMain(userName);
            } else if (choose == 2) {
                findCategory(userName);
            }else {
                System.out.println("Вибрано невiрний пункт");
            }

        } catch (Exception e) {
            System.out.println("Категорiї не iснує");
            System.out.println("[1] Повернутись в меню");
            System.out.println("[2] Вибрати iншу категорiю");
            System.out.println("[3] Створити категорiю");
            Scanner userChoose = new Scanner(System.in);
            int choose = userChoose.nextInt();
            if (choose == 1) {
                Menu.menuMain(userName);
            } else if (choose == 2) {
                findCategory(userName);
            } else if (choose == 3){
                CategoryFunctionality.createCategory();
            }else{
                System.out.println("Вибрано невiрний пункт");
            }
        }
    }
}
