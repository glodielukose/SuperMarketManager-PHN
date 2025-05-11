//module com.supermarket {
//    requires javafx.controls;
//    requires javafx.fxml;
//    requires java.sql;
////    requires org.xerial.sqlite.jdbc;
//
//    exports com.supermarket.app;
//    opens com.supermarket.app to javafx.graphics;
//}

module com.supermarket {
        requires javafx.controls;
        requires javafx.fxml;
        requires java.sql;

        opens com.supermarket.app to javafx.graphics;
        opens com.supermarket.models to javafx.base;

        opens com.supermarket.controllers.auth to javafx.fxml;
        opens com.supermarket.views.auth to javafx.fxml;

        opens com.supermarket.controllers.admin to javafx.fxml;
        opens  com.supermarket.views.admin to javafx.fxml;

        opens com.supermarket.controllers.cashier to javafx.fxml;
        opens com.supermarket.views.cashier to javafx.fxml;


        exports com.supermarket.app;
        }