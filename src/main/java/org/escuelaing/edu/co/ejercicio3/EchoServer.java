package org.escuelaing.edu.co.ejercicio3;

import java.net.*;
import java.io.*;

public class EchoServer {
    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = null;
        try {
            serverSocket = new ServerSocket(35000);
        } catch (IOException e) {
            System.err.println("Could not listen on port: 35000.");
            System.exit(1);
        }
        Socket clientSocket = null;
        try {
            clientSocket = serverSocket.accept();
        } catch (IOException e) {
            System.err.println("Accept failed.");
            System.exit(1);
        }
        PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
        BufferedReader in = new BufferedReader(
                new InputStreamReader(
                        clientSocket.getInputStream()));
        String inputLine, outputLine;
        Integer rInputLine, rOutputLine;
        while ((inputLine = in.readLine()) != null) {
            try {
                rInputLine = Integer.parseInt(inputLine);
                System.out.println("Mensaje: " + inputLine);
                rOutputLine = rInputLine*rInputLine;
                outputLine = rOutputLine.toString();
                out.println(outputLine);

                if (rOutputLine.equals(0))
                    break;
            } catch (NumberFormatException e) {
                System.err.println("Invalid input. Please Enter a number...");
            }
        }
        out.close();
        in.close();
        clientSocket.close();
        serverSocket.close();
    }
}