package io.schtekt.grpc;

import io.grpc.stub.StreamObserver;
import io.schtekt.GreetRequest;
import io.schtekt.GreetResponse;
import io.schtekt.GreetServiceGrpc.GreetServiceImplBase;
import javafx.application.Platform;
import javafx.scene.control.Label;

public class GreetService extends GreetServiceImplBase{
    public GreetService() {
    }

    public void setReplyMessage(String msg) {
        m_replyMessage = msg;
    }

    public void setClientMessageDestination(Label clientMsgDestination) {
        m_clientMsgDestination = clientMsgDestination;
    }

    @Override
    public void greet(GreetRequest request, StreamObserver<GreetResponse> responseObserver) {
        Platform.runLater(() -> {
            m_clientMsgDestination.setText(request.getMsg());
        });
        responseObserver.onNext(GreetResponse.newBuilder().setMsg(m_replyMessage).build());
        responseObserver.onCompleted();
    }

    private String m_replyMessage;
    private Label m_clientMsgDestination;
}
