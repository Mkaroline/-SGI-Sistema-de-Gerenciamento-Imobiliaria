package Projeto.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import Projeto.Entidades.UsuarioCliente;


public class UsuarioClienteDAO {
    String sql;
    public static Connection conexao;
    public void cadastrarCliente(UsuarioCliente cliente) {
        sql = "INSERT INTO cliente (nome, cpf, endereco, email, telefone, login, senha) VALUES (?, ?, ?, ?, ?, ?, ?)";
        try {
            PreparedStatement instrucao = conexao.prepareStatement(sql);
            instrucao.setString(1, cliente.getNome());
            instrucao.setString(2, cliente.getCpf());
            instrucao.setString(3, cliente.getEndereco());
            instrucao.setString(4, cliente.getEmail());
            instrucao.setLong(5, cliente.getTelefone()); 
            instrucao.setString(6, cliente.getLogin());
            instrucao.setString(7, cliente.getSenha());  
            instrucao.executeUpdate();
            System.out.println("Cliente cadastrado com sucesso!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    public boolean validarLoginCliente(String login, String senha) {
        String sql = "SELECT * FROM cliente WHERE login = ? AND senha = ?";
        try {
            PreparedStatement instrucao = conexao.prepareStatement(sql);
            instrucao.setString(1, login);
            instrucao.setString(2, senha);
            ResultSet resultado = instrucao.executeQuery();
    
            if (resultado.next()) {
                return true; 
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false; 
    }
    
    
    public void listarCliente() {
       
        sql = "SELECT * FROM cliente";
        try {
            PreparedStatement instrucao = conexao.prepareStatement(sql);
            ResultSet consulta = instrucao.executeQuery();
            boolean clientesEncontrados = false;
            while (consulta.next()) {
                clientesEncontrados = true;
              
                String nome = consulta.getString("nome");
                String cpf = consulta.getString("Cpf");
                String endereco = consulta.getString("endereco");
                String email = consulta.getString("Email"); 
                int telefone = consulta.getInt("Telefone");
                String login = consulta.getString("login");
                String senha = consulta.getString("senha");

                System.out.println("DADOS DO CLIENTE");
                System.out.println("Nome: " + nome);
                System.out.println("Cpf: " + cpf);
                System.out.println("Endereço: " + endereco);
                System.out.println("Email: " + email);
                System.out.println("Telefone: " + telefone);
                System.out.println("Login: " + login);
                System.out.println("senha: " + senha);
                System.out.println("\n");
            }
            if (!clientesEncontrados) {
                System.out.println("Nenhum cliente encontrado");
            } else {
                System.out.println("Todos os clientes previamente cadastrados foram listados com sucesso.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void editarCliente(UsuarioCliente cliente, String coluna, String nome) {
       
        sql = "UPDATE cliente SET " + coluna + " = ? WHERE nome = ?";
        try {
          
            PreparedStatement verificaNome = conexao.prepareStatement("SELECT COUNT(*) AS total FROM cliente WHERE nome = ?");
            verificaNome.setString(1, nome);
            ResultSet resultado = verificaNome.executeQuery();
            resultado.next();
            int total = resultado.getInt("total");
            if (total == 0) {
                System.out.println("\nNão foi possível editar o cliente, pois não existe o nome inserido\n");
                return;
            }

            PreparedStatement instrucao = conexao.prepareStatement(sql);

            switch (coluna) {  
                
                case "nome":
                    instrucao.setString(1, cliente.getNome());
                    System.out.println("\nCampo nome do cliente editado com sucesso\n");
                    break;
                    case "cpf":
                    instrucao.setString(1, cliente.getCpf());
                    System.out.println("\nCampo Cpf editado com sucesso\n");
                    break;
                case "endereco":
                    instrucao.setString(1, cliente.getEndereco());
                    System.out.println("\nCampo endereço editado com sucesso\n");
                    break;
                case "email":
                    instrucao.setString(1, cliente.getEmail());
                    System.out.println("\nCampo Email editado com sucesso\n");
                    break;
                case "telefone":
                    instrucao.setInt(1, cliente.getTelefone());
                    System.out.println("\nCampo Telefone editado com sucesso\n");
                    break;
                default:
                    System.out.println("\nColuna inexistente no banco de dados\n");
                    return;
            }

            instrucao.setString(2, nome);
            instrucao.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deletarCliente(String nome) throws SQLException {
        String sql = "DELETE FROM cliente WHERE nome = ?";
        try (PreparedStatement instrucao = conexao.prepareStatement(sql)) {
            instrucao.setString(1, nome);
            int linhasAfetadas = instrucao.executeUpdate();
            if (linhasAfetadas == 0) {
                throw new SQLException("Nenhum cliente encontrado com este nome.");
            } else {
                System.out.println("Cliente deletado com sucesso.");
            }
        } catch (SQLException e) {
            System.err.println("Erro ao deletar o cliente: " + e.getMessage());
            e.printStackTrace();
        }
    }
    
    public UsuarioCliente buscarClientePorLogin(String login) throws SQLException {
        String sql = "SELECT * FROM cliente WHERE login = ?";
        try (PreparedStatement instrucao = conexao.prepareStatement(sql)) {
            instrucao.setString(1, login);
            ResultSet consulta = instrucao.executeQuery();
    
            if (consulta.next()) {
            
                UsuarioCliente cliente = new UsuarioCliente(sql, sql, sql, sql, 0, login, sql);
                cliente.setNome(consulta.getString("nome"));
                cliente.setCpf(consulta.getString("Cpf"));
                cliente.setEndereco(consulta.getString("endereco"));
                cliente.setEmail(consulta.getString("Email"));
                cliente.setTelefone(consulta.getInt("Telefone"));
                cliente.setLogin(consulta.getString("login"));
                cliente.setSenha(consulta.getString("senha")); 
    
                return cliente;
            }
        }
        return null; 
    }
    
}
