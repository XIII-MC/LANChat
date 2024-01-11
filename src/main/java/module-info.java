module com.xiii.lanchat {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires java.desktop;

    opens com.xiii.lanchat to javafx.fxml;
    exports com.xiii.lanchat;
    exports com.xiii.lanchat.control;
    opens com.xiii.lanchat.control to javafx.fxml;
    exports com.xiii.lanchat.ui;
    opens com.xiii.lanchat.ui to javafx.fxml;
}