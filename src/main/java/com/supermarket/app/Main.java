package com.supermarket.app;

import com.supermarket.controllers.auth.LoginController;
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
    public void start(Stage stage) throws Exception {
        // Configuration DB
        IDatabaseConfig config = new SQLITEConfig();
        DatabaseManager dbManager = new DatabaseManager(config);

        // Initialisation des services
        UserDAO userDAO = new UserDAO(dbManager);
        AuthService authService = new AuthService(userDAO);

        // Chargement de la vue de login
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/supermarket/views/auth/login.fxml"));
        Parent root = loader.load();
        LoginController controller = loader.getController();
        controller.setAuthService(authService);

        stage.setScene(new Scene(root));
        stage.setTitle("SuperMarket Manager");
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}