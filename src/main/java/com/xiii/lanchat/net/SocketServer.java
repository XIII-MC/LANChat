package com.xiii.lanchat.net;

import com.xiii.lanchat.control.FXController;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;

public class SocketServer {

    public static void init(final String serverIP, final int serverPort) {

        try {
            final ServerSocket ss = new ServerSocket();
            ss.bind(new InetSocketAddress(serverIP, serverPort));
            System.out.println("ServerSocket awaiting connections...");
            final Socket socket = ss.accept();
            System.out.println("Connection from " + socket + "!");

            final InputStream inputStream = socket.getInputStream();
            final DataInputStream dataInputStream = new DataInputStream(inputStream);

            final String message = dataInputStream.readUTF();
            System.out.println(message);

            System.out.println("Closing sockets.");
            ss.close();
            socket.close();
        } catch (final IOException e) {
            throw new RuntimeException(e);
        }
    }
}
