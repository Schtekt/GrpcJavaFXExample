package io.schtekt.controllers;

import java.io.IOException;

import io.grpc.ServerCredentials;
import io.schtekt.grpc.GreetServer;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class ServerController {
    public ServerController(Stage stage, int serverPort, ServerCredentials serverCreds) throws IOException{
        m_port = serverPort;
        m_creds = serverCreds;

        var fxmlResource = getClass().getResource(m_resourcePath);
        var loader = new FXMLLoader(fxmlResource);
        loader.setController(this);
        Parent node = loader.load();

        stage.setScene(new Scene(node));
    }

    @FXML private void initialize() {
        m_btnStartServer.disableProperty().bind(m_txtReplyMsg.textProperty().isEmpty().or(m_btnStopServer.disableProperty().not()));
        m_btnStopServer.disableProperty().bind(m_txtReplyMsg.disableProperty().not());
    }

    @FXML
    public void onStartServer() {
        m_server = new GreetServer(m_port, m_creds);
        try {
            m_server.start(m_txtReplyMsg.getText(), m_lblLatestClientMessage);
            m_txtReplyMsg.setDisable(true);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void onStopServer() {
        try {
            m_server.stop();
            m_txtReplyMsg.setDisable(false);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private int m_port;
    private ServerCredentials m_creds;
    private String m_resourcePath = "/io/schtekt/views/serverView.fxml";
    private GreetServer m_server;

    @FXML
    private TextField m_txtReplyMsg;
    @FXML
    private Label m_lblLatestClientMessage;
    @FXML
    private Button m_btnStartServer;
    @FXML
    private Button m_btnStopServer;
}
