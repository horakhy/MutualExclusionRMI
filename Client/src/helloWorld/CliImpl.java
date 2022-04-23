package helloWorld;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.security.*;
import java.util.UUID;

public class CliImpl extends UnicastRemoteObject implements InterfaceCli {
  private long id;
  private PublicKey chavePublica;

  public CliImpl() throws RemoteException {
    this.id = (UUID.randomUUID().getMostSignificantBits() & Long.MAX_VALUE) % 1000;
    // referenciaServidor.registrarInteresse(this.id + " Yoooo", this, 1, 1);
  }

  public PublicKey getChavePublica() {
    return chavePublica;
  }

  public void setChavePublica(PublicKey chavePublica) {
    this.chavePublica = chavePublica;
  }

  public void solicitarRegistroInteresse(InterfaceServ referenciaServidor, PublicKey chavePublica, byte[] assinatura, String msg) throws RemoteException {
    try {
      referenciaServidor.registrarInteresse(this.id + " " + msg, this, 1, chavePublica);
    } catch (RemoteException e) {
      e.printStackTrace();
    }
  }

  public byte[] geraAssinatura(String mensagem) throws NoSuchAlgorithmException,
      InvalidKeyException, SignatureException {
    Signature sig = Signature.getInstance("DSA");

    // Geração das chaves públicas e privadas
    KeyPairGenerator kpg = KeyPairGenerator.getInstance("DSA");
    SecureRandom secRan = new SecureRandom();
    kpg.initialize(512, secRan);
    KeyPair keyP = kpg.generateKeyPair();
    this.chavePublica = keyP.getPublic();
    PrivateKey priKey = keyP.getPrivate();

    // Inicializando Obj Signature com a Chave Privada
    sig.initSign(priKey);

    // Gerar assinatura
    sig.update(mensagem.getBytes());
    byte[] assinatura = sig.sign();

    return assinatura;
  }

  @Override
  public void notificar(String text) {
    System.out.println("Sua mensagem eh: " + text);

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