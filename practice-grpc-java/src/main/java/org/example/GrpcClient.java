package org.example;

import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import org.example.grpc.HelloServiceGrpc;
import org.example.grpc.HelloServiceOuterClass.HelloRequest;
import org.example.grpc.HelloServiceOuterClass.HelloResponse;

import java.util.concurrent.ThreadLocalRandom;

public class GrpcClient {
    public static void main(String[] args) {
        System.out.println("Starting gRPC client");
        ManagedChannel channel = ManagedChannelBuilder.forAddress("localhost", 8080)
                .usePlaintext()
                .build();

        HelloServiceGrpc.HelloServiceBlockingStub stub
                = HelloServiceGrpc.newBlockingStub(channel);
        System.out.println("Client connected to " + channel.authority());

        var randomId = ThreadLocalRandom.current().nextInt(0, Integer.MAX_VALUE);
        System.out.println("Sending HelloRequest with id: " + randomId);
        var request = HelloRequest.newBuilder()
                .setFirstName("FirstName")
                .setLastName("LastName")
                .setId(randomId)
                .build();
        HelloResponse helloResponse = stub.hello(request);
        System.out.println("Received HelloResponse: " + helloResponse.getGreeting());
        
        channel.shutdown();
    }
}
