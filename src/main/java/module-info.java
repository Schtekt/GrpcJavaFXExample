module GrpcJavaFXExample {
    requires java.annotation;

    requires javafx.controls;
    requires javafx.fxml;
    requires transitive javafx.graphics;

    requires transitive io.grpc;
    requires io.grpc.protobuf;
    requires io.grpc.stub;

    requires com.google.protobuf;
    requires com.google.common;

    opens io.schtekt.controllers to javafx.fxml;

    exports io.schtekt;
}
