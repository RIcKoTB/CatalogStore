package com.rickotb.catalogstore.da.entity;

/**
 * Клас сутності користувачів
 */
public class User {

    private String name;

    private String password;

    public String getName() {
        return name;
    }

    public String getPassword() {
        return password;
    }

    /**
     * Конструктор сутності користувачів
     * @param name Ім'я користувача
     * @param password Пароль корисутвача
     */
    public User(String name, String password) {
        this.name = name;
        this.password = password;
    }
}
