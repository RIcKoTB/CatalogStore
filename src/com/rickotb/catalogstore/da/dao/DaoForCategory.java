package com.rickotb.catalogstore.da.dao;

import java.util.List;
import java.util.Optional;

/**
 * Інтерфейс категорії
 * @param <Category> Колекція категорій
 */
public interface DaoForCategory<Category> {

    Optional<Category> get(int id);

    List<Category> getAll();

}
