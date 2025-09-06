package org.escuelaing.edu.co.ejercicio4;

import java.net.*;
import java.io.*;

public class MathServer {
    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = null;
        try {
            serverSocket = new ServerSocket(34000);
        } catch (IOException e) {
            System.err.println("Could not listen on port: 34000.");
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
        Double rInputLine;
        int rOutputLine;
        String operacion = "cos";
        while ((inputLine = in.readLine()) != null) {
            try {
                if (inputLine.equals("fun:sin")) {
                    operacion = "sin";
                    outputLine = "Operacion cambiada a seno con exito.";
                    out.println(outputLine);
                } else if (inputLine.equals("fun:tan")){
                    operacion = "tan";
                    outputLine = "Operacion cambiada a tangente con exito.";
                    out.println(outputLine);
                } else if (inputLine.equals("fun:cos")){
                    operacion = "cos";
                    outputLine = "Operacion cambiada a coseno con exito.";
                    out.println(outputLine);
                } else {
                    rInputLine = Double.parseDouble(inputLine);
                    System.out.println("Mensaje: " + inputLine);
                    if (operacion.equals("sin")) {
                        rOutputLine = (int) Math.round(Math.sin(rInputLine));
                        out.println(rOutputLine);
                    } else if (operacion.equals("tan")) {
                        rOutputLine = (int) Math.round(Math.tan(rInputLine));
                        out.println(rOutputLine);
                    } else if (operacion.equals("cos")) {
                        rOutputLine = (int) Math.round(Math.cos(rInputLine));
                        out.println(rOutputLine);
                    }
                }
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
