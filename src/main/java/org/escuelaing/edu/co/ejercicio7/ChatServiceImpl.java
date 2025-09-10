package org.escuelaing.edu.co.ejercicio7;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

public class ChatServiceImpl implements ChatService {

    private String nombre;

    public ChatServiceImpl(String nombre, int puerto) {
        this.nombre = nombre;
        try {
            ChatService stub = (ChatService) UnicastRemoteObject.exportObject(this, 0);
            Registry registry = LocateRegistry.createRegistry(puerto);
            registry.rebind("ChatService", stub);
            System.out.println("["+nombre+"] Servicio de chat publicado en puerto " + puerto);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void sendMessage(String message) throws RemoteException {
        System.out.println("["+nombre+" recibió]: " + message);
    }
}
