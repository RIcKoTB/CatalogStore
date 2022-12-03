package com.rickotb.catalogstore.da.dao;

import com.rickotb.catalogstore.bl.Json;
import com.rickotb.catalogstore.da.entity.User;

import java.util.List;
import java.util.Optional;

/**
 * Інтерфейс користувачів
 */
public class UserDao implements Dao<User>{

    private List<User> userList;

    public UserDao(){
        userList = Json.jsonUserList();
    }

    @Override
    public Optional<User> get(int id) {
        return Optional.empty();
    }

    @Override
    public List<User> getAll() {
        return userList;
    }
}
