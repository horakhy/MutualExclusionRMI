package helloWorld;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.security.*;

public interface InterfaceCli extends Remote {
  
  public void notificar (String text, byte[] assinatura, PublicKey chavePublica) throws RemoteException, InvalidKeyException, NoSuchAlgorithmException, SignatureException;

  public void notificarText (String text) throws RemoteException;
}

