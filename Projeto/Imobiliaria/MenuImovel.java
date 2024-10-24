package Projeto.Imobiliaria;

import Projeto.DAO.ImovelDAO;
import Projeto.Entidades.Imovel;
import java.util.Scanner;

import Excecoes.DadosInvalidosException;

public class MenuImovel {
    public static void main(String[] args) throws DadosInvalidosException {
        Scanner scanner = new Scanner(System.in);
        ImovelDAO imovelDAO = new ImovelDAO();

            // Menu para escolher as operações
            while (true) {
                System.out.println();
                System.out.println("=== GERENCIAR IMOVEL ===");
                System.out.println("\nEscolha uma operação:");
                System.out.println("1. Cadastrar imóvel");
                System.out.println("2. Listar imóveis");
                System.out.println("3. Editar imóvel");
                System.out.println("4. Deletar imóvel");
                System.out.println("5. Sair");

                int opcao = scanner.nextInt();
                scanner.nextLine(); // Consumir a nova linha

                switch (opcao) {
                    case 1:
                        System.out.println();
                        cadastrarImovel(scanner, imovelDAO);
                        break;
                    case 2:
                        System.out.println();
                        imovelDAO.listarImovel();
                        break;
                    case 3:
                        System.out.println();
                        editarImovel(scanner, imovelDAO);
                        break;
                    case 4:
                        System.out.println();
                        deletarImovel(scanner, imovelDAO);
                        break;
                    case 5:
                    
                        return;
                    default:
                        System.out.println("Opção inválida!");
                        break;
                }
            }

       
    }

    // Método para cadastrar um imóvel
    public static void cadastrarImovel(Scanner scanner, ImovelDAO imovelDAO) throws DadosInvalidosException {
        System.out.println("=== Cadastro de Imóvel ===");
        
        System.out.print("Tipo de Imóvel: ");
        String tipo_imovel = scanner.nextLine();
        
        System.out.print("Endereço: ");
        String endereco_imovel = scanner.nextLine();
        
        System.out.print("Valor do Aluguel: ");
        double valor_Aluguel = scanner.nextDouble();
        scanner.nextLine(); // Consumir nova linha
        
        System.out.print("Cliente: ");
        String cliente = scanner.nextLine();

        System.out.print("Status: ");
        String status_imovel = scanner.nextLine();
        
        // Criando o objeto Imovel com os parâmetros informados
        Imovel novoImovel = new Imovel(tipo_imovel, endereco_imovel, valor_Aluguel, cliente, status_imovel);
        
        // Cadastrando o imóvel no banco de dados
        imovelDAO.cadastrarImovel(novoImovel);
    }
    
    // Método para editar um imóvel
    public static void editarImovel(Scanner scanner, ImovelDAO imovelDAO) throws DadosInvalidosException {
        System.out.println("Informe o ID do imóvel a ser editado:");
        int idImovel = scanner.nextInt();
        scanner.nextLine(); // Consumir nova linha

        System.out.println("Informe a coluna que deseja editar (tipo_imovel, endereco_imovel, valor_Aluguel, status_imovel):");
        String coluna = scanner.nextLine();

        Imovel imovel = new Imovel(coluna, coluna, idImovel, coluna, coluna); // Criando o objeto imóvel

        switch (coluna) {
            case "tipo_imovel":
                System.out.println("Informe o novo tipo de imóvel:");
                String novoTipoImovel = scanner.nextLine();
                imovel.setTipoImovel(novoTipoImovel);
                break;
            case "endereco_imovel":
                System.out.println("Informe o novo endereço:");
                String novoEnderecoImovel = scanner.nextLine();
                imovel.setEnderecoImovel(novoEnderecoImovel);
                break;
            case "valor_Aluguel":
                System.out.println("Informe o novo valor do aluguel:");
                double novoValorAluguel = scanner.nextDouble();
                imovel.setValorAluguel(novoValorAluguel);
                scanner.nextLine(); // Consumir nova linha
                break;
            case "status_imovel":
                System.out.println("Informe o novo status:");
                String novoStatus = scanner.nextLine();
                imovel.setStatusImovel(novoStatus);
                break;
            default:
                System.out.println("Coluna inválida.");
                return;
        }

        imovelDAO.editarImovel(imovel, coluna, idImovel);
    }

    // Método para deletar um imóvel
    public static void deletarImovel(Scanner scanner, ImovelDAO imovelDAO) {
        System.out.println("Informe o ID do imóvel a ser deletado:");
        int idImovel = scanner.nextInt();
        scanner.nextLine(); // Consumir nova linha

        imovelDAO.deletarImovel(idImovel);
    }
}
