package com.rickotb.catalogstore.da.dao;

import java.util.List;
import java.util.Optional;

/**
 * Інтерфейс продуктів
 * @param <Product> Колекція продуктів
 */
public interface Dao<Product> {

    Optional<Product> get(int id);

    List<Product> getAll();
}
