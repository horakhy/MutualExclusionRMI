package helloWorld;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.*;

public class ServImpl extends UnicastRemoteObject implements InterfaceServ {

  private int process;
  private int token = 0;
  private ArrayList<InterfaceCli> clientesEmEspera1;
  private ArrayList<InterfaceCli> clientesEmEspera2;

  public ServImpl() throws RemoteException {
    super();
    this.clientesEmEspera1 = new ArrayList<InterfaceCli>();
    this.clientesEmEspera2 = new ArrayList<InterfaceCli>();
  }

  @Override
  public void registrarInteresse(String text, InterfaceCli referenciaCliente) throws RemoteException {
    if (clientesEmEspera1.size() <= 0) {
      referenciaCliente.notificar(text);
      return;
    }
      clientesEmEspera1.add(referenciaCliente);
      referenciaCliente.notificar("O recurso está sendo utilizado por outro cliente");
    
  }

  public void enviarResposta(String text, InterfaceCli referenciaCliente) throws RemoteException {
    referenciaCliente.notificar(text);
  }

}
