package helloWorld;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface InterfaceServ extends Remote{
  
  public void registrarInteresse (String text, InterfaceCli referenciaCliente) throws RemoteException;
}
