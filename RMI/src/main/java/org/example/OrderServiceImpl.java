package org.example;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.HashMap;
import java.util.Map;

public class OrderServiceImpl extends UnicastRemoteObject implements OrderService {
    private static final long serialVersionUID = 1L;

    protected OrderServiceImpl() throws RemoteException {
        super();
    }

    @Override
    public double calculateTotal(String productName, int quantity) throws RemoteException {
        double price = getProductPrice(productName);
        return price * quantity;
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
}