# Grpc and JavaFX example
This is a simple project to showcase using grpc and javaFX in one and the same application

## Run the project
This project is managed by maven, to run the server and client applications, run the following maven commands:
```
mvn clean install
mvn javafx:run -D javafx.mainClass=GrpcJavaFXExample/io.schtekt.ServerApp
mvn javafx:run -D javafx.mainClass=GrpcJavaFXExample/io.schtekt.ClientApp
```