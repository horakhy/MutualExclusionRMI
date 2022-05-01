import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.security.PublicKey;
import java.util.Scanner;

import helloWorld.CliImpl;
import helloWorld.InterfaceServ;

public class Client {
    public static void main(String[] args) throws Exception {
        Registry referenciaServicoNomes = LocateRegistry.getRegistry("localhost", 1099);
        InterfaceServ referenciaServidor = (InterfaceServ) referenciaServicoNomes.lookup("HelloBrudah");
        CliImpl referenciaCliente = new CliImpl(referenciaServidor);

        String estaComRecurso_1;
        String estaComRecurso_2;
        String liberouRecurso_1;
        String liberouRecurso_2;

        System.out.println("Selecione uma opção abaixo:");
        System.out.println();
        System.out.println("+---+------------------------+");
        System.out.println("+ 1 +    Acessar recurso 1   +");
        System.out.println("+---+------------------------+");
        System.out.println("+ 2 +    Acessar recurso 2   +");
        System.out.println("+---+------------------------+");
        System.out.println("+ 3 +    Liberar recurso 1   +");
        System.out.println("+---+------------------------+");
        System.out.println("+ 4 +    Liberar recurso 2   +");
        System.out.println("+---+------------------------+");
        System.out.println("+ 5 +         Sair           +");
        System.out.println("+---+------------------------+");
        System.out.println();

        Scanner scanner = new Scanner(System.in);
        while (true) {

            System.out.println();
            int opt = scanner.nextInt();

            switch (opt) {
                case 1:
                    System.out.println("Acessando recurso 1...");
                    estaComRecurso_1 = referenciaServidor.registrarInteresse(1 + " Yoooo", referenciaCliente, 1);
                    System.out.println(estaComRecurso_1);
                    break;
                case 2:
                    System.out.println("Acessando recurso 2...");
                    estaComRecurso_2 = referenciaServidor.registrarInteresse(2 + " Yaaaa", referenciaCliente, 2);
                    System.out.println(estaComRecurso_2);
                    break;
                case 3:
                    System.out.println("Liberando o recurso 1...");
                    liberouRecurso_1 = referenciaServidor.registrarLiberacao("", referenciaCliente, 1);
                    System.out.println(liberouRecurso_1);
                    break;
                case 4:
                    System.out.println("Liberando o recurso 2...");
                    liberouRecurso_2 = referenciaServidor.registrarLiberacao("", referenciaCliente, 2);
                    System.out.println(liberouRecurso_2);
                    break;
                case 5:
                    System.out.println("Adios.........");
                    System.out.println();
                    scanner.close();
                    System.exit(0);
                default:
                    System.out.println("Opção inválida");
                    System.out.println();
                    break;
            }
        }
    }
}