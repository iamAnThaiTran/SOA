package org.example;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class Server {
    public static void main(String[] args) {
        try {
            System.setProperty("java.rmi.server.hostname", "192.168.99.26");
            OrderService service = new OrderServiceImpl();
            Registry registry = LocateRegistry.createRegistry(1099);
            registry.rebind("OrderService", service);
            System.out.println("Server is running...");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
