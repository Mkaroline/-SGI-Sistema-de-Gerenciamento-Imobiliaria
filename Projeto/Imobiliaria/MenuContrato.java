package Projeto.Imobiliaria;
import Projeto.Entidades.Contrato;
import Projeto.DAO.ContratoDAO;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class MenuContrato {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ContratoDAO contratoDAO = new ContratoDAO();


            // Menu para escolher as operações
            while (true) {
                System.out.println();
                System.out.println("=== GERENCIAR CONTRATO ===");
                System.out.println("\nEscolha uma operação:");
                System.out.println("1. Cadastrar contrato");
                System.out.println("2. Listar contratos");
                System.out.println("3. Editar contrato");
                System.out.println("4. Deletar contrato");
                System.out.println("5. Sair");

                int opcao = scanner.nextInt();
                scanner.nextLine(); // Consumir a nova linha

                switch (opcao) {
                    case 1:
                        System.out.println();
                        cadastrarContrato(scanner, contratoDAO);
                        break;
                    case 2:
                        System.out.println();
                        contratoDAO.listarContratos();
                        break;
                    case 3:
                        System.out.println();
                        editarContrato(scanner, contratoDAO);
                        break;
                    case 4:
                        System.out.println();
                        deletarContrato(scanner, contratoDAO);
                        break;
                    case 5:
                        
                        return;
                    default:
                        System.out.println("Opção inválida!");
                        break;
                }
            }

    }

    // Método para cadastrar um contrato
    public static void cadastrarContrato(Scanner scanner, ContratoDAO contratoDAO) {
        try {
            

            System.out.println("Informe a data de início (dd/MM/yyyy):");
            String dataInicioStr = scanner.nextLine();
            Date dataInicio = new SimpleDateFormat("dd/MM/yyyy").parse(dataInicioStr);

            System.out.println("Informe a data de fim (dd/MM/yyyy):");
            String dataFimStr = scanner.nextLine();
            Date dataFim = new SimpleDateFormat("dd/MM/yyyy").parse(dataFimStr);

            System.out.println("Informe o valor do contrato:");
            double valor = scanner.nextDouble();
            scanner.nextLine(); // Consumir nova linha

            System.out.println("Informe o status do contrato:");
            String status = scanner.nextLine();

            System.out.println("O contrato está ativo? (true/false):");
            boolean ativo = scanner.nextBoolean();

            Contrato contrato = new Contrato(dataInicio, dataFim, valor, status, ativo);
            contratoDAO.cadastrarContrato(contrato);
        } catch (ParseException e) {
            System.err.println("Erro ao converter data: " + e.getMessage());
        }
    }

    //  Método para editar um contrato
    public static void editarContrato(Scanner scanner, ContratoDAO contratoDAO) {
        System.out.println("Informe o ID do contrato a ser editado:");
        int idContrato = scanner.nextInt();
        scanner.nextLine(); // Consumir nova linha

        System.out.println("Informe a coluna que deseja editar (data_inicio, data_fim, valor, status, ativo):");
        String coluna = scanner.nextLine();

        Contrato contrato = new Contrato(null, null, idContrato, coluna, false);// Criando o contrato sem preencher todos os dados ainda
      

        switch (coluna) {
            case "data_inicio":
                System.out.println("Informe a nova data de início (dd/MM/yyyy):");
                try {
                    String novaDataInicioStr = scanner.nextLine();
                    Date novaDataInicio = new SimpleDateFormat("dd/MM/yyyy").parse(novaDataInicioStr);
                    contrato.setData_inicio(novaDataInicio);
                } catch (ParseException e) {
                    System.err.println("Erro ao converter data: " + e.getMessage());
                    return;
                }
                break;
            case "data_fim":
                System.out.println("Informe a nova data de fim (dd/MM/yyyy):");
                try {
                    String novaDataFimStr = scanner.nextLine();
                    Date novaDataFim = new SimpleDateFormat("dd/MM/yyyy").parse(novaDataFimStr);
                    contrato.setData_fim(novaDataFim);
                } catch (ParseException e) {
                    System.err.println("Erro ao converter data: " + e.getMessage());
                    return;
                }
                break;
            case "valor":
                System.out.println("Informe o novo valor:");
                double novoValor = scanner.nextDouble();
                contrato.setValor(novoValor);
                scanner.nextLine(); // Consumir nova linha
                break;
            case "status":
                System.out.println("Informe o novo status:");
                String novoStatus = scanner.nextLine();
                contrato.setStatus(novoStatus);
                break;
            case "ativo":
                System.out.println("O contrato está ativo? (true/false):");
                boolean novoAtivo = scanner.nextBoolean();
                contrato.setAtivo(novoAtivo);
                scanner.nextLine(); // Consumir nova linha
                break;
            default:
                System.out.println("Coluna inválida.");
                return;
        }

        contratoDAO.editarContrato(contrato, coluna, idContrato);
    }
 
    // Método para deletar um contrato
    public static void deletarContrato(Scanner scanner, ContratoDAO contratoDAO) {
        System.out.println("Informe o ID do contrato a ser deletado:");
        int idContrato = scanner.nextInt();
        scanner.nextLine(); // Consumir nova linha

        try {
            contratoDAO.deletarContrato(idContrato);
        } catch (SQLException e) {
            System.err.println("Erro ao deletar contrato: " + e.getMessage());
        }
    }
}
