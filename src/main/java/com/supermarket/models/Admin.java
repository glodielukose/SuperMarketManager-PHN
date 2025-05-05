package com.supermarket.models;

public class Admin extends User {
    public Admin(int id, String username, String password) {
        super(id, username, password);
    }

    @Override
    public String getRole() {
        return "admin";
    }

    // Méthodes spécifiques à l'admin
    public void manageUsers() {
        System.out.println("Gestion des utilisateurs...");
    }
}