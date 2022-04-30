package helloWorld;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.security.*;

public interface InterfaceCli extends Remote {
  
  public long getId() throws RemoteException;

  public void notificar (String text, byte[] assinatura, PublicKey chavePublica) throws RemoteException, InvalidKeyException, NoSuchAlgorithmException, SignatureException;

  public void notificar (String text, byte[] assinatura) throws RemoteException, InvalidKeyException, NoSuchAlgorithmException, SignatureException;
}

