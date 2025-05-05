package com.supermarket.models;

public class Menager extends Product {
    private String usageType; // Ex: "Nettoyage", "Cuisine"

    public Menager(int id, String name, double price, int stock, String usageType) {
        super(id, name, price, stock);
        this.usageType = usageType;
    }

    @Override
    public String getCategory() {
        return "Ménager";
    }

    public String getUsageType() {
        return usageType;
    }
}