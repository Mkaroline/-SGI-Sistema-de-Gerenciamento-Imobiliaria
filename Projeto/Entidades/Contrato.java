package Projeto.Entidades;

import java.util.Date;

public class Contrato {

    private Date data_inicio;
    private Date data_fim;
    private double valor;
    private String status;
    private boolean ativo;

    // Construtor
    public Contrato(Date data_inicio, Date data_fim, double valor, String status, boolean ativo) {
        this.data_inicio = data_inicio;
        this.data_fim = data_fim;
        this.valor = valor;
        this.status = status;
        this.ativo = ativo;
    }

    // Getters e Setters

    public Date getData_inicio() {
        return data_inicio;
    }

    public void setData_inicio(Date data_inicio) {
        this.data_inicio = data_inicio;
    }

    public Date getData_fim() {
        return data_fim;
    }

    public void setData_fim(Date data_fim) {
        this.data_fim = data_fim;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public boolean isAtivo() {
        return ativo;
    }

    public void setAtivo(boolean ativo) {
        this.ativo = ativo;
    }

    @Override
    public String toString() {
        return ", Início: " + data_inicio + ", Fim: " + data_fim + 
                ", Valor: " + valor + ", Status: " + status + ", Ativo: " + ativo + "]";
    }
}
