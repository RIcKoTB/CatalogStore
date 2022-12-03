package com.rickotb.catalogstore.bl;

import com.rickotb.catalogstore.ui.Menu;

/**
 * Клас виходу з аккаунту
 */
public class Logout {
    /**
     * Метод виходу з аккаунту
     */
    public static void logout(){
        System.out.println("Ви успiшно вийшли з аккаунту");
        Menu.startMenu();
    }
}
