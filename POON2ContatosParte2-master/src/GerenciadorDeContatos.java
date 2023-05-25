import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.io.*;
import java.time.LocalDate;

public class GerenciadorDeContatos implements Serializable {

    private Map<String, Contato> contatos;
    private Scanner scanner;

    public GerenciadorDeContatos() {
        contatos = new HashMap<String, Contato>();
        scanner = new Scanner(System.in);
    }

    // Resto do código...

    public void serializarContatos() {
        try {
            FileOutputStream fileOut = new FileOutputStream("contatos.ser");
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            Map<String, String> contatosSerializados = new HashMap<>();

            for (Map.Entry<String, Contato> entry : contatos.entrySet()) {
                String nome = entry.getKey();
                Contato contato = entry.getValue();
                String informacoesContato = contato.getNumeroTelefone() + "," + contato.getDataNascimento().toString();
                contatosSerializados.put(nome, informacoesContato);
            }

            out.writeObject(contatosSerializados);
            out.close();
            fileOut.close();
            System.out.println("Contatos serializados com sucesso.");
        } catch (IOException e) {
            System.out.println("Erro ao serializar contatos: " + e.getMessage());
        }
    }


    // Desserializar contatos
    public void deserializarContatos() {
        try {
            FileInputStream fileIn = new FileInputStream("contatos.ser");
            ObjectInputStream in = new ObjectInputStream(fileIn);
            Map<String, String> contatosSerializados = (Map<String, String>) in.readObject();
            in.close();
            fileIn.close();

            contatos.clear(); // Limpa os contatos existentes

            for (Map.Entry<String, String> entry : contatosSerializados.entrySet()) {
                String nome = entry.getKey();
                String informacoesContatoString = entry.getValue();
                String[] informacoesContato = informacoesContatoString.split(",");

                if (informacoesContato.length >= 2) {
                    String numeroTelefone = informacoesContato[0];
                    LocalDate dataNascimento = LocalDate.parse(informacoesContato[1]);
                    Contato contato = new Contato(nome, numeroTelefone, dataNascimento);
                    contatos.put(nome, contato);
                } else {
                    System.out.println("Informações inválidas para o contato: " + nome);
                }
            }

            System.out.println("Contatos desserializados com sucesso.");
        } catch (IOException e) {
            System.out.println("Erro ao desserializar contatos: " + e.getMessage());
        } catch (ClassNotFoundException e) {
            System.out.println("Classe não encontrada: " + e.getMessage());
        }
    }


    public void adicionarContato() {
        System.out.println("Adicionar contato");
        System.out.print("Nome: ");
        String nome = scanner.nextLine();
        System.out.print("Número de telefone: ");
        String numeroTelefone = scanner.nextLine();
        System.out.print("Data de nascimento (formato: yyyy-mm-dd): ");
        LocalDate dataNascimento = LocalDate.parse(scanner.nextLine());
        Contato contato = new Contato(nome, numeroTelefone, dataNascimento);
        contatos.put(nome, contato);
        System.out.println("Contato adicionado: " + contato);
    }

    public void removerContato() {
        System.out.println("Remover contato");
        System.out.print("Nome: ");
        String nome = scanner.nextLine();
        if (contatos.containsKey(nome)) {
            contatos.remove(nome);
            System.out.println("Contato removido: " + nome);
        } else {
            System.out.println("Contato não encontrado");
        }
    }

    public void buscarContato() {
        System.out.println("Buscar contato");
        System.out.print("Nome: ");
        String nome = scanner.nextLine();
        if (contatos.containsKey(nome)) {
            Contato contato = contatos.get(nome);
            System.out.println("Informações do contato:");
            System.out.println("Nome: " + contato.getNome());
            System.out.println("Número de telefone: " + contato.getNumeroTelefone());
            System.out.println("Data de nascimento: " + contato.getDataNascimento());
        } else {
            System.out.println("Contato não encontrado");
        }
    }

    public void listarContatos() {
        System.out.println("Contatos:");
        for (Map.Entry<String, Contato> entry : contatos.entrySet()) {
            Contato contato = entry.getValue();
            System.out.println(contato);
        }
    }
}
