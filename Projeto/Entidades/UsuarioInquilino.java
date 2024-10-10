package Projeto.Entidades;
import java.sql.Date;
public class UsuarioInquilino {
    private String nome;
    private String cpf;
    private Date ini_contrato;
    private Date Fim_contrato;
   
    public UsuarioInquilino(String nome, String cpf, Date ini_contrato, Date fim_contrato) {
        this.nome = nome;
        this.cpf = cpf;
        this.ini_contrato = ini_contrato;
        Fim_contrato = fim_contrato;
    }
    

    
}
