package api.reservas.api.entitys;

import jakarta.persistence.*;

import java.io.Serializable;


@Entity
@Table(name = "usuario")
public class Usuario implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;
    private String telefone;
    private Boolean ativo;

    //Contructors
    public Usuario(){}

    public Usuario(Long id, String nome, String telefone, Boolean ativo){
        this.ativo = true;
        this.id = id;
        this.nome =nome;
        this.telefone = telefone;

    }

    //Getters and Setters
    public void setId(Long id){ this.id = id; }
    public Long getId(){ return this.id; }

    public void setNome(String nome){ this.nome = nome; }
    public String getNome(){ return this.nome;}

    public void setTelefone(String telefone){ this.telefone = telefone;}
    public String getTelefone(){ return this.telefone; }

    public void setAtivo(Boolean ativo){ this.ativo = ativo; }
    public Boolean getAtivo(){ return this.ativo;}

}