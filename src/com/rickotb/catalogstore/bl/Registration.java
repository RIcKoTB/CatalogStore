package com.rickotb.catalogstore.bl;

import at.favre.lib.crypto.bcrypt.BCrypt;
import com.rickotb.catalogstore.da.dao.UserDao;
import com.rickotb.catalogstore.da.entity.User;
import com.rickotb.catalogstore.ui.Menu;

import java.nio.charset.Charset;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;

/**
 * Клас реєстрації
 */
public class Registration {

    /**
     * Метод реєстрації
     */
    public static void registration() {

        UserDao userDao = new UserDao();
        List<User> users = userDao.getAll();

        System.out.println("Введiть логiн: ");
        Scanner userLoginInput = new Scanner(System.in,
                Charset.forName( System.getProperty("os.name")
                        .toLowerCase().startsWith("win") ? "Windows-1251" : "UTF-8"));
        String userLogin = userLoginInput.nextLine();
        try {
            for (User usersList : users) {
                if (Objects.equals(usersList.getName(), userLogin)) {
                    System.out.println("Такий аккаунт iснує");
                    registration();
                }
            }
                System.out.println("Введiть пароль: ");
                Scanner password = new Scanner(System.in,
                        Charset.forName( System.getProperty("os.name")
                                .toLowerCase().startsWith("win") ? "Windows-1251" : "UTF-8"));
                String userPassword = password.next();

                if (Validation.validate(userLogin, userPassword)) {
                    String hashedUserPassword = BCrypt.withDefaults()
                            .hashToString(12, userPassword.toCharArray());
                    User newUser = new User(userLogin, hashedUserPassword);
                    users.add(newUser);
                    Json.userAdd(users);
                    System.out.println("Аккаунт успiшно створений");
                    Menu.startMenu();
                }else {
                    System.out.println("Логiн та пароль має мiстити не менше 5 символiв");
                    Menu.startMenu();
                }

        }catch (Exception e){
            System.out.println("Логiн та пароль має мiстити не менше 5 символiв");
        }

    }
}
