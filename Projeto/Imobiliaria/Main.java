package Projeto.Imobiliaria;
import Projeto.Entidades.*;
import java.util.Scanner;
import Excecoes.DadosInvalidosException;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        try {
            // Cria uma instância de UsuarioAdministrador
            UsuarioAdministrador admin = new UsuarioAdministrador("Admin", "123", "admin", "123");

            // Solicita login e senha do usuário
            System.out.println("Digite o login: ");
            String login = scanner.nextLine();

            System.out.println("Digite a senha: ");
            String senha = scanner.nextLine();

            // Valida o login
            boolean autenticado = admin.validarLogin(login, senha);

            if (autenticado) {
                System.out.println("Bem-vindo, " + admin.getNome() + "!");
                mostrarMenu(scanner);
            } else {
                System.out.println("Falha ao logar.");
            }

        } catch (DadosInvalidosException e) {
            System.out.println("Erro ao criar o administrador: " + e.getMessage());
        } finally {
            scanner.close();
        }
    }

    private static void mostrarMenu(Scanner scanner) throws DadosInvalidosException {
        boolean sair = false;

        while (!sair) {
            System.out.println("\n=== MENU PRINCIPAL ===");
            System.out.println("1. Menu Agenda");
            System.out.println("2. Menu Contrato");
            System.out.println("3. Menu Imóvel");
            System.out.println("4. Menu Cliente");
            System.out.println("5. Sair");
            System.out.print("Escolha uma opção: ");

            int opcao = scanner.nextInt();
            scanner.nextLine(); // Consumir a nova linha

            switch (opcao) {
                case 1:
                    MenuAgenda.main(null); // Chama o menu Agenda
                    break;
                case 2:
                    MenuContrato.main(null); // Chama o menu Contrato
                    break;
                case 3:
                    MenuImovel.main(null);
                    break;
                case 4:
                    MenuCliente.main(null); // Chama o menu Cliente
                    break;
                case 5:
                    sair = true;
                    System.out.println("Saindo...");
                    break;
                default:
                    System.out.println("Opção inválida! Tente novamente.");
            }
        }
    }
}
