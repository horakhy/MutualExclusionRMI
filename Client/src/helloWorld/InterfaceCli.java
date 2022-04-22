package helloWorld;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface InterfaceCli extends Remote {
  
  public void notificar (String text) throws RemoteException;
}

