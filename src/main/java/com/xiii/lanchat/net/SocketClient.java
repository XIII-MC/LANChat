package com.xiii.lanchat.net;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;

public class SocketClient {

    public static void init(final String serverIP, final int serverPort) {

        try {
            Socket socket = new Socket(serverIP, serverPort);
            System.out.println("Connected!");

            OutputStream outputStream = socket.getOutputStream();
            DataOutputStream dataOutputStream = new DataOutputStream(outputStream);

            System.out.println("Sending string to the ServerSocket");

            // write the message we want to send
            dataOutputStream.writeUTF("Hello from the other side!");
            outputStream.flush();
            dataOutputStream.flush();
            dataOutputStream.close();
            outputStream.close();

            System.out.println("Closing socket and terminating program.");
            socket.close();
        } catch (final IOException e) {
            throw new RuntimeException(e);
        }
    }
}
