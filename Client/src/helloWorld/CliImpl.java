package helloWorld;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.UUID;

public class CliImpl extends UnicastRemoteObject implements InterfaceCli {
  private int id;

  public CliImpl(InterfaceServ referenciaServidor) throws RemoteException {
    this.id = UUID.randomUUID().hashCode() % 100;
    referenciaServidor.registrarInteresse(this.id + " Yoooo", this);
  }

  @Override
  public void notificar(String text) {
    System.out.println("Sua mensagem eh: " + text);
    
  }
}
