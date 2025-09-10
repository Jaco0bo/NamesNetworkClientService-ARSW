package org.escuelaing.edu.co.ejercicio7;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Scanner;

public class ChatApp {

    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);

        // Configuración local
        System.out.print("Tu nombre: ");
        String nombre = sc.nextLine();

        System.out.print("Puerto local para publicar tu servicio: ");
        int puertoLocal = Integer.parseInt(sc.nextLine());
        new ChatServiceImpl(nombre, puertoLocal);

        // Configuración remota
        System.out.print("IP del otro usuario: ");
        String ip = sc.nextLine();

        System.out.print("Puerto remoto del otro usuario: ");
        int puertoRemoto = Integer.parseInt(sc.nextLine());

        Registry registry = LocateRegistry.getRegistry(ip, puertoRemoto);
        ChatService remoto = (ChatService) registry.lookup("ChatService");

        System.out.println("Conectado al chat. Escribe tus mensajes:");
        while (true) {
            String msg = sc.nextLine();
            remoto.sendMessage("["+nombre+"]: " + msg);
        }
    }
}


