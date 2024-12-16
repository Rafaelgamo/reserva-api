package api.reservas.api.gateway.database.jpa.entity;

import api.reservas.api.domain.enums.NotaAvaliacao;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "avaliacao")
public class AvaliacaoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private NotaAvaliacao notaAvaliacao;

    private String comentario;

    public AvaliacaoEntity() {}

    public AvaliacaoEntity(Long id, NotaAvaliacao notaAvaliacao, String comentario){
        this.id = id;
        this.notaAvaliacao = notaAvaliacao;
        this.comentario = comentario;
    }

    public void setId(Long id) {this.id = id;}
    public Long getId(){return id;}

    public void setNotaAvaliacao(NotaAvaliacao notaAvaliacao) {this.notaAvaliacao = notaAvaliacao;}
    public NotaAvaliacao getNotaAvaliacao(){return notaAvaliacao;}

    public void setComentario(String comentario) {this.comentario = comentario;}
    public String getComentario(){return comentario;}
}
