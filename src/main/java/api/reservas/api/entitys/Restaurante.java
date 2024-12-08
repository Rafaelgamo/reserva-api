package api.reservas.api.entitys;

import api.reservas.api.dto.RestauranteDTO;
import jakarta.persistence.*;

@Entity
@Table(name = "restaurante")
public class Restaurante  {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private String endereco;
    private String tipodecozinha;
    private String funcionamento;
    private Integer capacidade;
    private Boolean ativo;

    public Restaurante(){}

    public Restaurante(RestauranteDTO dados){
        this.id = dados.id();
        this.nome = dados.nome();
        this.endereco = dados.endereco();
        this.tipodecozinha = dados.tipodecozinha();
        this.funcionamento = dados.funcionamento();
        this.capacidade = dados.capacidade();
        this.ativo = true;

    }

    public void setId(Long id){ this.id = id; }
    public Long getId(){ return this.id; }

    public void setNome(String nome){ this.nome = nome; }
    public String getNome(){ return this.nome; }

    public void setEndereco(String endereco){ this.endereco = endereco; }
    public String getEndereco(){ return this.endereco; }

    public void setTipodecozinha(String tipodecozinha){ this.tipodecozinha = tipodecozinha; }
    public String getTipodecozinha(){ return this.tipodecozinha; }

    public void setFuncionamento(String funcionamento){ this.funcionamento = funcionamento; }
    public String getFuncionamento(){ return this.funcionamento; }

    public void setCapacidade(int capacidade){ this.capacidade = capacidade; }
    public Integer getCapacidade(){ return this.capacidade; }

    public void setAtivo(Boolean ativo){ this.ativo = ativo; }
    public Boolean getAtivo(){ return this.ativo; }

}