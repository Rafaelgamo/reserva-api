package api.reservas.api.gateway.database.jpa.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;

@Entity
@Table(name = "endereco")
public class EnderecoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "endereco_generator")
    @SequenceGenerator(name = "endereco_generator", sequenceName = "endereco_seq", allocationSize = 1)
    private Long id;
    private String cep;
    private String numero;

    public EnderecoEntity(Long id, String cep, String numero) {
        this.id = id;
        this.cep = cep;
        this.numero = numero;
    }

    public EnderecoEntity() {}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }
}
