package api.reservas.api.entitys;

import jakarta.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "restaurante")
public class Restaurante  implements Serializable  {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;
    private String endereco;
    private String tipodecozinha;
    private String funcionamento;
    private int capacidade;
    private Boolean ativo;

    //Contructors
    public Restaurante(){}

    public Restaurante(Long id, String nome, String endereco, String tipodecozinha, String funcionamento, int capacidade, Boolean ativo){
        this.id= id;
        this.nome = nome;
        this.endereco = endereco;
        this.tipodecozinha = tipodecozinha;
        this.funcionamento = funcionamento;
        this.capacidade = capacidade;
        this.ativo = true;

    }

    //Getters and Setters
    public void setId(Long id){ this.id = id; }
    public Long getId(){ return this.id; }

    public void setNome(String nome){ this.nome = nome; }
    public String getNome(){ return this.nome; }

    public void setEndereco(String endereco){ this.endereco = endereco; }
    public String getEndereco(){ return this.endereco; }

    public void setTipodecozinha(String tipodecozinha){ this.tipodecozinha = tipodecozinha; }
    public String getTipodecozinha(){ return this.tipodecozinha; }

    public void setFuncionamento(String funcionamento){ this.funcionamento = funcionamento; }
    public  String getFuncionamento(){ return this.funcionamento; }

    public void setCapacidade(Integer capacidade){ this.capacidade = capacidade; }
    public  Integer getCapacidade(){ return this.capacidade; }

    public void setAtivo(Boolean ativo){ this.ativo = ativo; }
    public Boolean getAtivo(){ return this.ativo; }

}