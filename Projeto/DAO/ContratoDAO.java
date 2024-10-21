package Projeto.DAO;

import Projeto.Entidades.Contrato;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ContratoDAO {
    String sql;
    public static Connection conexao;

    // Método para cadastrar um novo contrato
    public void cadastrarContrato(Contrato contrato) {
        sql = "INSERT INTO contratos (data_inicio, data_fim, valor, status, ativo) VALUES (?,?,?,?,?)";
        try (PreparedStatement instrucao = conexao.prepareStatement(sql)) {
            instrucao.setDate(1, new java.sql.Date(contrato.getData_inicio().getTime()));
            instrucao.setDate(2, new java.sql.Date(contrato.getData_fim().getTime()));
            instrucao.setDouble(3, contrato.getValor());
            instrucao.setString(4, contrato.getStatus());
            instrucao.setBoolean(5, contrato.isAtivo());
            instrucao.executeUpdate();
            System.out.println("Contrato cadastrado com sucesso!");
        } catch (SQLException e) {
            System.err.println("Erro ao cadastrar o contrato: " + e.getMessage());
            e.printStackTrace();
        }
    }

    // Método para listar todos os contratos
    public void listarContratos() {
        sql = "SELECT * FROM contratos";
        try (PreparedStatement instrucao = conexao.prepareStatement(sql);
             ResultSet consulta = instrucao.executeQuery()) {

            boolean contratosEncontrados = false;
            while (consulta.next()) {
                contratosEncontrados = true;
                int id = consulta.getInt("id");
                java.sql.Date data_inicio = consulta.getDate("data_inicio");
                java.sql.Date data_fim = consulta.getDate("data_fim");
                double valor = consulta.getDouble("valor");
                String status = consulta.getString("status");
                boolean ativo = consulta.getBoolean("ativo");

                System.out.println("DADOS DO CONTRATO");
                System.out.println("ID: " + id);
                System.out.println("Data de Início: " + data_inicio);
                System.out.println("Data de Fim: " + data_fim);
                System.out.println("Valor: " + valor);
                System.out.println("Status: " + status);
                System.out.println("Ativo: " + ativo);
                System.out.println("\n");
            }
            if (!contratosEncontrados) {
                System.out.println("Nenhum contrato encontrado");
            } else {
                System.out.println("Todos os contratos foram listados com sucesso.");
            }
        } catch (SQLException e) {
            System.err.println("Erro ao listar contratos: " + e.getMessage());
            e.printStackTrace();
        }
    }

    // Método para editar um contrato
    public void editarContrato(Contrato contrato, String coluna, int idContrato) {
        if (!coluna.matches("data_inicio|data_fim|valor|status|ativo")) {
            System.out.println("Coluna inválida.");
            return;
        }

        sql = "UPDATE contratos SET " + coluna + " = ? WHERE id = ?";
        try (PreparedStatement instrucao = conexao.prepareStatement(sql)) {
            switch (coluna) {
                case "data_inicio":
                    instrucao.setDate(1, new java.sql.Date(contrato.getData_inicio().getTime()));
                    break;
                case "data_fim":
                    instrucao.setDate(1, new java.sql.Date(contrato.getData_fim().getTime()));
                    break;
                case "valor":
                    instrucao.setDouble(1, contrato.getValor());
                    break;
                case "status":
                    instrucao.setString(1, contrato.getStatus());
                    break;
                case "ativo":
                    instrucao.setBoolean(1, contrato.isAtivo());
                    break;
                default:
                    System.out.println("Coluna inexistente.");
                    return;
            }

            instrucao.setInt(2, idContrato);
            instrucao.executeUpdate();
            System.out.println("Contrato atualizado com sucesso!");
        } catch (SQLException e) {
            System.err.println("Erro ao editar o contrato: " + e.getMessage());
            e.printStackTrace();
        }
    }

    // Método para deletar um contrato
    public void deletarContrato(int idContrato) throws SQLException {
        sql = "DELETE FROM contratos WHERE id = ?";
        try (PreparedStatement instrucao = conexao.prepareStatement(sql)) {
            instrucao.setInt(1, idContrato);
            int linhasAfetadas = instrucao.executeUpdate();
            if (linhasAfetadas == 0) {
                throw new SQLException("Nenhum contrato encontrado com este ID.");
            } else {
                System.out.println("Contrato deletado com sucesso.");
            }
        } catch (SQLException e) {
            System.err.println("Erro ao deletar o contrato: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
