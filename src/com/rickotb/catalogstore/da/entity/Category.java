package com.rickotb.catalogstore.da.entity;

/**
 * Клас сутності категорій
 */
public class Category {

    public String name;
    public Category( String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
