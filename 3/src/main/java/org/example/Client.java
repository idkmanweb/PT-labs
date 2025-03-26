package org.example;

import java.io.*;
import java.net.Socket;

import static java.lang.Integer.parseInt;

public class Client {
    private static Boolean running = true;
    private static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    private static String input;

    public static void main(String[] args) throws IOException {
        Socket socket = new Socket("127.0.0.1", 6969);

        PrintWriter outText = new PrintWriter(socket.getOutputStream(), true);
        BufferedReader inText = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
        ObjectInputStream in = new ObjectInputStream(socket.getInputStream());

        String response = inText.readLine();
        System.out.println("Received from server: " + response);

        while (running) {
            System.out.println("Input number of messages to send or type 'end' to end:");
            input = reader.readLine();
            if(isInt(input)){
                outText.println(input);
                System.out.println("Sent to server: " + input);

                response = inText.readLine();
                System.out.println("Received from server: " + response);

                if(response.equals("Ready for " + input + " messages")){
                    int nMessages = parseInt(input);
                    for (int i = 1; i <= nMessages; i++){
                        System.out.println("Input message number " + i + ":");
                        input = reader.readLine();
                        out.writeObject(new Message(i, input));
                        System.out.println("Sent message to server");
                    }
                    response = inText.readLine();
                    System.out.println("Received from server: " + response);
                }
            } else if(input.equals("end")){
                running = false;
            }
        }
        in.close();
        out.close();
        socket.close();
    }

    private static boolean isInt(String str) {
        if (str == null) {
            return false;
        }
        try {
            int n = parseInt(str);
        } catch (NumberFormatException nfe) {
            return false;
        }
        return true;
    }
}
