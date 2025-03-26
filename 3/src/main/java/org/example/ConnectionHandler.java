package org.example;

import java.io.*;
import java.net.Socket;

import static java.lang.Integer.parseInt;

public class ConnectionHandler implements Runnable {
    private Socket client;

    public ConnectionHandler(Socket socket) {
        this.client = socket;
    }

    @Override
    public void run() {
        try (
                PrintWriter outText = new PrintWriter(client.getOutputStream(), true);
                BufferedReader inText = new BufferedReader(new InputStreamReader(client.getInputStream()));

                ObjectOutputStream out = new ObjectOutputStream(client.getOutputStream());
                ObjectInputStream in = new ObjectInputStream(client.getInputStream())
        ) {
            outText.println("Ready");
            System.out.println("Sent to client: Ready");

            String inputLine;
            while ((inputLine = inText.readLine()) != null) {
                System.out.println("Received from client: " + inputLine);

                outText.println("Ready for " + inputLine + " messages");
                System.out.println("Sent to client: Ready for " + inputLine + " messages");

                int nMessages = parseInt(inputLine);
                for(int i = 1; i <= nMessages; i++){
                    Message mess = (Message)in.readObject();
                    System.out.println("Received message number " + mess.getNumber() + ": " + mess.getContent());
                }

                outText.println("Finished");
                System.out.println("Sent to client: Finished");
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                client.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
