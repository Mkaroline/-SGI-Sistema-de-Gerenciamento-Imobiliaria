package Projeto.Entidades;

import Excecoes.DadosInvalidosException;

public abstract class Usuario {
    private String nome;
    private String cpf; 


    // Construtor
    public Usuario( String nome, String cpf) throws DadosInvalidosException {
        validarCampos(nome, cpf);
        this.nome = nome;
        this.cpf =cpf;
    }

    // Método para validar campos
    private void validarCampos(String nome, String cpf) throws DadosInvalidosException {
        if (nome == null || nome.isEmpty()) {
            throw new DadosInvalidosException("Nome não pode estar vazio");
        }

        if (cpf == null || cpf.isEmpty()) {
            throw new DadosInvalidosException("CPF não pode estar vazio");
        }
    }
    public Usuario(){

    }
    // Getters e setters para id, nome e cpf

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    //Getters e setters para cpf
    public String getcpf() {
        return cpf;
    }

    public void setcpf(String cpf){
        this.cpf=cpf;
    }

    @Override
    public String toString() {
        return ", Nome: " + nome + ", cpf: " + cpf;
    }

}