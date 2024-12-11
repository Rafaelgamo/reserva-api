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
public class Reserva  implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(targetEntity = Usuario.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "id_usuario", referencedColumnName = "id")
    private Usuario usuario;

    @ManyToOne(targetEntity = Vaga.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "id_vaga", referencedColumnName = "id")
    private Vaga vaga;

    private Boolean mesaOcupada;

    public Reserva(){}

    public Reserva(Long id, Usuario usuario, Vaga vaga, Boolean mesaOcupada){
        this.id = id;
        this.usuario = new Usuario();
        this.vaga = new Vaga();
        this.mesaOcupada = true;
    }

    public void setId(Long id){ this.id = id; }
    public Long getId(){ return this.id; }

    public void setUsuario(Usuario usuario){ this.usuario = usuario; }
    public Usuario getUsuario(){ return this.usuario; }

    public void setVaga(Vaga vaga){ this.vaga = vaga; }
    public Vaga getVaga(){ return this.vaga; }

    public void setMesaOcupada(Boolean mesaOcupada){ this.mesaOcupada = mesaOcupada; }
    public Boolean getMesaOcupada(){ return this.mesaOcupada; }

}
