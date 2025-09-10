package org.escuelaing.edu.co.ejercicio6;

import java.io.IOException;
import java.net.*;


public class DatagramClient {
    public static void main(String[] args) throws SocketException, UnknownHostException {
        while (true) {
            DatagramSocket socket = new DatagramSocket();
            socket.setSoTimeout(2000);
            byte[] buf = new byte[256];
            var address = InetAddress.getByName("127.0.0.1");

            try {
                DatagramPacket packet = new DatagramPacket(buf, buf.length, address, 4444);
                socket.send(packet);

                packet = new DatagramPacket(buf, buf.length);
                socket.receive(packet);
                String received = new String(packet.getData(), 0, packet.getLength());
                System.out.println("Date: " + received);
                Thread.sleep(5000);
            } catch (IOException | InterruptedException ex) {
                System.out.println("No response from server, retrying...");
            }
        }
    }
}