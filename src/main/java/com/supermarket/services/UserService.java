package com.supermarket.services;

import com.supermarket.database.dao.UserDAO;
import com.supermarket.models.Admin;
import com.supermarket.models.Cashier;
import com.supermarket.models.User;
import java.sql.SQLException;
import java.util.List;

public class UserService {
    private final UserDAO userDAO;

    // Injection du DAO via le constructeur
    public UserService(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    // --- Authentification ---
    public User login(String username, String password) throws SQLException {
        User user = userDAO.findByUsername(username);
        if (user != null && user.getPassword().equals(password)) {
            return user; // Retourne Admin ou Cashier selon le rôle
        }
        return null; // Échec de l'authentification
    }

    // --- Gestion des Utilisateurs ---
    public List<User> getAllUsers() throws SQLException {
        return userDAO.findAll(); // Retourne une List<User> (mix Admin/Cashier)
    }

    public void addUser(User user) throws SQLException, IllegalArgumentException {
        // Validation métier : vérifie que le username est unique
        if (userDAO.findByUsername(user.getUsername()) != null) {
            throw new IllegalArgumentException("Ce nom d'utilisateur existe déjà !");
        }
        userDAO.save(user); // Sauvegarde en DB
    }

    public void updateUser(User user) throws SQLException {
        userDAO.update(user);
    }

    public void deleteUser(int userId) throws SQLException {
        userDAO.delete(userId);
    }

    // --- Méthodes Spécifiques aux Rôles ---
    public Admin createAdmin(String username, String password) throws SQLException {
        Admin admin = new Admin(0, username, password); // ID généré par la DB
        addUser(admin);
        return admin;
    }

    public Cashier createCashier(String username, String password) throws SQLException {
        Cashier cashier = new Cashier(0, username, password);
        addUser(cashier);
        return cashier;
    }
}