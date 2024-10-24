package Projeto.Imobiliaria;

import Projeto.DAO.AgendaDAO;
import Projeto.Entidades.Agenda;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Scanner;

import Excecoes.DadosInvalidosException;

public class MenuAgenda {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        AgendaDAO agendaDAO = new AgendaDAO();

        System.out.println("Conexão ao banco de dados estabelecida com sucesso!");

        // Menu para escolher as operações
        while (true) {
            System.out.println();
            System.out.println("=== GERENCIAR AGENDA ===");
            System.out.println("\nEscolha uma operação:");
            System.out.println("1. Cadastrar Agendamento");
            System.out.println("2. Pesquisar Agendamento por Data");
            System.out.println("3. Listar Agendamentos entre Duas Datas");
            System.out.println("4. Sair");

            int opcao = scanner.nextInt();
            scanner.nextLine(); // Consumir a nova linha

            switch (opcao) {
                case 1:
                    System.out.println();
                    cadastrarAgendamento(scanner, agendaDAO);
                    break;
                case 2:
                    System.out.println();
                    pesquisarAgendamento(scanner);
                    break;
                case 3:
                    System.out.println();
                    listarAgendamentos(scanner, agendaDAO);
                    break;
                case 4:
                    return;
                default:
                    System.out.println("Opção inválida!");
                    break;
            }
        }
    }

    // Método para cadastrar um agendamento
    public static void cadastrarAgendamento(Scanner scanner, AgendaDAO agendaDAO) {
        try {
            System.out.println("=== Cadastro de Agendamento ===");

            System.out.print("Digite a data (dd/MM/yyyy): ");
            String dataStr = scanner.nextLine();
            Date data = new SimpleDateFormat("dd/MM/yyyy").parse(dataStr);

            System.out.print("Digite o imóvel: ");
            String imovel = scanner.nextLine();

            System.out.print("Digite o nome do cliente: ");
            String cliente = scanner.nextLine();

            java.sql.Date sqlDate = new java.sql.Date(data.getTime());
            Agenda agenda = new Agenda(sqlDate, imovel, cliente);
            agendaDAO.cadastrarAgenda(agenda);
        } catch (ParseException e) {
            System.err.println("Erro ao converter data: " + e.getMessage());
        } catch (DadosInvalidosException e) {
            System.err.println("Dados inválidos: " + e.getMessage());
        }
    }

    // Método para pesquisar um agendamento por data
    public static void pesquisarAgendamento(Scanner scanner) {
        System.out.print("Digite a data (dd/MM/yyyy) para pesquisa: ");
        String dataStr = scanner.nextLine();
        try {
            LocalDate dataPesquisa = new SimpleDateFormat("dd/MM/yyyy").parse(dataStr).toInstant()
                    .atZone(ZoneId.systemDefault()).toLocalDate();
            AgendaDAO.pesquisarNaAgenda(dataPesquisa);
        } catch (ParseException e) {
            System.err.println("Erro ao converter data: " + e.getMessage());
        }
    }

    // Método para listar agendamentos entre duas datas
    public static void listarAgendamentos(Scanner scanner, AgendaDAO agendaDAO) {
        try {
            System.out.print("Digite a data de início (dd/MM/yyyy): ");
            String dataInicioStr = scanner.nextLine();
            LocalDate dataInicio = new SimpleDateFormat("dd/MM/yyyy").parse(dataInicioStr).toInstant()
                    .atZone(ZoneId.systemDefault()).toLocalDate();

            System.out.print("Digite a data de fim (dd/MM/yyyy): ");
            String dataFimStr = scanner.nextLine();
            LocalDate dataFim = new SimpleDateFormat("dd/MM/yyyy").parse(dataFimStr).toInstant()
                    .atZone(ZoneId.systemDefault()).toLocalDate();

            agendaDAO.listarAgenda(dataInicio, dataFim);
        } catch (ParseException e) {
            System.err.println("Erro ao converter data: " + e.getMessage());
        }
    }
}
