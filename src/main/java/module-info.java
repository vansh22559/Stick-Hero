module com.example.practise {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.media;

    requires com.dlsc.formsfx;
    requires org.kordamp.bootstrapfx.core;

    opens com.example.practise to javafx.fxml;
    exports com.example.practise;
}