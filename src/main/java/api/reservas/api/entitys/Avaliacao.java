package api.reservas.api.entitys;

import api.reservas.api.domain.enums.NotaAvaliacao;
import api.reservas.api.dto.AvaliacaoDTO;
import api.reservas.api.gateway.database.jpa.entity.RestauranteEntity;

public class Avaliacao {

    private RestauranteEntity restauranteEntity;
    private Usuario usuario;
    private String comentario;
    private NotaAvaliacao notaAvaliacao;

    public Avaliacao(){}

    public Avaliacao(AvaliacaoDTO dados){
        this.restauranteEntity = new RestauranteEntity();
        this.usuario = new Usuario();
        this.comentario = dados.comentario();
        this.notaAvaliacao = dados.notaAvaliacao();
    }

    public RestauranteEntity getRestaurante(){ return restauranteEntity;}
    public void setRestaurante(RestauranteEntity restauranteEntity){ this.restauranteEntity = restauranteEntity;}

    public void setUsuario(Usuario usuario){ this.usuario = usuario;}
    public Usuario getUsuario(){return this.usuario;}

    public void setComentario(String comentario){this.comentario = comentario;}
    public String getComentario(){return this.comentario;}

    public void setNotaAvaliacao(NotaAvaliacao notaAvaliacao){ this.notaAvaliacao = notaAvaliacao;}
    public NotaAvaliacao getNotaAvaliacao(){return this.notaAvaliacao;}

    }


