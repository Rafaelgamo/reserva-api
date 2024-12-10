package api.reservas.api.entitys;

import api.reservas.api.Enum.NotaAvaliacao;
import jakarta.persistence.*;

@Entity
@Table(name = "avaliacao")
public class Avaliacao {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private NotaAvaliacao notaAvaliacao;

    @ManyToOne(targetEntity = Restaurante.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "id_restaurante", referencedColumnName = "id")
    private Restaurante restaurante;

    @ManyToOne(targetEntity = Reserva.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "id_reserva", referencedColumnName = "id")
    private Reserva reserva;

    private String comentario;

    //Contructors
    public Avaliacao(){}

    public  Avaliacao(Long id, NotaAvaliacao notaAvaliacao, Restaurante restaurante, Reserva reserva ,String comentario ){
       this.id = id;
        this.notaAvaliacao = notaAvaliacao;
        this.restaurante = new Restaurante();
        this.reserva = new Reserva();
        this.comentario = comentario;
    }

    //Getters and Setters
    public void setId(Long id){ this.id = id; }
    public Long getId(){ return this.id; }

    public void setNotaAvaliacao(NotaAvaliacao notaAvaliacao) {this.notaAvaliacao = notaAvaliacao;}
    public NotaAvaliacao getNotaAvaliacao() {return notaAvaliacao;}

    public void setRestaurante(Restaurante restaurante) {this.restaurante = restaurante;}
    public Restaurante getRestaurante() {return restaurante;}

    public void setReserva(Reserva reserva){this.reserva = reserva;}
    public Reserva getReserva(){return reserva;}

    public void setComentario(String comentario) {this.comentario = comentario;}
    public String getComentario() {return comentario;}
}
