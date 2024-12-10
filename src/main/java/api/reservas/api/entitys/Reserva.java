package api.reservas.api.entitys;


import api.reservas.api.Enum.NotaAvaliacao;
import jakarta.persistence.*;

import java.io.Serializable;

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

    @Enumerated
    private NotaAvaliacao notaAvaliacao;

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
