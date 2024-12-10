package api.reservas.api.entitys;


import api.reservas.api.dto.ReservaDTO;
import api.reservas.api.gateway.database.jpa.entity.RestauranteEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "reserva")
public class Reserva {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(targetEntity = RestauranteEntity.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "id_restaurante", referencedColumnName = "id")
    private RestauranteEntity restauranteEntity;

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
        this.restauranteEntity = new RestauranteEntity();
        this.usuario = new Usuario();
        this.quantidade = dados.quantidade();
        this.horario = dados.horario();
        this.ativo = true;
    }

    //getters e setters
    public void setId(Long id){ this.id = id;}
    public Long getId(){ return id;}

    public RestauranteEntity getRestaurante(){ return restauranteEntity;}
    public void setRestaurante(RestauranteEntity restauranteEntity){ this.restauranteEntity = restauranteEntity;}

    public void setUsuario(Usuario usuario){ this.usuario = usuario;}
    public Usuario getUsuario(){return this.usuario;}

    public void setQuantidade(int quantidade){ this.quantidade = quantidade;}
    public Integer getQuantidade(){return this.quantidade;}

    public void setHorario(String horario){this.horario = horario;}
    public String getHorario(){ return this.horario;}

    public void setAtivo(Boolean ativo){ this.ativo = ativo; }
    public Boolean getAtivo(){ return this.ativo;}
}