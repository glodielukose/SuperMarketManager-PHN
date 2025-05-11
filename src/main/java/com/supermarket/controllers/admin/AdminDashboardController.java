package com.supermarket.controllers.admin;

import com.supermarket.models.Product;
import com.supermarket.models.User;
import com.supermarket.models.Admin;
import com.supermarket.models.Cashier;
import com.supermarket.services.UserService;
import com.supermarket.services.ProductService;
import com.supermarket.utils.AlertUtils;
import com.supermarket.utils.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class AdminDashboardController {
    // Gestion des utilisateurs
    @FXML private TableView<User> usersTable;
    @FXML private TableColumn<User, Integer> userIdColumn;
    @FXML private TableColumn<User, String> usernameColumn;
    @FXML private TableColumn<User, String> userRoleColumn;

    // Gestion des produits
    @FXML private TableView<Product> productsTable;
    @FXML private TableColumn<Product, Integer> productIdColumn;
    @FXML private TableColumn<Product, String> productNameColumn;
    @FXML private TableColumn<Product, Double> productPriceColumn;
    @FXML private TableColumn<Product, Integer> productStockColumn;

    private UserService userService;
    private ProductService productService;

    public void setServices(UserService userService, ProductService productService) {
        this.userService = userService;
        this.productService = productService;
        loadAllData();
    }

    @FXML
    private void initialize() {
        // Configuration de la table des utilisateurs
        userIdColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        usernameColumn.setCellValueFactory(new PropertyValueFactory<>("username"));
        userRoleColumn.setCellValueFactory(new PropertyValueFactory<>("role"));

        // Configuration de la table des produits
        productIdColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        productNameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        productPriceColumn.setCellValueFactory(new PropertyValueFactory<>("price"));
        productStockColumn.setCellValueFactory(new PropertyValueFactory<>("stock"));

        // Ajout d'un menu contextuel pour les utilisateurs
        setupUserContextMenu();
        setupProductContextMenu();
    }

    private void loadAllData() {
        loadUsers();
        loadProducts();
    }

    private void loadUsers() {
        try {
            ObservableList<User> users = FXCollections.observableArrayList(userService.getAllUsers());
            usersTable.setItems(users);
        } catch (SQLException e) {
            AlertUtils.showError("Erreur lors du chargement des utilisateurs: " + e.getMessage());
        }
    }

    private void loadProducts() {
        try {
            ObservableList<Product> products = FXCollections.observableArrayList(productService.getAllProducts());
            productsTable.setItems(products);
        } catch (SQLException e) {
            AlertUtils.showError("Erreur lors du chargement des produits: " + e.getMessage());
        }
    }

    // Gestion des utilisateurs
    @FXML
    private void handleAddUser() {
        try {
            // Dialogue pour choisir le type d'utilisateur
            ChoiceDialog<String> typeDialog = new ChoiceDialog<>("Admin", List.of("Admin", "Cashier"));
            typeDialog.setTitle("Nouvel utilisateur");
            typeDialog.setHeaderText("Choisissez le type d'utilisateur");
            Optional<String> typeResult = typeDialog.showAndWait();

            if (typeResult.isPresent()) {
                // Dialogue pour saisir les informations
                TextInputDialog dialog = new TextInputDialog();
                dialog.setTitle("Nouvel utilisateur");
                dialog.setHeaderText("Créer un nouvel " + typeResult.get());
                dialog.setContentText("Nom d'utilisateur:");

                Optional<String> usernameResult = dialog.showAndWait();
                usernameResult.ifPresent(username -> {
                    TextInputDialog passDialog = new TextInputDialog();
                    passDialog.setTitle("Mot de passe");
                    passDialog.setHeaderText("Définir le mot de passe pour " + username);
                    passDialog.setContentText("Mot de passe:");

                    Optional<String> passResult = passDialog.showAndWait();
                    passResult.ifPresent(password -> {
                        try {
                            User newUser = typeResult.get().equals("Admin")
                                    ? userService.createAdmin(username, password)
                                    : userService.createCashier(username, password);
                            loadUsers();
//                            AlertUtils.showSuccess("Utilisateur créé avec succès!");
                        } catch (SQLException e) {
                            AlertUtils.showError("Erreur lors de la création: " + e.getMessage());
                        }
                    });
                });
            }
        } catch (Exception e) {
            AlertUtils.showError("Erreur: " + e.getMessage());
        }
    }

    private void setupUserContextMenu() {
        ContextMenu contextMenu = new ContextMenu();

        MenuItem editItem = new MenuItem("Modifier");
        editItem.setOnAction(e -> handleEditUser());

        MenuItem deleteItem = new MenuItem("Supprimer");
        deleteItem.setOnAction(e -> handleDeleteUser());

        contextMenu.getItems().addAll(editItem, deleteItem);
        usersTable.setContextMenu(contextMenu);
    }

    private void handleEditUser() {
        User selected = usersTable.getSelectionModel().getSelectedItem();
        if (selected != null) {
            // Implémentez la logique d'édition ici
            // Par exemple, ouvrir une fenêtre de dialogue avec les infos actuelles
        }
    }

    private void handleDeleteUser() {
        User selected = usersTable.getSelectionModel().getSelectedItem();
        if (selected != null) {
            Alert confirmation = new Alert(Alert.AlertType.CONFIRMATION);
            confirmation.setTitle("Confirmation");
            confirmation.setHeaderText("Supprimer l'utilisateur");
            confirmation.setContentText("Êtes-vous sûr de vouloir supprimer " + selected.getUsername() + "?");

            Optional<ButtonType> result = confirmation.showAndWait();
            if (result.isPresent() && result.get() == ButtonType.OK) {
                try {
                    userService.deleteUser(selected.getId());
                    loadUsers();
//                    AlertUtils.showSuccess("Utilisateur supprimé avec succès!");
                } catch (SQLException e) {
                    AlertUtils.showError("Erreur lors de la suppression: " + e.getMessage());
                }
            }
        }
    }

    // Gestion des produits
    @FXML
    private void handleAddProduct() {
        // Implémentez la logique d'ajout de produit
    }

    private void setupProductContextMenu() {
        ContextMenu contextMenu = new ContextMenu();

        MenuItem editItem = new MenuItem("Modifier");
        editItem.setOnAction(e -> handleEditProduct());

        MenuItem deleteItem = new MenuItem("Supprimer");
        deleteItem.setOnAction(e -> handleDeleteProduct());

        contextMenu.getItems().addAll(editItem, deleteItem);
        productsTable.setContextMenu(contextMenu);
    }

    private void handleEditProduct() {
        // Implémentez la logique d'édition de produit
    }

    private void handleDeleteProduct() {
        // Implémentez la logique de suppression de produit
    }
}