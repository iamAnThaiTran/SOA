package org.example;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class Client {
    public static void main(String[] args) {
        try {
            Registry registry = LocateRegistry.getRegistry("localhost", 1099);
            OrderService service = (OrderService) registry.lookup("OrderService");

            String productId = "Product_5";
            int quantity = 5;

            long startTime = System.nanoTime();
            double total = service.calculateTotal(productId, quantity);
            long endTime = System.nanoTime();

            double latency = (endTime - startTime) / 1_000_000.0;
            System.out.println("Total cost for " + quantity + " of " + productId + ": " + total);
            System.out.println("Latency: " + latency + " ms");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
