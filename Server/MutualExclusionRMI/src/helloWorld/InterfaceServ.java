package helloWorld;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.security.*;

public interface InterfaceServ extends Remote {

	public void registrarInteresse(String text, InterfaceCli referenciaCliente, int numRecurso) throws RemoteException, InvalidKeyException, NoSuchAlgorithmException, SignatureException;
}
