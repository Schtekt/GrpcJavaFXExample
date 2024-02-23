package io.schtekt.grpc;

import io.grpc.Channel;
import io.grpc.StatusRuntimeException;
import io.schtekt.GreetRequest;
import io.schtekt.GreetServiceGrpc;
import io.schtekt.GreetServiceGrpc.GreetServiceBlockingStub;

public class GreetClient {
    public GreetClient(Channel ch) {
        m_stub = GreetServiceGrpc.newBlockingStub(ch);
    }

    public String greet(String msg) {
        try {
            var resp = m_stub.greet(GreetRequest.newBuilder().setMsg(msg).build());
            return resp.getMsg();
        } catch (StatusRuntimeException e) {
            e.printStackTrace();
            return null;
        }
    }

    private GreetServiceBlockingStub m_stub;
}
