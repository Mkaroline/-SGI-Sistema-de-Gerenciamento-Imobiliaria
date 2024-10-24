package Projeto.Imobiliaria;

import Projeto.DAO.UsuarioClienteDAO;
import Projeto.DAO.AgendaDAO;
import Projeto.DAO.BancoDeDados;
import Projeto.Entidades.UsuarioAdministrador;
import Projeto.Entidades.UsuarioCliente;
import java.sql.SQLException;
import java.util.Scanner;
import Excecoes.DadosInvalidosException;

public class Main {

    public static void main(String[] args) throws DadosInvalidosException {
        Scanner scanner = new Scanner(System.in);
        UsuarioClienteDAO clienteDAO = new UsuarioClienteDAO();
        UsuarioAdministrador admin = new UsuarioAdministrador("admin", "123", "admin", "123"); // Administrador de exemplo

        try {
            // Conectar ao banco de dados
            BancoDeDados.iniciarConexao();

            boolean continuar = true; // Controle do loop principal
            while (continuar) {
                System.out.println("=== BEM-VINDO AO SISTEMA DE GERENCIAMENTO IMOBILIÁRIO ===");
                System.out.println();
                System.out.println("1. ADMINISTRADOR");
                System.out.println("2. USUARIO CLIENTE");
                System.out.println("3. Sair");
                System.out.print("Opção: ");
                int opcaoLogin = scanner.nextInt();
                scanner.nextLine(); // Consumir a linha após o número

                switch (opcaoLogin) {
                    case 1:
                        // Login de Administrador
                        realizarLoginAdmin(scanner, admin);
                        break;

                    case 2:
                        // Login de Cliente
                        realizarLoginCliente(scanner, clienteDAO);
                        break;

                    case 3:
                        continuar = false; // Sair do loop
                        BancoDeDados.fecharConexao();
                        System.out.println("Saindo do sistema...");
                        break;

                    default:
                        System.out.println("Opção inválida. Tente novamente.");
                }
            }

        } catch (SQLException e) {
            System.err.println("Erro ao conectar ao banco de dados: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Erro: " + e.getMessage());
        } 
    }

    // Método para login de Administrador
    private static void realizarLoginAdmin(Scanner scanner, UsuarioAdministrador admin) throws DadosInvalidosException {
        boolean sair = false; // Controle do loop do menu do administrador

        while (!sair) {
            System.out.println("=== ADMINISTRADOR ===");
            System.out.print("LOGIN: ");
            String loginAdmin = scanner.nextLine();

            System.out.print("SENHA: ");
            String senhaAdmin = scanner.nextLine();

            boolean autenticadoAdmin = admin.validarLogin(loginAdmin, senhaAdmin);

            if (autenticadoAdmin) {
                System.out.println("SEJA BEM VINDO, " + admin.getNome() + "!");
                mostrarMenuAdministrador(scanner); // Exibe o menu do administrador
                sair = true; // Sai do loop de login após sucesso
            } else {
                System.out.println("Falha ao logar como administrador. Verifique suas credenciais.");
            }
        }
    }

    // Método para login de Cliente
    private static void realizarLoginCliente(Scanner scanner, UsuarioClienteDAO clienteDAO) {
        int tentativas = 0; // Contador de tentativas
        boolean sair = false; // Controle do loop do menu do cliente
    
        while (!sair && tentativas < 3) {
            System.out.println("=== CLIENTE ===");
            System.out.print("LOGIN: ");
            String loginCliente = scanner.nextLine();
    
            System.out.print("SENHA: ");
            String senhaCliente = scanner.nextLine();
    
            boolean autenticadoCliente = clienteDAO.validarLoginCliente(loginCliente, senhaCliente);
    
            if (autenticadoCliente) {
                System.out.println("SEJA BEM VINDO!");
                mostrarMenuCliente(scanner, clienteDAO); // Exibe o menu do cliente
                sair = true; // Sai do loop de login após sucesso
            } else {
                tentativas++; // Incrementa o contador de tentativas
                System.out.println("Falha ao logar como cliente.");
                if (tentativas < 3) {
                    System.out.println((3 - tentativas) + " tentativas restantes.");
                } else {
                    System.out.println("Número máximo de tentativas alcançado.");
                    System.exit(0); // Encerra o programa
                }
            }
        }
    }
    

