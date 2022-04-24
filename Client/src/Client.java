import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.security.PublicKey;

import helloWorld.CliImpl;
import helloWorld.InterfaceServ;

public class Client {
    public static void main(String[] args) throws Exception {
        Registry referenciaServicoNomes = LocateRegistry.getRegistry("localhost", 1099);
        String msg = "Teste";
        InterfaceServ referenciaServidor = (InterfaceServ) referenciaServicoNomes.lookup("HelloBrudah");
        
        CliImpl client = new CliImpl();
        byte[] assinatura = client.geraAssinatura(msg);

        PublicKey chavePublica = client.getChavePublica();
        // System.out.println("Chave Publica: " + chavePublica.toString());
        int numRecurso = 1;
        
        client.solicitarRegistroInteresse(referenciaServidor, chavePublica, assinatura, numRecurso, msg);
    }
}
