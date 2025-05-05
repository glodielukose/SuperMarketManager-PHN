package com.supermarket.database.dao;

import com.supermarket.database.IConnectionProvider;
import com.supermarket.models.Admin;
import com.supermarket.models.Cashier;
import com.supermarket.models.User;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDAO {
    private final IConnectionProvider connectionProvider;

    // Injection de la dépendance IConnectionProvider
    public UserDAO(IConnectionProvider connectionProvider) {
        this.connectionProvider = connectionProvider;
    }

    // --- Opérations CRUD ---

    /**
     * Trouve un utilisateur par son nom d'utilisateur.
     * Retourne un objet Admin ou Cashier selon le rôle.
     */
    public User findByUsername(String username) throws SQLException {
        String sql = "SELECT * FROM users WHERE username = ?";
        try (Connection conn = connectionProvider.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, username);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return mapResultSetToUser(rs);
            }
            return null;
        }
    }

    /**
     * Récupère tous les utilisateurs (Admin + Cashier).
     */
    public List<User> findAll() throws SQLException {
        String sql = "SELECT * FROM users";
        List<User> users = new ArrayList<>();

        try (Connection conn = connectionProvider.getConnection();
             Statement stmt = conn.createStatement()) {
            ResultSet rs = stmt.executeQuery(sql);

            while (rs.next()) {
                users.add(mapResultSetToUser(rs));
            }
        }
        return users;
    }

    /**
     * Sauvegarde un utilisateur (Admin ou Cashier) en base.
     */
    public void save(User user) throws SQLException {
        String sql = "INSERT INTO users (username, password, role) VALUES (?, ?, ?)";
        try (Connection conn = connectionProvider.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setString(1, user.getUsername());
            stmt.setString(2, user.getPassword());
            stmt.setString(3, user.getRole());
            stmt.executeUpdate();

            // Récupère l'ID auto-généré
            ResultSet rs = stmt.getGeneratedKeys();
            if (rs.next()) {
                user.setId(rs.getInt(1));
            }
        }
    }

    /**
     * Met à jour un utilisateur existant.
     */
    public void update(User user) throws SQLException {
        String sql = "UPDATE users SET username = ?, password = ? WHERE id = ?";
        try (Connection conn = connectionProvider.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, user.getUsername());
            stmt.setString(2, user.getPassword());
            stmt.setInt(3, user.getId());
            stmt.executeUpdate();
        }
    }

    /**
     * Supprime un utilisateur par son ID.
     */
    public void delete(int userId) throws SQLException {
        String sql = "DELETE FROM users WHERE id = ?";
        try (Connection conn = connectionProvider.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, userId);
            stmt.executeUpdate();
        }
    }

    // --- Méthode Helper pour mapper un ResultSet vers User ---
    private User mapResultSetToUser(ResultSet rs) throws SQLException {
        int id = rs.getInt("id");
        String username = rs.getString("username");
        String password = rs.getString("password");
        String role = rs.getString("role");

        return role.equals("admin")
                ? new Admin(id, username, password)
                : new Cashier(id, username, password);
    }
}