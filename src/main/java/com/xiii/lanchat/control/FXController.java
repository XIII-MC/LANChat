package com.xiii.lanchat.control;


import com.xiii.lanchat.net.SocketClient;
import com.xiii.lanchat.net.SocketServer;
import com.xiii.lanchat.net.dynamic.network.DynamicNetwork;
import com.xiii.lanchat.ui.FXLoader;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.paint.Paint;

import java.io.*;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.Scanner;

public class FXController implements Initializable {

    @FXML
    public Label softwareVersion;
    public Label connectionStatus;
    public TextField pseudo, sessionName, sessionPassword;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        softwareVersion.setText(FXLoader.getSoftwareVersion());
        connectionStatus.setText("C<...>H");
        connectionStatus.setTextFill(Paint.valueOf("#000000")); // Black
    }

    @FXML
    protected void joinSession() {
        connectionStatus.setText("C<-?->H");
        connectionStatus.setTextFill(Paint.valueOf("#e3e300")); // Yellow

        // Init session connection
        final String serverIP = DynamicNetwork.getIPBySessionName(sessionName.getText());
        final int serverPort = DynamicNetwork.getPortByName(sessionName.getText());

        if (serverIP != null) {

            SocketClient.init(serverIP, serverPort);

            connectionStatus.setText("C<--->H");
            connectionStatus.setTextFill(Paint.valueOf("#00aa00")); // Red
        } else {
            connectionStatus.setText("C<-x->H");
            connectionStatus.setTextFill(Paint.valueOf("#aa0000")); // Red
        }
    }

    @FXML
    protected void startSession() {
        //new Thread(() -> {
            String ip = null;
            try (final DatagramSocket socket = new DatagramSocket()) {
                socket.connect(InetAddress.getByName("8.8.8.8"), 10002);
                ip = socket.getLocalAddress().getHostAddress();
            } catch (final IOException ignored) {}
            SocketServer.init(ip, DynamicNetwork.getPortByName(sessionName.getText()));
        //}).start();
    }
}