package com.xiii.lanchat.net;

import com.xiii.lanchat.net.dynamic.network.DCNPort;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class HostServer {

    public static void init(final int port) {

        new Thread(() -> {

            try {
                ServerSocket serverSocket = new ServerSocket(port);
                Socket clientSocket = serverSocket.accept();
                PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
                BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            } catch (final IOException e) {
                e.printStackTrace();
            }
        }).start();
    }
}
