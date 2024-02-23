package org.example;

import io.grpc.stub.StreamObserver;
import org.example.grpc.HelloServiceGrpc.HelloServiceImplBase;
import org.example.grpc.HelloServiceOuterClass.HelloRequest;
import org.example.grpc.HelloServiceOuterClass.HelloResponse;

;

public class HelloServiceImpl extends HelloServiceImplBase {

    @Override
    public void hello(
            HelloRequest request, StreamObserver<HelloResponse> responseObserver) {

        String greeting = new StringBuilder()
                .append("Hello, ")
                .append(request.getFirstName())
                .append(" ")
                .append(request.getLastName())
                .toString();

        HelloResponse response = HelloResponse.newBuilder()
                .setGreeting(greeting)
                .build();

        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }
}