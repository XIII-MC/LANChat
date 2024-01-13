package com.xiii.lanchat.control;

import com.xiii.lanchat.net.HostServer;
import com.xiii.lanchat.net.dynamic.network.DCNPort;
import com.xiii.lanchat.ui.FXLoader;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.paint.Paint;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.concurrent.ExecutionException;

public class FXController implements Initializable {

    @FXML
    public Label softwareVersion;
    public Label connectionStatus;
    public TextField pseudo, sessionName, sessionPassword;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        softwareVersion.setText(FXLoader.getSoftwareVersion());
        connectionStatus.setText("En attente...");
        connectionStatus.setTextFill(Paint.valueOf("#000000")); // Black
    }

    @FXML
    protected void joinSession() throws ExecutionException, InterruptedException {
        connectionStatus.setText("Connexion...");
        connectionStatus.setTextFill(Paint.valueOf("#e3e300")); // Yellow

        // Init session connection
        if (DCNPort.getIPBySessionName(sessionName.getText()) != null) {
            System.out.println("SERVER EXISTS!!");
            connectionStatus.setText("Connecté");
            connectionStatus.setTextFill(Paint.valueOf("#00aa00")); // Red
        } else {
            System.out.println("Nah bieatch");
            connectionStatus.setText("Échoué");
            connectionStatus.setTextFill(Paint.valueOf("#aa0000")); // Red
        }
    }

    @FXML
    protected void startSession() {
        HostServer.init(DCNPort.getPortByName(sessionName.getText()));
    }
}