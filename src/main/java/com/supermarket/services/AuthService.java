package com.supermarket.services;

import com.supermarket.database.dao.UserDAO;
import com.supermarket.models.User;

import java.sql.SQLException;

public class AuthService {
    private UserDAO userDAO;

    public AuthService(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    public boolean login(String username, String password) throws SQLException {
        User user = userDAO.findByUsername(username);
        return user != null && user.getPassword().equals(password);
    }
}