package Projeto.Entidades;

public class UsuarioCliente {
    private String nome;
    private String cpf;
    private String endereco;
    private String email;
    private int telefone;
    private String login;
    private String senha;

    public UsuarioCliente(String nome,String cpf, String endereco, String email, int telefone, String login, String senha) {
        this.nome = nome;
        this. cpf = cpf;
        this.endereco = endereco;
        this.email = email;
        this.telefone = telefone;
        this.login = login;
        this.senha = senha;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getTelefone() {
        return telefone;
    }

    public void setTelefone(int telefone) {
        this.telefone = telefone;
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
        sb.append("CPF: ").append(getCpf()).append("\n");
        sb.append("Endereco: ").append(endereco).append("\n");
        sb.append("Email: ").append(email).append("\n");
        sb.append("Telefone: ").append(telefone).append("\n");
        sb.append("Login: ").append(login).append("\n");
        sb.append("Senha: ").append(senha).append("\n");
        return sb.toString();
    }

}
