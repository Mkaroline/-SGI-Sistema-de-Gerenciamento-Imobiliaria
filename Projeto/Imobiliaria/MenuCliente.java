package Projeto.Imobiliaria;
import Projeto.Entidades.UsuarioCliente;
import Projeto.DAO.BancoDeDados;
import Projeto.DAO.UsuarioClienteDAO;

import java.sql.SQLException;
import java.util.Scanner;

public class MenuCliente {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        UsuarioClienteDAO usuarioClienteDAO = new UsuarioClienteDAO();

        try {
            // Conectar ao banco de dados
            BancoDeDados.iniciarConexao();

            System.out.println("Conexão ao banco de dados estabelecida com sucesso!");

            // Menu para escolher as operações
            while (true) {
                System.out.println("\nEscolha uma operação:");
                System.out.println("1. Cadastrar cliente");
                System.out.println("2. Listar clientes");
                System.out.println("3. Editar cliente");
                System.out.println("4. Deletar cliente");
                System.out.println("5. Sair");

                int opcao = scanner.nextInt();
                scanner.nextLine(); // Consumir a nova linha

                switch (opcao) {
                    case 1:
                        cadastrarCliente(scanner, usuarioClienteDAO);
                        break;
                    case 2:
                        usuarioClienteDAO.listarCliente();
                        break;
                    case 3:
                        editarCliente(scanner, usuarioClienteDAO);
                        break;
                    case 4:
                        deletarCliente(scanner, usuarioClienteDAO);
                        break;
                    case 5:
                        BancoDeDados.fecharConexao();
                        System.out.println("Conexão ao banco de dados encerrada.");
                        return;
                    default:
                        System.out.println("Opção inválida!");
                        break;
                }
            }

        } catch (SQLException e) {
            System.err.println("Erro ao conectar ao banco de dados: " + e.getMessage());
        }
    }

    // Método para cadastrar um cliente
    public static void cadastrarCliente(Scanner scanner, UsuarioClienteDAO usuarioClienteDAO) {
        System.out.println("=== Cadastro de Cliente ===");
        System.out.print("Nome: ");
        String nome = scanner.nextLine();
        System.out.print("CPF: ");
        String cpf = scanner.nextLine();
        System.out.print("Endereço: ");
        String endereco = scanner.nextLine();
        System.out.print("Email: ");
        String email = scanner.nextLine();
        System.out.print("Telefone: ");
        int telefone = scanner.nextInt();
        scanner.nextLine(); // Consumir nova linha


        UsuarioCliente novoCliente = new UsuarioCliente(nome, cpf, endereco, email, telefone);
        usuarioClienteDAO.cadastrarCliente(novoCliente);

        
    }

    // Método para editar um cliente
    public static void editarCliente(Scanner scanner, UsuarioClienteDAO usuarioClienteDAO) {
        System.out.println("Informe o nome do cliente a ser editado:");
        String nomeCliente = scanner.nextLine();

        System.out.println("Informe a coluna que deseja editar (nome, cpf, endereco, email, telefone):");
        String coluna = scanner.nextLine();

        UsuarioCliente clienteEditado = new UsuarioCliente(nomeCliente, coluna, nomeCliente, coluna, 0);

        switch (coluna) {
            case "nome":
                System.out.print("Novo nome: ");
                clienteEditado.setNome(scanner.nextLine());
                break;
            case "cpf":
                System.out.print("Novo CPF: ");
                clienteEditado.setCpf(scanner.nextLine());
                break;
            case "endereco":
                System.out.print("Novo Endereço: ");
                clienteEditado.setEndereco(scanner.nextLine());
                break;
            case "email":
                System.out.print("Novo Email: ");
                clienteEditado.setEmail(scanner.nextLine());
                break;
            case "telefone":
                System.out.print("Novo Telefone: ");
                clienteEditado.setTelefone(scanner.nextInt());
                scanner.nextLine(); // Consumir nova linha
                break;
            default:
                System.out.println("Coluna inválida.");
                return;
        }

        usuarioClienteDAO.editarCliente(clienteEditado, coluna, nomeCliente);
    }

    // Método para deletar um cliente
    public static void deletarCliente(Scanner scanner, UsuarioClienteDAO usuarioClienteDAO) {
        System.out.println("Informe o nome do cliente a ser deletado:");
        String nome = scanner.nextLine();

        try {
            usuarioClienteDAO.deletarCliente(nome);
        
        } catch (SQLException e) {
            System.err.println("Erro ao deletar cliente: " + e.getMessage());
        }
    }
    
} 



