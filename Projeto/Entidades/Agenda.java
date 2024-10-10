package Projeto.Entidades;

import java.sql.Date;

public class Agenda {
    private Date data;
    private String cliente;
    private String Imovel;

    public Agenda(Date data, String cliente, String imovel) {
        this.data = data;
        this.cliente = cliente;
        Imovel = imovel;
    }

    
}
