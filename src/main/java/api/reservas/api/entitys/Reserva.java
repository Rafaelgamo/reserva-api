package api.reservas.api.entitys;


import api.reservas.api.dto.ReservaDTO;
import jakarta.persistence.*;

import java.io.Serializable;

@Entity
@Table(name = "reserva")
public class Reserva {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(targetEntity = Restaurante.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "id_restaurante", referencedColumnName = "id")
    private Restaurante restaurante;

    @OneToOne
    @JoinColumn(name = "id_Usuario", referencedColumnName = "id")
    private Usuario usuario;

    private Integer quantidade;
    private String horario;
    private Boolean ativo;

    //contrutores
    public Reserva() { }

    public Reserva(ReservaDTO dados) {
        this.id = dados.id();
        this.restaurante = new Restaurante();
        this.usuario = new Usuario();
        this.quantidade = dados.quantidade();
        this.horario = dados.horario();
        this.ativo = true;
    }

    //getters e setters
    public void setId(Long id){ this.id = id;}
    public Long getId(){ return id;}

    public Restaurante getRestaurante(){ return restaurante;}
    public void setRestaurante(Restaurante restaurante){ this.restaurante = restaurante;}

    public void setUsuario(Usuario usuario){ this.usuario = usuario;}
    public Usuario getUsuario(){return this.usuario;}

    public void setQuantidade(int quantidade){ this.quantidade = quantidade;}
    public Integer getQuantidade(){return this.quantidade;}

    public void setHorario(String horario){this.horario = horario;}
    public String getHorario(){ return this.horario;}

    public void setAtivo(Boolean ativo){ this.ativo = ativo; }
    public Boolean getAtivo(){ return this.ativo;}
}