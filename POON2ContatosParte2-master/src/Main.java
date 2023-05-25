import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        GerenciadorDeContatos gerenciador = new GerenciadorDeContatos();
        Scanner scanner = new Scanner(System.in);

        // Desserializa contatos
        gerenciador.deserializarContatos();

        int opcao = 0;
        while (opcao != 5) {
            System.out.println("\nSelecione uma opção:");
            System.out.println("1 - Adicionar contato");
            System.out.println("2 - Remover contato");
            System.out.println("3 - Buscar contato");
            System.out.println("4 - Listar contatos");
            System.out.println("5 - Sair");

            try {
                opcao = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Opção inválida");
                continue;
            }

            switch (opcao) {
                case 1:
                    gerenciador.adicionarContato();
                    break;
                case 2:
                    gerenciador.removerContato();
                    break;
                case 3:
                    gerenciador.buscarContato();
                    break;
                case 4:
                    gerenciador.listarContatos();
                    break;
                case 5:
                    System.out.println("Encerrando programa...");
                    break;
                default:
                    System.out.println("Opção inválida");
            }
        }

        // Serializa contatos
        gerenciador.serializarContatos();

        scanner.close();
    }
}