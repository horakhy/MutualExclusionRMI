import java.rmi.registry.*;

import helloWorld.InterfaceServ;
import helloWorld.ServImpl;

public class Server {
    public static void main(String[] args) throws Exception {
        Registry referenciaServicoNomes = LocateRegistry.createRegistry(1099);
        InterfaceServ referenciaServidor = new ServImpl();
        referenciaServicoNomes.rebind("HelloBrudah", referenciaServidor);
    
    }
}
