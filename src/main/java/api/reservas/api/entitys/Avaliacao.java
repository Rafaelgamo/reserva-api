package api.reservas.api.entitys;

import api.reservas.api.dto.AvaliacaoDTO;
import api.reservas.api.enums.NotaAvaliacao;

public class Avaliacao {

    private Restaurante restaurante;
    private Usuario usuario;
    private String comentario;
    private NotaAvaliacao notaAvaliacao;

    public Avaliacao(){}

    public Avaliacao(AvaliacaoDTO dados){
        this.restaurante = new Restaurante();
        this.usuario = new Usuario();
        this.comentario = dados.comentario();
        this.notaAvaliacao = dados.notaAvaliacao();
    }

    public Restaurante getRestaurante(){ return restaurante;}
    public void setRestaurante(Restaurante restaurante){ this.restaurante = restaurante;}

    public void setUsuario(Usuario usuario){ this.usuario = usuario;}
    public Usuario getUsuario(){return this.usuario;}

    public void setComentario(String comentario){this.comentario = comentario;}
    public String getComentario(){return this.comentario;}

    public void setNotaAvaliacao(NotaAvaliacao notaAvaliacao){ this.notaAvaliacao = notaAvaliacao;}
    public NotaAvaliacao getNotaAvaliacao(){return this.notaAvaliacao;}

    }


