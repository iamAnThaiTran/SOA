package com.ecommerce;

import org.apache.thrift.TException;
import java.sql.*;

public class OrderServiceHandler implements OrderService.Iface {
    private static final String DB_URL = "jdbc:mysql://localhost:3306/ProductDB";
    private static final String USER = "root";
    private static final String PASS = "123456";

    @Override
    public double calculateTotal(String productId, int quantity) throws TException {
        double price = getProductPrice(productId);
        if (price < 0) {
            throw new TException("Product not found.");
        }
        return price * quantity;
    }

    private double getProductPrice(String productId) {
        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
             PreparedStatement stmt = conn.prepareStatement("SELECT price FROM Productss WHERE name = ?")) {
            stmt.setString(1, productId);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return rs.getDouble("price");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1;
    }

    public static void main(String[] args) {
        OrderServiceHandler osh = new OrderServiceHandler();
        System.out.println(osh.getProductPrice("Product_20"));
    }
}
