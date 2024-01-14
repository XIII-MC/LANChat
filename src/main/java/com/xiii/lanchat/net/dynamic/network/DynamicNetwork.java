package com.xiii.lanchat.net.dynamic.network;

import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicReference;

// Taken from Dynamic.Network
public class DynamicNetwork {

    public static String getIPBySessionName(final String sessionName) {

        final ExecutorService executorService = Executors.newFixedThreadPool(256);
        final List<CompletableFuture<Void>> futures = new ArrayList<>();

        // Get IP
        String ipv4_3bytes = "";
        try {
            ipv4_3bytes = InetAddress.getLocalHost().getHostAddress();
        } catch (final UnknownHostException ignored) {}


        // Split the IP, so we can change the last byte
        final String[] ipv4Split = ipv4_3bytes.split("\\.");
        final String ip = ipv4Split[0] + "." + ipv4Split[1] + "." + ipv4Split[2] + ".";

        // Get the port
        final int sessionPort = getPortByName(sessionName);
        
        // CompletableFuture return thingy
        AtomicReference<String> serverIp = new AtomicReference<>();

        for (int i = 0; i < 255; i++) {

            final String finalIp = ip + i;

            final CompletableFuture<Void> future = CompletableFuture.runAsync(() -> {
                if (checkPort(finalIp, sessionPort)) {
                    serverIp.set(finalIp);
                }
            }, executorService);
            futures.add(future);
        }

        // Clear up all the threads
        CompletableFuture.allOf(futures.toArray(new CompletableFuture[0])).join();
        executorService.shutdownNow();

        return serverIp.get() == null ? null : serverIp.get();
    }

    private static boolean checkPort(final String ipv4, final int port) {

        try {
            new Socket().connect(new InetSocketAddress(ipv4, port), 1000);
            return true;
        } catch (final IOException ignored) {
            return false;
        }
    }

    public static int getPortByName(final String sessionName) {

        int port = 0;
        for (int i = 0; i < sessionName.length(); i++) {
            port += sessionName.charAt(i);
        }

        return port;
    }
}
