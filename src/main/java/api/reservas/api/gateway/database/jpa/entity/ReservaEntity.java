package api.reservas.api.gateway.database.jpa.entity;


import api.reservas.api.domain.enums.StatusReserva;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

import java.time.LocalDateTime;

@Entity
@Table(name = "reserva")
public class ReservaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "reserva_generator")
    @SequenceGenerator(name = "reserva_generator", sequenceName = "reserva_seq", allocationSize = 1)
    private Long id;

    @JoinColumn(name = "restaurante_id")
    @ManyToOne(targetEntity = RestauranteEntity.class, fetch = FetchType.LAZY)
    private RestauranteEntity restaurante;

    @Enumerated(EnumType.STRING)
    private StatusReserva status;

    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime data;


    public ReservaEntity(Long id, RestauranteEntity restaurante, StatusReserva status, LocalDateTime data) {
        this.id = id;
        this.restaurante = restaurante;
        this.status = status;
        this.data = data;
    }

    public ReservaEntity() {}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getData() {
        return data;
    }

    public void setData(LocalDateTime data) {
        this.data = data;
    }

    public RestauranteEntity getRestaurante() {
        return restaurante;
    }

    public void setRestaurante(RestauranteEntity restaurante) {
        this.restaurante = restaurante;
    }

    public StatusReserva getStatus() {
        return status;
    }

    public void setStatus(StatusReserva statusReserva) {
        this.status = statusReserva;
    }
}
