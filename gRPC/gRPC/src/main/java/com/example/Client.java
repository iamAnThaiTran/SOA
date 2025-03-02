package com.example;

import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;

public class Client {
    public static void main(String[] args) {
        ManagedChannel channel = ManagedChannelBuilder.forAddress("localhost", 50051)
                .usePlaintext()
                .build();

        ProductServiceGrpc.ProductServiceBlockingStub stub = ProductServiceGrpc.newBlockingStub(channel);

        ProductRequest request = ProductRequest.newBuilder()
                .setProductId("Product_5")
                .setQuantity(3)
                .build();

        OrderConfirmation response = stub.calculateTotal(request);
        System.out.println("Total Price: " + response.getTotalPrice());

        channel.shutdown();
    }
}
