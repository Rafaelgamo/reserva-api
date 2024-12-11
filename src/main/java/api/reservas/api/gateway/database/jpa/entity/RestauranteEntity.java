package api.reservas.api.gateway.database.jpa.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "restaurante")
public class RestauranteEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(unique = true, nullable = false)
    private String cnpj;

    @Column(unique = true, nullable = false)
    private String nome;

    private Integer horaAbertura;
    private Integer horaFechamento;
    private Integer capacidade;
    private Boolean ativo;

    @JoinColumn(name = "endereco_id")
    @ManyToOne(targetEntity = EnderecoEntity.class, fetch = FetchType.LAZY)
    private EnderecoEntity endereco;

    @JoinColumn(name = "tipo_cozinha_id")
    @ManyToOne(targetEntity = TipoCozinhaEntity.class, fetch = FetchType.LAZY)
    private TipoCozinhaEntity tipoCozinha;

    public RestauranteEntity(Long id, String cnpj, String nome, Integer horaAbertura, Integer horaFechamento, Integer capacidade, Boolean ativo, EnderecoEntity endereco, TipoCozinhaEntity tipoCozinha) {
        this.id = id;
        this.cnpj = cnpj;
        this.nome = nome;
        this.horaAbertura = horaAbertura;
        this.horaFechamento = horaFechamento;
        this.capacidade = capacidade;
        this.ativo = ativo;
        this.endereco = endereco;
        this.tipoCozinha = tipoCozinha;
    }

    public RestauranteEntity() {}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Integer getHoraAbertura() {
        return horaAbertura;
    }

    public void setHoraAbertura(Integer horaAbertura) {
        this.horaAbertura = horaAbertura;
    }

    public Integer getHoraFechamento() {
        return horaFechamento;
    }

    public void setHoraFechamento(Integer horaFechamento) {
        this.horaFechamento = horaFechamento;
    }

    public Integer getCapacidade() {
        return capacidade;
    }

    public void setCapacidade(Integer capacidade) {
        this.capacidade = capacidade;
    }

    public Boolean getAtivo() {
        return ativo;
    }

    public void setAtivo(Boolean ativo) {
        this.ativo = ativo;
    }

    public EnderecoEntity getEndereco() {
        return endereco;
    }

    public void setEndereco(EnderecoEntity endereco) {
        this.endereco = endereco;
    }

    public TipoCozinhaEntity getTipoCozinha() {
        return tipoCozinha;
    }

    public void setTipoCozinha(TipoCozinhaEntity tipoCozinha) {
        this.tipoCozinha = tipoCozinha;
    }
}