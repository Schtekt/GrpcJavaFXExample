package io.schtekt;

import java.io.IOException;

import io.grpc.InsecureServerCredentials;
import io.schtekt.controllers.ServerController;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.stage.Stage;

public class ServerApp extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        try {
            new ServerController(primaryStage, 50051, InsecureServerCredentials.create());
            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
            Platform.exit();
        }
    }
}