package Projeto.Entidades;

import Excecoes.DadosInvalidosException;

public class UsuarioAdministrador extends Usuario{

    private String login;
    private String senha;
    private boolean log;

    //construtor
    public UsuarioAdministrador(String nome, String cpf, String login, String senha) throws DadosInvalidosException{
        super (nome, cpf);
        validarCampos(nome, cpf, login, senha);
        this.login = login;
        this.senha = senha;
    }

    // Método para validar campos
    private void validarCampos(String nome, String cpf, String login, String senha) throws DadosInvalidosException {
        if (nome == null || nome.isEmpty()) {
            throw new DadosInvalidosException("Nome não pode estar vazio");
        }

        if (cpf == null || cpf.isEmpty()) {
            throw new DadosInvalidosException("CPF não pode estar vazio");
        }

        if (login == null || login.isEmpty()) {
            throw new DadosInvalidosException("Login não pode estar vazio");
        }

        if (senha == null || senha.isEmpty()) {
            throw new DadosInvalidosException("Senha não pode estar vazia");
        }
    }

    public boolean validarLogin(String login, String senha){
        if((login.equals(this.login)) && senha.equals(this.senha)){
            System.out.println("Logado com sucesso!");
            log = true;
        } else {
            System.out.println("Senha ou usuario incorretos");
           log = false;
        }
        return log;
    }


    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Nome: ").append(getNome()).append("\n");
        // Assumindo que getcpf() foi definido corretamente
        sb.append("CPF: ").append(getcpf()).append("\n");
        sb.append("Login: ").append(login).append("\n");
        sb.append("Senha: ").append(senha).append("\n"); // Geralmente não é recomendado exibir senhas
        return sb.toString();
    }

}