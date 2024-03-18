use tonic::{transport::Server, Request, Response, Status};

use hello_world::hello_service_server::{HelloService, HelloServiceServer};
use hello_world::{HelloRequest, HelloResponse};

pub mod hello_world {
    tonic::include_proto!("org.example.grpc");
}

#[derive(Debug, Default)]
pub struct MyHelloService {}

#[tonic::async_trait]
impl HelloService for MyHelloService {
    async fn hello(
        &self,
        request: Request<HelloRequest>,
    ) -> Result<Response<HelloResponse>, Status> {
        println!("Got a request: {:?}", request);
        let request_data = request.into_inner();
        let reply = hello_world::HelloResponse {
            greeting: format!("Hello, {} {}!", request_data.first_name, request_data.last_name),
        };
        Ok(Response::new(reply))
    }
}

#[tokio::main]
async fn main() -> Result<(), Box<dyn std::error::Error>> {
    let addr = "[::1]:8080".parse()?;
    let my_service = MyHelloService::default();

    Server::builder()
        .add_service(HelloServiceServer::new(my_service))
        .serve(addr)
        .await?;

    Ok(())
}