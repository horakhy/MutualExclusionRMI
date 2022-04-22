package helloWorld;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class CliImpl extends UnicastRemoteObject implements InterfaceCli {
  public CliImpl(InterfaceServ referenciaServidor) throws RemoteException {
    referenciaServidor.registrarInteresse("Yoooo", this);
  }

  @Override
  public void notificar(String text) {
    System.out.println("Sua mensagem eh: " + text);
    
  }
}
