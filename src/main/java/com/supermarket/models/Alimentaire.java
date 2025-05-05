package com.supermarket.models;

public class Alimentaire extends Product {
    private String expirationDate;

    public Alimentaire(int id, String name, double price, int stock, String expirationDate) {
        super(id, name, price, stock);
        this.expirationDate = expirationDate;
    }

    @Override
    public String getCategory() {
        return "Alimentaire";
    }

    // Getter spécifique
    public String getExpirationDate() {
        return expirationDate;
    }
}