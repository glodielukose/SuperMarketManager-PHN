package com.supermarket.controllers.auth;

import com.supermarket.services.AuthService;
import com.supermarket.utils.AlertUtils;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.control.PasswordField;

import java.sql.SQLException;

public class LoginController {
    @FXML private TextField usernameField;
    @FXML private PasswordField passwordField;

    private AuthService authService;

    public void setAuthService(AuthService authService) {
        this.authService = authService;
    }

    @FXML
    private void handleLogin() throws SQLException {
        String username = usernameField.getText();
        String password = passwordField.getText();

        if (authService.login(username, password)) {
            // Rediriger vers le dashboard (admin/cashier)
        } else {
            AlertUtils.showError("Identifiants incorrects !");
        }
    }
}