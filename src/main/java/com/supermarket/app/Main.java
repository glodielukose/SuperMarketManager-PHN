package com.supermarket.app;  // Changé pour correspondre à votre structure

import com.supermarket.controllers.auth.LoginController;
import com.supermarket.database.dao.ProductDAO;
import com.supermarket.database.dao.UserDAO;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import com.supermarket.database.*;
import com.supermarket.services.*;

public class Main extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        // Configuration DB
        IDatabaseConfig config = new SQLITEConfig();
        DatabaseManager dbManager = new DatabaseManager(config);

        // Initialisation des services
        UserDAO userDAO = new UserDAO(dbManager);
        ProductDAO productDAO = new ProductDAO(dbManager);

        UserService userService = new UserService(userDAO);
        ProductService productService = new ProductService(productDAO);
        AuthService authService = new AuthService(userDAO);

        // Chargement de la vue de login
        FXMLLoader loader = new FXMLLoader(
                getClass().getResource("/com/supermarket/views/auth/login.fxml")
        );
        Parent root = loader.load();

        // Configuration du contrôleur
        LoginController loginController = loader.getController();
        loginController.setAuthService(authService);
        loginController.setUserService(userService);
        loginController.setProductService(productService); // Ajoutez cette méthode dans LoginController

        primaryStage.setScene(new Scene(root));
        primaryStage.setTitle("SuperMarket Manager");
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}