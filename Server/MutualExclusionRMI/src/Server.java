import java.rmi.registry.*;

import helloWorld.InterfaceServ;
import helloWorld.ServImpl;
import java.security.PublicKey;

public class Server {
    public static void main(String[] args) throws Exception {
        Registry referenciaServicoNomes = LocateRegistry.createRegistry(1099);
        InterfaceServ referenciaServidor = new ServImpl();
        ServImpl server = new ServImpl();

        referenciaServicoNomes.rebind("HelloBrudah", referenciaServidor);

        
        int numRecurso = 1;

        PublicKey chavePublica = server.getChavePublica();
    }
}
