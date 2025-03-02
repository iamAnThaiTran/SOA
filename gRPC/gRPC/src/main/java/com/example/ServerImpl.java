package com.example;

import io.grpc.stub.StreamObserver;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class ServerImpl extends ProductServiceGrpc.ProductServiceImplBase {
    @Override
    public void calculateTotal(ProductRequest request, StreamObserver<OrderConfirmation> responseObserver) {
        String productId = request.getProductId();
        int quantity = request.getQuantity();
        double totalPrice = 0.0;

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement("SELECT price FROM Productss WHERE name = ?")) {
            stmt.setString(1, productId);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                double price = rs.getDouble("price");
                totalPrice = price * quantity;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        OrderConfirmation response = OrderConfirmation.newBuilder()
                .setTotalPrice(totalPrice)
                .build();

        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }
}