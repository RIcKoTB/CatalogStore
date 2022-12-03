package com.rickotb.catalogstore.bl;

import at.favre.lib.crypto.bcrypt.BCrypt;
import com.rickotb.catalogstore.da.entity.User;
import com.rickotb.catalogstore.ui.Menu;

import java.nio.charset.Charset;
import java.util.List;
import java.util.Scanner;

/**
 * Клас авторизації
 */
public class Authorization {
    /**
     * Авторизує корисутвача в програму
     */
    public static void authorization() {
        while (true) {
            System.out.println("Введiть логiн: ");
            Scanner userLoginInput = new Scanner(System.in,
                    Charset.forName( System.getProperty("os.name")
                            .toLowerCase().startsWith("win") ? "Windows-1251" : "UTF-8"));
            try {
                String userLogin = userLoginInput.nextLine();
                System.out.println("Введiть пароль: ");
                Scanner password = new Scanner(System.in,
                        Charset.forName( System.getProperty("os.name")
                                .toLowerCase().startsWith("win") ? "Windows-1251" : "UTF-8"));
                String userPassword = password.nextLine();
                User user = new User(userLogin, userPassword);

                 if (isExistUser(user) == 1) {
                     System.out.println("Авторизацiя успiшна");
                     Menu.menuMain(userLogin);
                     break;
                 }else if(isExistUser(user) == 2){
                     System.out.println("Не вiрний логiн або пароль");
                     Menu.startMenu();
                 }
            }
            catch (Exception e){
                System.out.println("Помилка, спробуйте ще раз");
            }
        }
    }

    /**
     * Користувач існує
     * @param user Список всіх корисутвачів
     * @return 1 - Аккаунта не існує 2 - Аккаунт існує
     */
    public static int isExistUser(User user){
        List<User> userList = Json.jsonUserList();
        for (User userFromList : userList) {
            if(userFromList.getName().equals(user.getName()) &&
                    BCrypt.verifyer().verify(user.getPassword().toCharArray(),
                            userFromList.getPassword()).verified) {
                return 1;
            }
        }
        return 2;
    }
}
