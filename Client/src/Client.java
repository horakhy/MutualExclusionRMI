import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import helloWorld.CliImpl;
import helloWorld.InterfaceServ;

public class Client {
    public static void main(String[] args) throws Exception {
        Registry referenciaServicoNomes = LocateRegistry.getRegistry("localhost", 1099);
        InterfaceServ referenciaServidor = (InterfaceServ) referenciaServicoNomes.lookup("HelloBrudah");
        new CliImpl(referenciaServidor);
    }
}
