package helloWorld;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.security.*;
import java.util.*;

public class ServImpl extends UnicastRemoteObject implements InterfaceServ {
  private final String PEGOU_RECURSO_UM = "Você pegou o recurso 1";
  private final String PEGOU_RECURSO_DOIS = "Você pegou o recurso 2";
  private final String RECURSO_UM_OCUPADO = "O recurso 1 está sendo utilizado por outro cliente";
  private final String RECURSO_DOIS_OCUPADO = "O recurso 2 está sendo utilizado por outro cliente";

  private ArrayList<InterfaceCli> clientesEmEspera1;
  private ArrayList<InterfaceCli> clientesEmEspera2;
  private ArrayList<InterfaceCli> clientesQueJáPediramRecurso;
  private boolean recursoDisponível_1 = true;
  private boolean recursoDisponível_2 = true;
  private PublicKey chavePublica;
  private PrivateKey chavePrivada;

  private static final int TEMPO_LIMITE = 5000; // Tempo em milisegundos

  public ServImpl() throws RemoteException {
    super();
    this.clientesEmEspera1 = new ArrayList<InterfaceCli>();
    this.clientesEmEspera2 = new ArrayList<InterfaceCli>();
    this.clientesQueJáPediramRecurso = new ArrayList<InterfaceCli>();
  }

  public PublicKey getChavePublica() {
    return chavePublica;
  }

  public void setChavePublica(PublicKey chavePublica) {
    this.chavePublica = chavePublica;
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
    this.chavePrivada = keyP.getPrivate();

    // Inicializando Obj Signature com a Chave Privada
    sig.initSign(this.chavePrivada);

    // Gerar assinatura
    sig.update(mensagem.getBytes());
    byte[] assinatura = sig.sign();

    return assinatura;
  }

  @Override
  public void registrarInteresse(String text, InterfaceCli referenciaCliente, int numRecurso)
      throws RemoteException, InvalidKeyException, NoSuchAlgorithmException, SignatureException {
    if (clientesEmEspera1.size() == 0 && this.recursoDisponível_1) {
      
      // Cliente pediu recurso pela primeira vez
      if (!clientesQueJáPediramRecurso.contains(referenciaCliente)) {
        byte[] assinatura = this.geraAssinatura(PEGOU_RECURSO_UM);
        referenciaCliente.notificar(PEGOU_RECURSO_UM, assinatura, this.chavePublica);
        this.recursoDisponível_1 = false;
        return;
      }

      // Cliente pediu recurso mais de uma vez
      referenciaCliente.notificar(PEGOU_RECURSO_UM);
      return;
    }
    
    // Adiciona na fila de espera
    clientesEmEspera1.add(referenciaCliente);
    referenciaCliente.notificar(RECURSO_UM_OCUPADO);

  }

  public void enviarResposta(String text, InterfaceCli referenciaCliente) throws RemoteException {
    referenciaCliente.notificar(text);
  }

}
