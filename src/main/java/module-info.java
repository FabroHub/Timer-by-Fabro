module com.example.timer {
    requires javafx.controls;
    requires javafx.fxml;

        requires org.controlsfx.controls;

    opens com.example.timer to javafx.fxml;
    exports com.example.timer;
}