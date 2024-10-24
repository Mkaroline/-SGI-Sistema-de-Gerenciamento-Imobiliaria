package Projeto.DAO;

import java.sql.Connection;
import Projeto.Entidades.Agenda;
import java.sql.SQLException;
import java.time.LocalDate;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class AgendaDAO {
    public static Connection conexao;
    String sql;

    // Método para cadastrar um agendamento
    public void cadastrarAgenda(Agenda agenda) {
        sql = "INSERT INTO agenda (data, imovel, id_cliente) VALUES (?, ?, (SELECT id_cliente FROM cliente WHERE nome = ? LIMIT 1))";

        try (PreparedStatement instrucao = conexao.prepareStatement(sql)) {
            instrucao.setDate(1, agenda.getData());
            instrucao.setString(2, agenda.getImovel());
            instrucao.setString(3, agenda.getCliente());
            instrucao.executeUpdate();
            System.out.println("Agendamento cadastrado com sucesso!");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    // Método para pesquisar na agenda por data
    public static void pesquisarNaAgenda(LocalDate data) {
        String sql = "SELECT a.data, a.imovel, c.id_cliente, c.nome, c.cpf, c.endereco, c.email, c.telefone " +
                "FROM agenda a INNER JOIN cliente c ON a.id_cliente = c.id_cliente WHERE a.data = ?";
    
        try (PreparedStatement instrucao = conexao.prepareStatement(sql)) {
            instrucao.setDate(1, java.sql.Date.valueOf(data));
            ResultSet consulta = instrucao.executeQuery();

            boolean encontrouCompromissos = false;

            while (consulta.next()) {
                encontrouCompromissos = true;
                LocalDate dataEvento = consulta.getDate("data").toLocalDate();
                String imovel = consulta.getString("imovel");
                int id_cliente = consulta.getInt("id_cliente");
                String nome = consulta.getString("nome");
                String cpf = consulta.getString("cpf");
                String endereco = consulta.getString("endereco");
                String email = consulta.getString("email");
                String telefone = consulta.getString("telefone");

                System.out.println("\nAgendamento: ");
                System.out.println("Data: " + dataEvento);
                System.out.println("Cliente: " + id_cliente);
                System.out.println("Imóvel: " + imovel);
                System.out.println("\nDados do cliente que agendou o imóvel: ");
                System.out.println("Nome do cliente: " + (nome != null ? nome : "Este imóvel não possui um cliente cadastrado"));
                System.out.println("CPF do Cliente: " + cpf);
                System.out.println("Endereço do Cliente: " + endereco);
                System.out.println("Email do Cliente: " + email);
                System.out.println("Telefone: " + telefone);
            }

            if (!encontrouCompromissos) {
                System.out.println("Não há compromissos agendados para a data informada.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Método para listar agenda entre duas datas
    public void listarAgenda(LocalDate data_inicio, LocalDate data_fim) {
        if (data_inicio.isAfter(data_fim)) {
            System.out.println("A data de início deve ser anterior à data de fim.");
            return;
        }

        String sql = "SELECT a.data, a.imovel, c.id_cliente, c.nome, c.cpf, c.endereco, c.email, c.telefone " +
                "FROM agenda a INNER JOIN cliente c ON a.id_cliente = c.id_cliente WHERE a.data BETWEEN ? AND ?";
    
        try (PreparedStatement instrucao = conexao.prepareStatement(sql)) {
            instrucao.setDate(1, java.sql.Date.valueOf(data_inicio));
            instrucao.setDate(2, java.sql.Date.valueOf(data_fim));
            ResultSet consulta = instrucao.executeQuery();

            boolean encontrouCompromissos = false;

            while (consulta.next()) {
                encontrouCompromissos = true;
                LocalDate dataEvento = consulta.getDate("data").toLocalDate();
                String imovel = consulta.getString("imovel");
                int id_cliente = consulta.getInt("id_cliente");
                String nome = consulta.getString("nome");
                String cpf = consulta.getString("cpf");
                String endereco = consulta.getString("endereco");
                String email = consulta.getString("email");
                String telefone = consulta.getString("telefone");

                System.out.println("\nAgendamento: ");
                System.out.println("Data: " + dataEvento);
                System.out.println("Imóvel: " + imovel);
                System.out.println("Cliente: " + id_cliente);
                System.out.println("\nDados do cliente que agendou o imóvel: ");
                System.out.println("Nome do cliente: " + (nome != null ? nome : "Este imóvel não possui um cliente cadastrado"));
                System.out.println("CPF do Cliente: " + cpf);
                System.out.println("Endereço do Cliente: " + endereco);
                System.out.println("Email do Cliente: " + email);
                System.out.println("Telefone: " + telefone);
            }

            if (!encontrouCompromissos) {
                System.out.println("Não há compromissos agendados para o período informado.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void pesquisarAgendamentosPorCliente(int idCliente) {
        String sql = "SELECT a.data, a.imovel, c.id_cliente, c.nome, c.cpf, c.endereco, c.email, c.telefone " +
                     "FROM agenda a INNER JOIN cliente c ON a.id_cliente = c.id_cliente WHERE c.id_cliente = ?";
        
        try (PreparedStatement instrucao = conexao.prepareStatement(sql)) {
            instrucao.setInt(1, idCliente);
            ResultSet consulta = instrucao.executeQuery();
    
            boolean encontrouCompromissos = false;
    
            while (consulta.next()) {
                encontrouCompromissos = true;
                LocalDate dataEvento = consulta.getDate("data").toLocalDate();
                String imovel = consulta.getString("imovel");
                String nome = consulta.getString("nome");
                String cpf = consulta.getString("cpf");
                String endereco = consulta.getString("endereco");
                String email = consulta.getString("email");
                String telefone = consulta.getString("telefone");
    
                System.out.println("\nAgendamento: ");
                System.out.println("Data: " + dataEvento);
                System.out.println("Imóvel: " + imovel);
                System.out.println("\nDados do cliente que agendou o imóvel: ");
                System.out.println("Nome do cliente: " + (nome != null ? nome : "Este imóvel não possui um cliente cadastrado"));
                System.out.println("CPF do Cliente: " + cpf);
                System.out.println("Endereço do Cliente: " + endereco);
                System.out.println("Email do Cliente: " + email);
                System.out.println("Telefone: " + telefone);
            }
    
            if (!encontrouCompromissos) {
                System.out.println("Não há compromissos agendados para este cliente.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }    


}
