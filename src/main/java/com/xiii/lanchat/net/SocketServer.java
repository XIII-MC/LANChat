package com.xiii.lanchat.net;

import com.xiii.lanchat.control.FXController;

import java.io.*;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;

public class SocketServer {

    public static void init(final String serverIP, final int serverPort) {

        new Thread(() -> {

            try {
                ServerSocket serverSocket = new ServerSocket();
                serverSocket.bind(new InetSocketAddress(serverIP, serverPort));
                Socket clientSocket = serverSocket.accept();
                PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
                BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            } catch (final IOException e) {
                e.printStackTrace();
            }
        }).start();
    }
}
