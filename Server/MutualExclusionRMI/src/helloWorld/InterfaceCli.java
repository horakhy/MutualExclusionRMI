package helloWorld;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.security.*;

public interface InterfaceCli extends Remote {
  
  public long getId() throws RemoteException;

  public void setChavePublicaServidor(PublicKey chavePublicaServidor) throws RemoteException, InvalidKeyException;

  public void notificar (String text, byte[] assinatura) throws RemoteException, InvalidKeyException, NoSuchAlgorithmException, SignatureException;
}

