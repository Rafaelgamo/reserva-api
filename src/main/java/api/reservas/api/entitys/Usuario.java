package api.reservas.api.entitys;

import jakarta.persistence.*;


@Entity
@Table(name = "usuario")
public class Usuario {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private String telefone;

    public void setId(Long id){ this.id = id; }
    public Long getId(){ return this.id; }

    public void setNome(String nome){ this.nome = nome; }
    public String getNome(){ return this.nome;}

    public void setTelefone(String telefone){ this.telefone = telefone;}
    public String getTelefone(){ return this.telefone; }


}