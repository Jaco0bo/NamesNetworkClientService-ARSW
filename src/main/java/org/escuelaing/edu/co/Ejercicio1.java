package org.escuelaing.edu.co;

import java.io.*;
import java.net.*;


public class Ejercicio1 {

    public static void main(String[] args) throws Exception {
        URL google = new URL("http://www.google.com:80/?name=andres#red");

        try (BufferedReader reader
                     = new BufferedReader(new InputStreamReader(google.openStream()))) {
            System.out.println(google.getProtocol());
            System.out.println(google.getAuthority());
            System.out.println(google.getHost());
            System.out.println(google.getPort());
            System.out.println(google.getPath());
            System.out.println(google.getQuery());
            System.out.println(google.getFile());
            System.out.println(google.getRef());
        } catch (IOException x) {
            System.err.println(x);

        }
    }
}