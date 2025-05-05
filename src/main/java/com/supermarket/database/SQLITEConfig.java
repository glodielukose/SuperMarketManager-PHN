package com.supermarket.database;

public class SQLITEConfig implements IDatabaseConfig{

    @Override
    public String getUrl() {
        return "jdbc:sqlite:supermarket.db";
    }
}
