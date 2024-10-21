package Projeto.Entidades;

import java.sql.Date;
import Excecoes.DadosInvalidosException;

public class Agenda {
    private Date data;
    private String imovel;
    private String cliente;

    public Agenda(Date data, String imovel, String cliente) throws DadosInvalidosException {
        super();
        validarCampos(data, cliente, imovel);
        validarData(data);
        this.data = data;
        this.cliente = cliente;
        this.imovel = imovel;
        System.out.println("Agendado com sucesso!");
    } 

    private void validarCampos(Date data, String cliente, String imovel) throws DadosInvalidosException {
        if (data == null) {
            throw new DadosInvalidosException("Data não pode estar vazia");
        }
    
        if (cliente == null || cliente.isEmpty()) {
            throw new DadosInvalidosException("Nome do cliente não pode estar vazio");
        }
    
        if (imovel == null || imovel.isEmpty()) {
            throw new DadosInvalidosException("Nome do imóvel não pode estar vazio");
        }
    }
    
    public static void validarData(Date data) throws DadosInvalidosException {
        if (data.before(new Date(System.currentTimeMillis()))) {
            throw new DadosInvalidosException("Não é possível agendar para datas retroativas.");
        }
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public String getCliente() {
        return cliente;
    }

    public void setCliente(String cliente) {
        this.cliente = cliente;
    }

    public String getImovel() {
        return imovel;
    }

    public void setImovel(String imovel) {
        this.imovel = imovel;
    }

    @Override
    public String toString() {
        return "Agenda:\n" +
               "Data: " + data + "\n" +
               "Cliente: " + cliente + "\n" +
               "Imóvel: " + imovel + "\n";
    }
}
