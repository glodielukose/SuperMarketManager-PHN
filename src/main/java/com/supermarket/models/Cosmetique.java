package com.supermarket.models;

public class Cosmetique extends Product {
    private String brand;

    public Cosmetique(int id, String name, double price, int stock, String brand) {
        super(id, name, price, stock);
        this.brand = brand;
    }

    @Override
    public String getCategory() {
        return "Cosmétique";
    }

    public String getBrand() {
        return brand;
    }
}