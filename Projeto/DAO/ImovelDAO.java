package Projeto.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import Projeto.Entidades.Imovel;

public class ImovelDAO {
    public static Connection conexao;

    public void cadastrarImovel(Imovel imovel) {
        String sql = "INSERT INTO imovel (tipo_imovel, endereco_imovel, valor_aluguel, status_imovel, id_cliente) VALUES (?, ?, ?, ?, (SELECT id_cliente FROM cliente WHERE nome = ? LIMIT 1))";
    
        try (PreparedStatement instrucao = conexao.prepareStatement(sql)) {
            // Definindo os valores para o tipo de imóvel, endereço, valor de aluguel, status, e cliente
            instrucao.setString(1, imovel.getTipoImovel());
            instrucao.setString(2, imovel.getEnderecoImovel());
            instrucao.setDouble(3, imovel.getValorAluguel());
            instrucao.setString(4, imovel.getStatusImovel());
            instrucao.setString(5, imovel.getCliente()); // Busca o cliente pelo nome
    
            // Executa o comando de inserção
            instrucao.executeUpdate();
            System.out.println("Imóvel cadastrado com sucesso!");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void listarImovel() {
        // Consulta SQL para listar imóveis e seus clientes
        String sql = "SELECT i.id_imovel, i.tipo_imovel, i.endereco_imovel, i.valor_Aluguel, i.status_imovel, "
                   + "c.nome, c.cpf, c.endereco "
                   + "FROM imovel i "
                   + "LEFT JOIN cliente c ON i.id_cliente = c.id_cliente";
        
        try (PreparedStatement instrucao = conexao.prepareStatement(sql);
             ResultSet consulta = instrucao.executeQuery()) {
             
            boolean imovelPreparados = false;
    
            // Loop para percorrer os resultados dos imóveis e seus respectivos clientes
            while (consulta.next()) {
                imovelPreparados = true;
                int id_imovel = consulta.getInt("id_imovel");
                String tipo_imovel = consulta.getString("tipo_imovel");
                String endereco_imovel = consulta.getString("endereco_imovel");
                double valor_Aluguel = consulta.getDouble("valor_Aluguel");
                String status_imovel = consulta.getString("status_imovel");
    
                // Exibir os dados do imóvel
                System.out.println("DADOS DO IMÓVEL: ");
                System.out.println("ID do imóvel: " + id_imovel);
                System.out.println("Tipo de imóvel: " + tipo_imovel);
                System.out.println("Endereço: " + endereco_imovel);
                System.out.println("Valor de aluguel: " + valor_Aluguel);
                System.out.println("Status do imóvel: " + status_imovel);
    
                // Dados do cliente relacionados ao imóvel
                String nome = consulta.getString("nome");
                if (nome != null) {
                    String cpf = consulta.getString("cpf");
                    String endereco = consulta.getString("endereco");
    
                    System.out.println("\nDADOS DO CLIENTE RELACIONADO: ");
                    System.out.println("Nome: " + nome);
                    System.out.println("CPF: " + cpf);
                    System.out.println("ENDEREÇO: " + endereco);
                    
                } else {
                    System.out.println("Nenhum cliente relacionado a este imóvel.");
                }
                System.out.println("\n");
            }
    
            // Caso não existam imóveis cadastrados
            if (!imovelPreparados) {
                System.out.println("Não há imóveis cadastrados.");
            } else {
                System.out.println("\nTodos os imóveis previamente cadastrados foram listados com sucesso.");
            }
    
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    

    public void editarImovel(Imovel imovel, String coluna, int idImovel) {
        String sql = "UPDATE imovel SET " + coluna + " = ? WHERE id_imovel = ?";
        try (PreparedStatement instrucao = conexao.prepareStatement(sql)) {
            switch (coluna) {
                case "tipo_imovel": // Corrigido o nome da coluna
                    instrucao.setString(1, imovel.getTipoImovel());
                    break;
                case "endereco_imovel":
                    instrucao.setString(1, imovel.getEnderecoImovel());
                    break;
                case "valor_Aluguel":
                    instrucao.setDouble(1, imovel.getValorAluguel());
                    break;
                case "status_imovel":
                    instrucao.setString(1, imovel.getStatusImovel()); // Corrigido o método
                    break;
                default:
                    System.out.println("Coluna inexistente.");
                    return;
            }
    
            instrucao.setInt(2, idImovel);
            int linhasAfetadas = instrucao.executeUpdate();
            if (linhasAfetadas > 0) {
                System.out.println("Imóvel atualizado com sucesso.");
            } else {
                System.out.println("Nenhum imóvel encontrado com o ID fornecido.");
            }
        } catch (SQLException e) {
            System.err.println("Erro ao editar imóvel: " + e.getMessage());
        }
    }

    public void deletarImovel(int idImovel) {
        String sql = "DELETE FROM imovel WHERE id_imovel = ?";
        try (PreparedStatement instrucao = conexao.prepareStatement(sql)) {
            instrucao.setInt(1, idImovel);
            int linhasAfetadas = instrucao.executeUpdate();
            if (linhasAfetadas > 0) {
                System.out.println("Imóvel deletado com sucesso.");
            } else {
                System.out.println("Nenhum imóvel encontrado com este ID.");
            }
        } catch (SQLException e) {
            System.err.println("Erro ao deletar imóvel: " + e.getMessage());
        }
    }
}

