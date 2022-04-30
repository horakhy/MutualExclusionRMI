package helloWorld;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.security.*;
import java.util.UUID;

public class CliImpl extends UnicastRemoteObject implements InterfaceCli {
	private long id;
	private PublicKey chavePublicaServidor;

	public CliImpl(InterfaceServ referenciaServidor) throws RemoteException, InvalidKeyException, NoSuchAlgorithmException, SignatureException {
		this.id = (UUID.randomUUID().getMostSignificantBits() & Long.MAX_VALUE) % 1000;
		System.out.println("Cliente " + this.id + " criado");
	}
	@Override
	public long getId() {
		return this.id;
	}

	public void setChavePublicaServidor(PublicKey chavePublicaServidor) {
		this.chavePublicaServidor = chavePublicaServidor;
	}

	@Override
	public void notificar(String text, byte[] assinatura) throws RemoteException, InvalidKeyException, NoSuchAlgorithmException, SignatureException {
		verificaMensagem(this.chavePublicaServidor, text, assinatura);
		System.out.println("Sua mensagem eh: " + text);
	}

	@Override
	public void notificar(String text, byte[] assinatura, PublicKey chavePublica) throws RemoteException, InvalidKeyException, NoSuchAlgorithmException, SignatureException {
		this.chavePublicaServidor = chavePublica;
		verificaMensagem(this.chavePublicaServidor, text, assinatura);
		System.out.println("Sua mensagem eh: " + text);

	}

	public void verificaMensagem(PublicKey chavePublica, String mensagem, byte[] assinatura)
			throws NoSuchAlgorithmException, InvalidKeyException, SignatureException {
		Signature serverSig = Signature.getInstance("DSA");
		serverSig.initVerify(chavePublica);
		serverSig.update(mensagem.getBytes());

		if (serverSig.verify(assinatura)) {
			// Mensagem corretamente assinada
			System.out.println("A Mensagem recebida foi assinada corretamente.");
		} else {
			// Mensagem não pode ser validada
			System.out.println("A Mensagem recebida NÃO pode ser validada.");
		}
	}
}
// private PublicKey pubKey;

// public PublicKey getPubKey() {
// return pubKey;
// }

// public void setPubKey(PublicKey pubKey) {
// this.pubKey = pubKey;
// }

// public byte[] geraAssinatura(String mensagem) throws
// NoSuchAlgorithmException,
// InvalidKeyException, SignatureException {
// Signature sig = Signature.getInstance("DSA");

// //Geração das chaves públicas e privadas
// KeyPairGenerator kpg = KeyPairGenerator.getInstance("DSA");
// SecureRandom secRan = new SecureRandom();
// kpg.initialize(512, secRan);
// KeyPair keyP = kpg.generateKeyPair();
// this.pubKey = keyP.getPublic();
// PrivateKey priKey = keyP.getPrivate();

// //Inicializando Obj Signature com a Chave Privada
// sig.initSign(priKey);

// //Gerar assinatura
// sig.update(mensagem.getBytes());
// byte[] assinatura = sig.sign();

// return assinatura;
// }