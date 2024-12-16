package api.reservas.api.gateway.database.jpa.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;

@Entity
@Table(name = "tipo_cozinha")
public class TipoCozinhaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "tipo_cozinha_generator")
    @SequenceGenerator(name = "tipo_cozinha_generator", sequenceName = "tipo_cozinha_seq", allocationSize = 1)
    private Long id;

    @Column(unique = true, nullable = false)
    private String nome;

    public TipoCozinhaEntity(Long id, String nome) {
        this.id = id;
        this.nome = nome;
    }

    public TipoCozinhaEntity() {}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

}

