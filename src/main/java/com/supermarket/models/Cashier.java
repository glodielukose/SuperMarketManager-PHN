package com.supermarket.models;

public class Cashier extends User {
    public Cashier(int id, String username, String password) {
        super(id, username, password);
    }

    @Override
    public String getRole() {
        return "cashier";
    }

    // Méthodes spécifiques au caissier
    public void processOrder() {
        System.out.println("Traitement d'une commande...");
    }
}