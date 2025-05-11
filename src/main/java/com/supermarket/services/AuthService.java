package com.supermarket.services;

import com.supermarket.database.dao.UserDAO;
import com.supermarket.models.User;

import java.sql.SQLException;

public class AuthService {
    private UserDAO userDAO;

    public AuthService(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    public User login(String username, String password) throws SQLException {
        User user = userDAO.findByUsername(username);
        if (user != null && user.getPassword().equals(password)) {
            return user; // Retourne l'objet User complet (Admin ou Cashier)
        }
        return null; // Échec de l'authentification
    }
}