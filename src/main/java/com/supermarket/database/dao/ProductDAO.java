package com.supermarket.database.dao;

import com.supermarket.database.IConnectionProvider;
import com.supermarket.models.Alimentaire;
import com.supermarket.models.Cosmetique;
import com.supermarket.models.Menager;
import com.supermarket.models.Product;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProductDAO {
    private final IConnectionProvider connectionProvider;

    // Injection de la dépendance IConnectionProvider
    public ProductDAO(IConnectionProvider connectionProvider) {
        this.connectionProvider = connectionProvider;
    }

    public List<Product> findAll() throws SQLException {
        String sql = "SELECT * FROM products";
        List<Product> products = new ArrayList<>();

        try (Connection conn = connectionProvider.getConnection();
             Statement stmt = conn.createStatement()) {
            ResultSet rs = stmt.executeQuery(sql);

            while (rs.next()) {
                String category = rs.getString("category");
                Product product;

                switch (category) {
                    case "Alimentaire":
                        product = new Alimentaire(
                                rs.getInt("id"),
                                rs.getString("name"),
                                rs.getDouble("price"),
                                rs.getInt("stock"),
                                rs.getString("expiry_date")
                        );
                        break;
                    case "Cosmétique":
                        product = new Cosmetique(
                                rs.getInt("id"),
                                rs.getString("name"),
                                rs.getDouble("price"),
                                rs.getInt("stock"),
                                rs.getString("brand")
                        );
                        break;
                    case "Ménager":
                        product = new Menager(
                                rs.getInt("id"),
                                rs.getString("name"),
                                rs.getDouble("price"),
                                rs.getInt("stock"),
                                rs.getString("usage_type")
                        );
                        break;
                    default:
                        throw new IllegalArgumentException("Catégorie inconnue : " + category);
                }
                products.add(product);
            }
        }
        return products;
    }
}