package com.supermarket.services;

import com.supermarket.database.dao.ProductDAO;
import com.supermarket.models.Product;

import java.sql.SQLException;
import java.util.List;

public class ProductService {
    private ProductDAO productDAO;

    public ProductService(ProductDAO productDAO) {
        this.productDAO = productDAO;
    }

    public List<Product> getAllProducts() throws SQLException {
        return productDAO.findAll();
    }
}