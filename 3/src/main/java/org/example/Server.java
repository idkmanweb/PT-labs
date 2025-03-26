package org.example;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class Server {
    private static Boolean running = true;
    private static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

    public static void main(String[] args) throws IOException {
        ServerSocket host = new ServerSocket(6969);

        while (running) {
            Socket client = host.accept();
            System.out.println("New client connected: " + client.getInetAddress().toString() + ":" + client.getPort());

            ConnectionHandler clientHandler = new ConnectionHandler(client);
            Thread clientThread = new Thread(clientHandler);
            clientThread.start();

            String input = reader.readLine();
            if(input.equals("end")){
                running = false;
                clientThread.interrupt();
            }
        }
    }
}
