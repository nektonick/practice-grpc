package org.example;

import io.grpc.Server;
import io.grpc.ServerBuilder;

import java.io.IOException;

public class ServerMain {
    public static void main(String[] args) throws InterruptedException, IOException {
        Server server = ServerBuilder
                .forPort(8080)
                .addService(new HelloServiceImpl()).build();
        server.start();
        System.out.println("Server started");
        server.awaitTermination();
        System.out.println("Server stopped");
    }
}