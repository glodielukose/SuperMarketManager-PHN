module com.supermarket {
    // Modules JavaFX requis
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.graphics;

    // Modules Java standards requis
    requires java.sql;
    requires java.desktop;

    // Module pour SQLite JDBC
//    requires org.xerial.sqlite.jdbc;

    // Exportations et ouvertures nécessaires
    exports com.supermarket.app;  // Pour la classe Main
    exports com.supermarket.controllers.admin; // Pour les contrôleurs FXML
    exports com.supermarket.controllers.auth; // Pour les contrôleurs FXML
    exports com.supermarket.controllers.cashier; // Pour les contrôleurs FXML
    exports com.supermarket.models; // Pour les modèles

    // Ouvertures pour la réflexion nécessaire à JavaFX/FXML
    opens com.supermarket.app to javafx.graphics;
    opens com.supermarket.controllers.admin to javafx.fxml;
    opens com.supermarket.controllers.auth to javafx.fxml;
    opens com.supermarket.controllers.cashier to javafx.fxml;
    opens com.supermarket.views.auth to javafx.fxml;
    opens com.supermarket.views.admin to javafx.fxml;
    opens com.supermarket.views.cashier to javafx.fxml;
    opens com.supermarket.models to javafx.base;
}