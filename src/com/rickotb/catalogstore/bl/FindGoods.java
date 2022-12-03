package com.rickotb.catalogstore.bl;

import com.rickotb.catalogstore.da.ProductFunctionality;
import com.rickotb.catalogstore.da.dao.ProductDao;
import com.rickotb.catalogstore.da.entity.Product;
import com.rickotb.catalogstore.ui.Menu;

import java.nio.charset.Charset;
import java.util.List;
import java.util.Scanner;

/**
 * Пошук продуктів
 */
public class FindGoods {
    static ProductDao productDao = new ProductDao();

    /**
     * Пошук продуктів
     * @param userName Назва продукта
     */
    public static void findGoods(String userName){
        List<Product> productList = productDao.getAll();
        try {
            System.out.println("Введiть назву товару: ");
            Scanner nameGoods = new Scanner(System.in,
                    Charset.forName( System.getProperty("os.name")
                            .toLowerCase().startsWith("win") ? "Windows-1251" : "UTF-8"));
            String goods = nameGoods.nextLine();

            for (Product product: productList) {
                if (product.getName().equals(goods)) {
                    System.out.println("Назва категорiї: " + product.getName() + "\n" +
                            "Кiлькiсть на складi: " + product.getCount() + "\n" +
                            "Цiна: " + product.getPrice() + " $" + "\n" +
                            "Категорiя: " + product.getCategory());
                            System.out.println();
                }
            }

            System.out.println("[1] Повернутись в меню");
            System.out.println("[2] Знайти iнший товар");
            Scanner userChoose = new Scanner(System.in);
            int choose = userChoose.nextInt();
            if (choose == 1) {
                Menu.menuMain(userName);
            } else if (choose == 2) {
                findGoods(userName);
            }

        }catch (Exception e){
            System.out.println("Такого товару не iснує.");
            System.out.println("[1] Додати товар");
            System.out.println("[2] Повернутись в меню");
            Scanner userChoose = new Scanner(System.in);
            int choose = userChoose.nextInt();
            if(choose == 1){
                ProductFunctionality.createProduct();
            }else if (choose == 2){
                Menu.menuMain(userName);
            }else{
                System.out.println("Введено не вiрнi данi");
            }
        }
    }
}
