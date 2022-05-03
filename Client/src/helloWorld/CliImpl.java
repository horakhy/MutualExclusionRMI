package helloWorld;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.security.*;
import java.util.UUID;

public class CliImpl extends UnicastRemoteObject implements InterfaceCli {
	private long id;
	private PublicKey chavePublicaServidor;

	public CliImpl(InterfaceServ referenciaServidor) throws RemoteException, InvalidKeyException, NoSuchAlgorithmException, SignatureException {
		this.id = (UUID.randomUUID().getMostSignificantBits() & Long.MAX_VALUE) % 1000000;
		System.out.println("\nCliente " + this.id + " criado\n");
	}
	@Override
	public long getId() {
		return this.id;
	}

	@Override
	public void setChavePublicaServidor(PublicKey chavePublicaServidor) {
		this.chavePublicaServidor = chavePublicaServidor;
	}

	@Override
	public void notificar(String text, byte[] assinatura) throws RemoteException, InvalidKeyException, NoSuchAlgorithmException, SignatureException {
		verificaMensagem(this.chavePublicaServidor, text, assinatura);
		System.out.println(text);
	}

	public void verificaMensagem(PublicKey chavePublica, String mensagem, byte[] assinatura)
			throws NoSuchAlgorithmException, InvalidKeyException, SignatureException {
		Signature serverSig = Signature.getInstance("DSA");
		serverSig.initVerify(chavePublica);
		serverSig.update(mensagem.getBytes());

		if (serverSig.verify(assinatura)) {
			// Mensagem corretamente assinada
			System.out.println("\nA Mensagem recebida foi assinada corretamente.");
		} else {
			// Mensagem não pode ser validada
			System.out.println("\nA Mensagem recebida NÃO pode ser validada.");
		}
	}
}