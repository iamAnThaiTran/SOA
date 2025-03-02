package com.ecommerce;

import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.transport.TSocket;
import org.apache.thrift.transport.TTransport;

public class OrderClient {
    public static void main(String[] args) {
        try {
            TTransport transport = new TSocket("localhost", 9090);
            transport.open();

            OrderService.Client client = new OrderService.Client(new TBinaryProtocol(transport));
            String productId = "Product_5";
            int quantity = 5;

            double totalPrice = client.calculateTotal(productId, quantity);
            System.out.println("Total Price for " + quantity + " items of " + productId + ": $" + totalPrice);

            transport.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
