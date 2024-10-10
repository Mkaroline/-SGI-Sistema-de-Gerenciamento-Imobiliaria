package Projeto.Entidades;

public class UsuarioAdministrador {
    private String cargo;
    private String login;
    private String senha;
    private boolean log;
    
    public UsuarioAdministrador(String cargo, String login, String senha, boolean log) {
        this.cargo = cargo;
        this.login = login;
        this.senha = senha;
        this.log = log;
    }

    
}
