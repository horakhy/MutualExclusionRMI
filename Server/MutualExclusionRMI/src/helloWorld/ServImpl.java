package helloWorld;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class ServImpl extends UnicastRemoteObject implements InterfaceServ {

  public ServImpl() throws RemoteException {
    super();
  }

  @Override
  public void registrarInteresse(String text, InterfaceCli referenciaCliente) throws RemoteException {
    referenciaCliente.notificar(text);
    
  }

}

