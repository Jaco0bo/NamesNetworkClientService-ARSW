package org.escuelaing.edu.co.ejercicio7;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface ChatService extends Remote {
    void sendMessage(String message) throws RemoteException;
}
