import java.rmi.registry.*;

import mutualExclusion.InterfaceServ;
import mutualExclusion.ServImpl;

public class Server {
    public static void main(String[] args) throws Exception {
        Registry referenciaServicoNomes = LocateRegistry.createRegistry(1099);
        InterfaceServ referenciaServidor = new ServImpl();
        new ServImpl();

        referenciaServicoNomes.rebind("HelloBrudah", referenciaServidor);
        System.out.println("Servidor pronto");
    }
}
