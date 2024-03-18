use hello_world::hello_service_client::HelloServiceClient;
use hello_world::HelloRequest;

pub mod hello_world {
    tonic::include_proto!("org.example.grpc");
}

#[tokio::main]
async fn main() -> Result<(), Box<dyn std::error::Error>> {
    let mut client = HelloServiceClient::connect("http://[::1]:8080").await?;

    let request = tonic::Request::new(HelloRequest {
        id: 1,
        first_name: "FirstName".into(),
        last_name: "LastName".into(),
    });

    let response = client.hello(request).await?;

    println!("RESPONSE={:?}", response);

    Ok(())
}