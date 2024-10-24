package Projeto.Entidades;

import Excecoes.DadosInvalidosException;

public class Imovel {
    private String tipo_imovel;
    private String endereco_imovel;
    private double valor_Aluguel;
    private String cliente;
    private String status_imovel; // Ajuste: Camel case para consistência com o resto da classe


    // Construtor
    public Imovel(String tipo_imovel, String endereco_imovel, double valor_Aluguel,String cliente, String status_imovel) throws DadosInvalidosException {
        validarCampos(tipo_imovel, endereco_imovel, valor_Aluguel,cliente, status_imovel);
        this.tipo_imovel = tipo_imovel;
        this.endereco_imovel = endereco_imovel;
        this.valor_Aluguel = valor_Aluguel;
        this.cliente = cliente;
        this.status_imovel = status_imovel;
    }

    // Validação dos campos
    private void validarCampos(String tipo_imovel, String endereco_imovel, double valor_Aluguel, String cliente, String status_imovel) throws DadosInvalidosException {
        if (tipo_imovel == null || tipo_imovel.isEmpty()) {
            throw new DadosInvalidosException("Tipo do imóvel não pode estar vazio.");
        }
        if (endereco_imovel == null || endereco_imovel.isEmpty()) {
            throw new DadosInvalidosException("Endereço não pode estar vazio.");
        }
        if (valor_Aluguel< 0) {
            throw new DadosInvalidosException("Valor do aluguel não pode ser negativo.");
        }
        if (cliente == null || cliente.isEmpty()) {
            throw new DadosInvalidosException("Cliente não pode estar vazio");
        }
        if (status_imovel == null || status_imovel.isEmpty()) {
            throw new DadosInvalidosException("Status do imóvel não pode estar vazio.");
        }
    }

    // Getters e setters
    public String getTipoImovel() {
        return tipo_imovel;
    }

    public void setTipoImovel(String tipo_imovel) {
        this.tipo_imovel = tipo_imovel;
    }

    public String getEnderecoImovel() {
        return endereco_imovel;
    }

    public void setEnderecoImovel(String endereco_imovel) {
        this.endereco_imovel = endereco_imovel;
    }

    public double getValorAluguel() {
        return valor_Aluguel;
    }

    public void setValorAluguel(double valor_Aluguel) {
        this.valor_Aluguel = valor_Aluguel;
    }

    public String getCliente() {
        return cliente;
    }

    public void setCliente(String cliente) {
        this.cliente = cliente;
    }

    public String getStatusImovel() {
        return status_imovel;
    }

    public void setStatusImovel(String status_imovel) {
        this.status_imovel = status_imovel;
    }

    @Override
    public String toString() {
        return "Imovel [tipo_imovel=" + tipo_imovel + ", endereco_imovel=" + endereco_imovel + ", valor_Aluguel="
                + valor_Aluguel + ", cliente=" + cliente + ", status_imovel=" + status_imovel + "]";
    }

}