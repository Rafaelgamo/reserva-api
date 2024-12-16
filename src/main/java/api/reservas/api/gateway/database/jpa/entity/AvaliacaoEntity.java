package api.reservas.api.gateway.database.jpa.entity;

import api.reservas.api.domain.enums.NotaAvaliacao;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "avaliacao")
public class AvaliacaoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JoinColumn(name = "reserva_id")
    @ManyToOne(targetEntity = ReservaEntity.class, fetch = FetchType.LAZY)
    private ReservaEntity reserva;

    @Enumerated(EnumType.STRING)
    private NotaAvaliacao notaAvaliacao;

    private String comentario;

    public AvaliacaoEntity(Long id, ReservaEntity reserva, NotaAvaliacao notaAvaliacao, String comentario) {
        this.id = id;
        this.reserva = reserva;
        this.notaAvaliacao = notaAvaliacao;
        this.comentario = comentario;
    }

    public AvaliacaoEntity() {}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public ReservaEntity getReserva() {
        return reserva;
    }

    public void setReserva(ReservaEntity reserva) {
        this.reserva = reserva;
    }

    public NotaAvaliacao getNotaAvaliacao() {
        return notaAvaliacao;
    }

    public void setNotaAvaliacao(NotaAvaliacao notaAvaliacao) {
        this.notaAvaliacao = notaAvaliacao;
    }

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }
}
