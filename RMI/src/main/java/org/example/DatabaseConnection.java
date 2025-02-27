package org.example;

import java.sql.*;

public class DatabaseConnection {
    private static final String URL = "jdbc:mysql://localhost:3306/ProductDB";
    private static final String USER = "root";
    private static final String PASSWORD = "123456";

    public static Connection getConnection() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            return DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            System.out.println("loi here");
            return null;
        }
    }

    private double getProductPrice(String productName) {
        double price = 0.0;
        String query = "SELECT price FROM Productss WHERE name = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, productName);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                price = rs.getDouble("price");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return price;
    }

    public static void main(String[] args) {
        DatabaseConnection dbc = new DatabaseConnection();
        System.out.println(dbc.getProductPrice("Product_20"));
    }
}
