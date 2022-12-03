package com.rickotb.catalogstore.ui;

import com.rickotb.catalogstore.bl.*;
import com.rickotb.catalogstore.da.CategoryFunctionality;
import com.rickotb.catalogstore.da.ProductFunctionality;

import java.util.Scanner;

/**
 * Клас меню
 */
public class Menu {
    /**
     * Метод меню який викликається при самому старті програми
     */
    public static void startMenu() {
        int menuChoice = 0;
        Scanner choice = new Scanner(System.in);
        do {
            System.out.println();
            System.out.println("Вiтаємо в програмi каталогу продуктового магазину");
            System.out.println(
                            "░██████╗░██████╗░░█████╗░░█████╗░███████╗██████╗░██╗░░░██╗  ░██████╗████████╗░█████╗░██████╗░███████╗\n" +
                            "██╔════╝░██╔══██╗██╔══██╗██╔══██╗██╔════╝██╔══██╗╚██╗░██╔╝  ██╔════╝╚══██╔══╝██╔══██╗██╔══██╗██╔════╝\n" +
                            "██║░░██╗░██████╔╝██║░░██║██║░░╚═╝█████╗░░██████╔╝░╚████╔╝░  ╚█████╗░░░░██║░░░██║░░██║██████╔╝█████╗░░\n" +
                            "██║░░╚██╗██╔══██╗██║░░██║██║░░██╗██╔══╝░░██╔══██╗░░╚██╔╝░░  ░╚═══██╗░░░██║░░░██║░░██║██╔══██╗██╔══╝░░\n" +
                            "╚██████╔╝██║░░██║╚█████╔╝╚█████╔╝███████╗██║░░██║░░░██║░░░  ██████╔╝░░░██║░░░╚█████╔╝██║░░██║███████╗\n" +
                            "░╚═════╝░╚═╝░░╚═╝░╚════╝░░╚════╝░╚══════╝╚═╝░░╚═╝░░░╚═╝░░░  ╚═════╝░░░░╚═╝░░░░╚════╝░╚═╝░░╚═╝╚══════╝");
            System.out.println("[1] Авторизацiя");
            System.out.println("[2] Реєстрацiя");
            System.out.println("[3] Перегляд списку товарiв");
            System.out.println("[4] Вихiд");
            menuChoice = choice.nextInt();
            switch (menuChoice) {
                case 1:
                    Authorization.authorization();
                    break;
                case 2:
                    Registration.registration();
                    break;
                case 3:
                    try {
                        ProductFunctionality.viewCatalogProduct();
                    }
                    catch (Exception e){
                        System.out.println("Помилка, файл пустий");
                    }
                    break;
                case 4:
                    System.out.println("Дякуємо що скористалися нашими послугами");
                    System.exit(4);
                    break;
            }
            if(menuChoice > 4){
                System.out.println();
                System.out.println("Введено хибнi данi");
            }
        }while (menuChoice != 4) ;
    }

