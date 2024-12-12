package api.reservas.api.entitys_remove;

import api.reservas.api.domain.enums.NotaAvaliacao;
import jakarta.persistence.Entity;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "avaliar")
public class Avaliacao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated
    private NotaAvaliacao notaAvaliacao;

    private String comentario;

    public Avaliacao(){}

    public Avaliacao(Long id, NotaAvaliacao notaAvaliacao, String comentario){
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