    // Menu do Administrador
    private static void mostrarMenuAdministrador(Scanner scanner) throws DadosInvalidosException {
        boolean sair = false;

        while (!sair) {
            System.out.println("\n=== SISTEMA DE GERENCIAMENTO IMOBILIARIO (ADMINISTRADOR) ===");
            System.out.println("1. GERENCIAR AGENDA");
            System.out.println("2. GERENCIAR CONTRATO");
            System.out.println("3. GERENCIAR IMÓVEL");
            System.out.println("4. GERENCIAR CLIENTE");
            System.out.println("5. SAIR");
            System.out.print("Escolha uma opção: ");

            int opcao = scanner.nextInt();
            scanner.nextLine(); // Consumir nova linha

            switch (opcao) {
                case 1:
                    MenuAgenda.main(null); // Chama o menu de agenda
                    break;
                case 2:
                    MenuContrato.main(null); // Chama o menu de contrato
                    break;
                case 3:
                    MenuImovel.main(null); // Chama o menu de imóvel
                    break;
                case 4:
                    MenuCliente.main(null); // Chama o menu de cliente
                    break;
                case 5:
                    sair = true; // Sair do menu do administrador
                    break;
                default:
                    System.out.println("Opção inválida! Tente novamente.");
            }
        }
    }

    // Menu do Cliente
    private static void mostrarMenuCliente(Scanner scanner, UsuarioClienteDAO clienteDAO) {
        AgendaDAO agendaDAO = new AgendaDAO();
        boolean sair = false;

        while (!sair) {
            System.out.println();
            System.out.println("\n=== SISTEMA DE GERENCIAMENTO IMOBILIARIO (CLIENTE) ===");
            System.out.println("1. Consultar Agendamentos");
            System.out.println("2. Visualizar Informações");
            System.out.println("3. Sair");
            System.out.print("Escolha uma opção: ");

            int opcao = scanner.nextInt();
            scanner.nextLine(); // Consumir nova linha

            switch (opcao) {
                case 1:
                    System.out.println();
                    System.out.println("Consultar Agendamentos");
                    consultarAgendamentosDoCliente(scanner, agendaDAO);
                    break;
                case 2:
                    System.out.println();
                    System.out.println("Visualizando suas informações:");
                    visualizarInformacoesCliente(scanner, clienteDAO);
                    break;
                case 3:
                    sair = true; // Sair do menu do cliente
                    break;
                default:
                    System.out.println("Opção inválida! Tente novamente.");
            }
        }
    }

    // Método para visualizar informações do cliente
    private static void visualizarInformacoesCliente(Scanner scanner, UsuarioClienteDAO clienteDAO) {
        System.out.print("Digite seu login: ");
        String loginCliente = scanner.nextLine();

        try {
            // Supondo que o método clienteDAO.buscarClientePorLogin(login) retorne um objeto UsuarioCliente
            UsuarioCliente cliente = clienteDAO.buscarClientePorLogin(loginCliente);
            
            if (cliente != null) {
                System.out.println("DADOS DO CLIENTE");
                System.out.println("Nome: " + cliente.getNome());
                System.out.println("Cpf: " + cliente.getCpf());
                System.out.println("Endereço: " + cliente.getEndereco());
                System.out.println("Email: " + cliente.getEmail());
                System.out.println("Telefone: " + cliente.getTelefone());
                System.out.println("Login: " + cliente.getLogin());
                // Não exibir a senha por motivos de segurança
                System.out.println("\n");
            } else {
                System.out.println("Cliente não encontrado.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Erro ao buscar informações do cliente.");
        }
    } 

    // Método para consultar agendamentos do cliente
    private static void consultarAgendamentosDoCliente(Scanner scanner, AgendaDAO agendaDAO) {
        System.out.println();
        System.out.print("Digite seu ID de cliente para consultar agendamentos: ");
        int idCliente = scanner.nextInt();
        scanner.nextLine(); 
        
        agendaDAO.pesquisarAgendamentosPorCliente(idCliente); // Chamar método para pesquisar na agenda
    }
}