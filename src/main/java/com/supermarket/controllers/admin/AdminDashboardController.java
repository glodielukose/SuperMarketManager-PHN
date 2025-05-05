package com.supermarket.controllers.admin;

import com.supermarket.models.User;
import com.supermarket.services.UserService;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableView;

import java.sql.SQLException;

public class AdminDashboardController {
    @FXML private TableView<User> usersTable;

    private UserService userService;

    public void setUserService(UserService userService) {
        this.userService = userService;
        try {
            usersTable.setItems((ObservableList<User>) userService.getAllUsers());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    private void handleAddUser() {
        // Ouvrir une fenêtre pour ajouter un utilisateur
    }
}