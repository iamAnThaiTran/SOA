package com.example;
import io.grpc.Server;
import io.grpc.ServerBuilder;


public class ProductServer {
    public static void main(String[] args) throws Exception {
        Server server = ServerBuilder.forPort(50051)
                .addService(new ServerImpl())
                .build()
                .start();

        System.out.println("Server is running on port 50051...");
        server.awaitTermination();
    }
}
