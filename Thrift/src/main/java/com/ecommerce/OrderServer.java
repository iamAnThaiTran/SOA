package com.ecommerce;

import org.apache.thrift.server.TServer;
import org.apache.thrift.server.TSimpleServer;
import org.apache.thrift.transport.TServerSocket;
import org.apache.thrift.transport.TServerTransport;

public class OrderServer {
    public static void main(String[] args) {
        try {
            OrderServiceHandler handler = new OrderServiceHandler();
            OrderService.Processor<OrderServiceHandler> processor = new OrderService.Processor<>(handler);
            TServerTransport serverTransport = new TServerSocket(9090);
            TServer server = new TSimpleServer(new TSimpleServer.Args(serverTransport).processor(processor));

            System.out.println("OrderService is running on port 9090...");
            server.serve();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
