package helloWorld;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.security.*;
import java.util.*;

public class ServImpl extends UnicastRemoteObject implements InterfaceServ {

  private int process;
  private int token = 0;
  private ArrayList<InterfaceCli> clientesEmEspera1;
  private ArrayList<InterfaceCli> clientesEmEspera2;
  private boolean recursoDisponível_1 = true;
  private boolean recursoDisponível_2 = true;

  public ServImpl() throws RemoteException {
    super();
    this.clientesEmEspera1 = new ArrayList<InterfaceCli>();
    this.clientesEmEspera2 = new ArrayList<InterfaceCli>();
  }
  
  public boolean recebeMensagem(PublicKey pubKey, String mensagem, byte[] assinatura) throws
   NoSuchAlgorithmException, InvalidKeyException, SignatureException {
       Signature clientSig = Signature.getInstance("DSA");
       clientSig.initVerify(pubKey);
       clientSig.update(mensagem.getBytes());

       if (clientSig.verify(assinatura)) {
           //Mensagem corretamente assinada
          System.out.println("A Mensagem recebida foi assinada corretamente.");
          return true;
       } else {
           //Mensagem não pode ser validada
          System.out.println("A Mensagem recebida NÃO pode ser validada.");
          return false;
       }
   }

  @Override
  public void registrarInteresse(String text, InterfaceCli referenciaCliente, int numRecurso, PublicKey chavePublica) throws RemoteException {
    if (recursoDisponível_1) {
      
      referenciaCliente.notificar(text);
      this.recursoDisponível_1 = false;
      return;
    }
      clientesEmEspera1.add(referenciaCliente);
      referenciaCliente.notificar("O recurso 1 está sendo utilizado por outro cliente");
    
  }

  public void enviarResposta(String text, InterfaceCli referenciaCliente) throws RemoteException {
    referenciaCliente.notificar(text);
  }

}
