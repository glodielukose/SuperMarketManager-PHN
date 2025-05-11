package com.supermarket.utils;

import com.supermarket.controllers.admin.AdminDashboardController;
import com.supermarket.controllers.cashier.CashierDashboardController;
import com.supermarket.services.ProductService;
import com.supermarket.services.UserService;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class ViewUtils {
    public static void loadAdminDashboard(Stage stage, UserService userService, ProductService productService) throws IOException {
        FXMLLoader loader = new FXMLLoader(ViewUtils.class.getResource("/com/supermarket/views/admin/dashboard.fxml"));
        Parent root = loader.load();

        AdminDashboardController controller = loader.getController();
        controller.setServices(userService, productService);

        stage.setScene(new Scene(root));
        stage.setTitle("Tableau de bord Admin");
        stage.show();
    }

    public static void loadCashierDashboard(Stage stage, ProductService productService) {
        try {
            FXMLLoader loader = new FXMLLoader(
                    ViewUtils.class.getResource("/com/supermarket/views/cashier/dashboard.fxml")
            );
            Parent root = loader.load();

            CashierDashboardController controller = loader.getController();
            controller.setProductService(productService);

            stage.setScene(new Scene(root));
            stage.setTitle("Tableau de bord Caissier");
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
            AlertUtils.showError("Erreur lors du chargement du tableau de bord caissier");
        }
    }

}