package api.reservas.api.entitys;

import api.reservas.api.dto.AlterarUsuarioDTO;
import api.reservas.api.dto.UsuarioDTO;
import jakarta.persistence.*;
import jakarta.validation.Valid;


@Entity
@Table(name = "usuario")
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private String telefone;
    private Boolean ativo;

    //construtors

    public Usuario(){}

    public Usuario(UsuarioDTO dados){
        this.ativo = true;
        this.id = dados.id();
        this.nome = dados.nome();
        this.telefone = dados.telefone();

    }

    public void setId(Long id){ this.id = id; }
    public Long getId(){ return this.id; }

    public void setNome(String nome){ this.nome = nome; }
    public String getNome(){ return this.nome;}

    public void setTelefone(String telefone){ this.telefone = telefone;}
    public String getTelefone(){ return this.telefone; }

    public void setAtivo(Boolean ativo){ this.ativo = ativo; }
    public Boolean getAtivo(){ return this.ativo;}

}