package io.schtekt.controllers;

import java.io.IOException;

import io.schtekt.grpc.GreetClient;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class ClientController {
    public ClientController(Stage stage, GreetClient client) throws IOException{
        var fxmlResource = getClass().getResource(m_resourcePath);
        var loader = new FXMLLoader(fxmlResource);
        loader.setController(this);
        Parent node = loader.load();

        stage.setScene(new Scene(node));

        m_client = client;
    }

    @FXML
    private void initialize() {
        m_btnSendMessage.disableProperty().bind(m_txtMsg.textProperty().isEmpty());
    }

    @FXML
    public void onSendMessage() {
        String serverReply = m_client.greet(m_txtMsg.getText());
        if(serverReply != null)
        {
            m_lblServerReply.setTextFill(Color.BLACK);
            m_lblServerReply.setText(serverReply);
        }
        else {
            m_lblServerReply.setTextFill(Color.RED);
            m_lblServerReply.setText("Failed request to server");
        }
    }

    private String m_resourcePath = "/io/schtekt/views/clientView.fxml";
    private GreetClient m_client;

    @FXML
    private TextField m_txtMsg;
    @FXML
    private Label m_lblServerReply;
    @FXML
    private Button m_btnSendMessage;
}
