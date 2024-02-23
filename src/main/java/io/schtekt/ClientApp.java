package io.schtekt;

import java.io.IOException;

import io.grpc.Grpc;
import io.grpc.InsecureChannelCredentials;
import io.schtekt.controllers.ClientController;
import io.schtekt.grpc.GreetClient;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.stage.Stage;

public class ClientApp extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {

        var client = new GreetClient(Grpc.newChannelBuilderForAddress("localhost", 50051, InsecureChannelCredentials.create()).build());
        try {
            new ClientController(primaryStage, client);
            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
            Platform.exit();
        }
    }
}