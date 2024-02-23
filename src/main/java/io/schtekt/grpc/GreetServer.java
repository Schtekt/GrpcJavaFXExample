package io.schtekt.grpc;

import java.io.IOException;

import io.grpc.Grpc;
import io.grpc.Server;
import io.grpc.ServerCredentials;
import javafx.scene.control.Label;

public class GreetServer {
    public GreetServer(int port, ServerCredentials credentials) {
        m_greetService = new GreetService();
        m_server = Grpc.newServerBuilderForPort(port, credentials)
        .addService(m_greetService)
        .build();
    }

    public void start(String replyMessage, Label clientMsgDestination) throws IOException{
        m_greetService.setReplyMessage(replyMessage);
        m_greetService.setClientMessageDestination(clientMsgDestination);
        m_server.start();
    }

    public void stop() throws InterruptedException{
        m_server.shutdown().awaitTermination();
    }
    private GreetService m_greetService;
    private Server m_server;
}
