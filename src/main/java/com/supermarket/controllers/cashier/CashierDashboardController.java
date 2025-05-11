package com.supermarket.controllers.cashier;

import com.supermarket.models.Product;
import com.supermarket.services.ProductService;
import com.supermarket.utils.AlertUtils;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.sql.SQLException;
import java.util.List;

public class CashierDashboardController {
    @FXML private TableView<Product> productsTable;
    @FXML private TableColumn<Product, String> nameColumn;
    @FXML private TableColumn<Product, Double> priceColumn;

    private ProductService productService;
    private final ObservableList<Product> productData = FXCollections.observableArrayList();

    public void setProductService(ProductService productService) {
        this.productService = productService;
        initializeTable();
        loadProducts();
    }

    private void initializeTable() {
        // Associe les colonnes aux propriétés de la classe Product
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        priceColumn.setCellValueFactory(new PropertyValueFactory<>("price"));

        // Définit les données de la table
        productsTable.setItems(productData);
    }

    private void loadProducts() {
        try {
            // Récupère tous les produits depuis le service
            List<Product> products = productService.getAllProducts();

            // Vide et remplit la liste observable
            productData.clear();
            productData.addAll(products);

        } catch (SQLException e) {
            AlertUtils.showError("Erreur lors du chargement des produits: " + e.getMessage());
            e.printStackTrace();
        }
    }

    @FXML
    private void handleCheckout() {
        // Récupère le produit sélectionné
        Product selectedProduct = productsTable.getSelectionModel().getSelectedItem();

        if (selectedProduct != null) {
            // Ici, vous pouvez implémenter la logique de vente
            System.out.println("Vente du produit: " + selectedProduct.getName());
//            AlertUtils.showInfo("Vente enregistrée pour: " + selectedProduct.getName());
        } else {
            AlertUtils.showError("Veuillez sélectionner un produit");
        }
    }
}