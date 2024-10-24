package Projeto.DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class BancoDeDados {
    private static String driver = "org.postgresql.Driver";
    private static String url = "jdbc:postgresql://localhost:5432/Imobiliaria";
    private static String user = "postgres";
    private static String password = "postgres";

    private static Connection conn;

    private BancoDeDados() {
    }
    // Método para obter a conexão com o banco de dados
    private static Connection getConnection() throws SQLException {
        if (conn == null || conn.isClosed()) {
            try {
                Class.forName(driver);
                conn = DriverManager.getConnection(url, user, password);
            } catch (ClassNotFoundException e) {
                System.out.println("Erro na conexão com o banco");
                e.printStackTrace();
            }
        }
        return conn;
    }

    // Iniciar conexão em todas as classes DAO
    public static void iniciarConexao() throws SQLException {
        Connection connection = getConnection();
        AgendaDAO.conexao = connection;
        UsuarioClienteDAO.conexao = connection;
        ContratoDAO.conexao = connection;
        ImovelDAO.conexao = connection;
    }

    // Fechar a conexão
    public static void fecharConexao() throws SQLException {
        if (conn != null && !conn.isClosed()) {
            conn.close();
        }
    }

    // Métodos utilitários para fechar recursos
    public static void fecharStatement(Statement statement) throws SQLException {
        if (statement != null && !statement.isClosed()) {
            statement.close();
        }
    }

    public static void fecharPreparedStatement(PreparedStatement preparedStatement) throws SQLException {
        if (preparedStatement != null && !preparedStatement.isClosed()) {
            preparedStatement.close();
        }
    }

    public static void fecharResultSet(ResultSet resultSet) throws SQLException {
        if (resultSet != null && !resultSet.isClosed()) {
            resultSet.close();
        }
    }
}
