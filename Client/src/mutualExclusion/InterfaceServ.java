package mutualExclusion;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.security.*;

public interface InterfaceServ extends Remote {

	public String registrarInteresse(String text, InterfaceCli referenciaCliente, int numRecurso) throws RemoteException, InvalidKeyException, NoSuchAlgorithmException, SignatureException;

	public String registrarLiberacao(String text, long idCliente, int numRecurso) throws RemoteException, RemoteException, NoSuchAlgorithmException, InvalidKeyException, SignatureException;
}