    /**
     * Метод основного меню
     * @param userName Ім'я користувача під яким увійшли в програму
     */
    public static void menuMain(String userName){
        int mainMenu;
        Scanner mainInput = new Scanner(System.in);
        do{
            System.out.println();
            System.out.println("Вiтаємо в програмi каталогу продуктового магазину!         "
                    + "\t" + " Ви авторизувалися як: " + userName);
            System.out.println(
                    "░██████╗░██████╗░░█████╗░░█████╗░███████╗██████╗░██╗░░░██╗  ░██████╗████████╗░█████╗░██████╗░███████╗\n" +
                    "██╔════╝░██╔══██╗██╔══██╗██╔══██╗██╔════╝██╔══██╗╚██╗░██╔╝  ██╔════╝╚══██╔══╝██╔══██╗██╔══██╗██╔════╝\n" +
                    "██║░░██╗░██████╔╝██║░░██║██║░░╚═╝█████╗░░██████╔╝░╚████╔╝░  ╚█████╗░░░░██║░░░██║░░██║██████╔╝█████╗░░\n" +
                    "██║░░╚██╗██╔══██╗██║░░██║██║░░██╗██╔══╝░░██╔══██╗░░╚██╔╝░░  ░╚═══██╗░░░██║░░░██║░░██║██╔══██╗██╔══╝░░\n" +
                    "╚██████╔╝██║░░██║╚█████╔╝╚█████╔╝███████╗██║░░██║░░░██║░░░  ██████╔╝░░░██║░░░╚█████╔╝██║░░██║███████╗\n" +
                    "░╚═════╝░╚═╝░░╚═╝░╚════╝░░╚════╝░╚══════╝╚═╝░░╚═╝░░░╚═╝░░░  ╚═════╝░░░░╚═╝░░░░╚════╝░╚═╝░░╚═╝╚══════╝");
            System.out.println("[1] Переглянути товар на складi");
            System.out.println("[2] Категорiї");
            System.out.println("[3] Товари");
            System.out.println("[4] Знайти категорiю товарiв");
            System.out.println("[5] Знайти товар");
            System.out.println("[6] Вийти з аккаунту");
            System.out.println("[7] Вихiд");
            mainMenu = mainInput.nextInt();
            switch (mainMenu){
                case 1:
                    try {
                        ProductFunctionality.viewCatalogProduct();
                    }
                    catch (Exception e){
                        System.out.println("Помилка, файл пустий");
                    }
                    break;
                case 2:
                    menuCategory(userName);
                    break;
                case 3:
                    menuProduct(userName);
                    break;
                case 4:
                    FindCategory.findCategory(userName);
                    break;
                case 5:
                    FindGoods.findGoods(userName);
                    break;
                case 6:
                    Logout.logout();
                    break;
                case 7:
                    System.out.println("Дякуємо що скористалися нашими послугами");
                    System.exit(7);
                    break;
            }
            if(mainMenu > 7){
                System.out.println();
                System.out.println("Введено хибнi данi");
            }
        }while (mainMenu != 7);
    }

    /**
     * Меню роботи з продуктами
     * @param userName Ім'я користувача під яким увійшли в програму
     */
    public static void menuProduct(String userName){
        int mainMenu;
        Scanner mainInput = new Scanner(System.in);
        do{
            System.out.println();
            System.out.println("[1] Переглянути товар на складi");
            System.out.println("[2] Додати товар");
            System.out.println("[3] Редагувати товар");
            System.out.println("[4] Видалити товар");
            System.out.println("[5] Повернутись в головне меню");
            System.out.println("[6] Вийти з програми");

            mainMenu = mainInput.nextInt();
            switch (mainMenu){
                case 1:
                    try {
                        ProductFunctionality.viewCatalogProduct();
                    }
                    catch (Exception e){
                        System.out.println("Помилка, файл пустий");
                    }
                    break;
                case 2:
                    ProductFunctionality.createProduct();
                    break;
                case 3:
                    ProductFunctionality.editProduct();
                    break;
                case 4:
                    ProductFunctionality.deleteProduct();
                    break;
                case 5:
                    menuMain(userName);
                    break;
                case 6:
                    System.out.println("Дякуємо що скористалися нашими послугами");
                    System.exit(6);
                    break;
            }
            if(mainMenu > 6){
                System.out.println();
                System.out.println("Введено хибнi данi");
            }
        }while (mainMenu != 6);
    }

    /**
     * Метод роботи з категріями
     * @param userName Ім'я користувача під яким увійшли в програму
     */
    public static void menuCategory(String userName){
        int mainMenu;
        Scanner mainInput = new Scanner(System.in);
        do{
            System.out.println();
            System.out.println("[1] Переглянути категорiї");
            System.out.println("[2] Додати категорiю");
            System.out.println("[3] Редагувати категорiю");
            System.out.println("[4] Видалити категорiю");
            System.out.println("[5] Повернутись в головне меню");
            System.out.println("[6] Вийти з програми");

            mainMenu = mainInput.nextInt();
            switch (mainMenu){
                case 1:
                    try {
                        CategoryFunctionality.viewCatalogCategory();
                    }
                    catch (Exception e){
                        System.out.println("Помилка, файл пустий");
                    }
                    break;
                case 2:
                    CategoryFunctionality.createCategory();
                    break;
                case 3:
                    CategoryFunctionality.editCategory();
                    break;
                case 4:
                    CategoryFunctionality.deleteCategory();
                    break;
                case 5:
                    menuMain(userName);
                    break;
                case 6:
                    System.out.println("Дякуємо що скористалися нашими послугами");
                    System.exit(6);
                    break;
            }
            if(mainMenu > 6){
                System.out.println();
                System.out.println("Введено хибнi данi");
            }
        }while (mainMenu != 6);
    }
}
