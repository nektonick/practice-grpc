package org.example;

import io.grpc.stub.StreamObserver;
import org.example.grpc.HelloServiceGrpc.HelloServiceImplBase;
import org.example.grpc.HelloServiceOuterClass.HelloRequest;
import org.example.grpc.HelloServiceOuterClass.HelloResponse;

public class HelloServiceImpl extends HelloServiceImplBase {

    @Override
    public void hello(HelloRequest request, StreamObserver<HelloResponse> responseObserver) {
        System.out.println("Start processing HelloRequest with id: " + request.getId());

        String greeting = String.format("Hello, %s %s!", request.getFirstName(), request.getLastName());
        HelloResponse response = HelloResponse.newBuilder()
                .setGreeting(greeting)
                .build();
        responseObserver.onNext(response);
        responseObserver.onCompleted();

        System.out.println("Finish processing HelloRequest with id: " + request.getId());
    }
}