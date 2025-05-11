package com.supermarket.controllers.auth;

import com.supermarket.models.User;
import com.supermarket.services.AuthService;
import com.supermarket.services.ProductService;
import com.supermarket.services.UserService;
import com.supermarket.utils.AlertUtils;
import com.supermarket.utils.ViewUtils;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.control.PasswordField;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;

public class LoginController {
    @FXML private TextField usernameField;
    @FXML private PasswordField passwordField;

    private AuthService authService;
    private UserService userService;
    private ProductService productService;

    public void setAuthService(AuthService authService) {
        this.authService = authService;
    }

    public void setUserService(UserService userService){
        this.userService = userService;
    }

    public void setProductService(ProductService productService) {
        this.productService = productService;
    }

    @FXML
    private void handleLogin() {
        String username = usernameField.getText().trim();
        String password = passwordField.getText().trim();

        if (username.isEmpty() || password.isEmpty()) {
            AlertUtils.showError("Veuillez remplir tous les champs");
            return;
        }

        try {
            User user = authService.login(username, password);
            if (user != null) {
                Stage stage = (Stage) usernameField.getScene().getWindow();
                switch (user.getRole().toLowerCase()) {
                    case "admin":
                        ViewUtils.loadAdminDashboard(stage, userService, productService);
                        break;
                    case "cashier":
                        ViewUtils.loadCashierDashboard(stage, productService);
                        break;
                    default:
                        AlertUtils.showError("Rôle non reconnu !");
                }
            } else {
                AlertUtils.showError("Identifiants incorrects !");
            }
        } catch (SQLException e) {
            AlertUtils.showError("Erreur de base de données: " + e.getMessage());
            e.printStackTrace();
        } catch (IOException e) {
            AlertUtils.showError("Erreur de chargement de l'interface");
            e.printStackTrace();
        }
    }
}