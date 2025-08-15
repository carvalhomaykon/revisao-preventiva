package com.revisao_preventiva.revisao_preventiva.model;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDate;

@Data
@Entity
@Table(name = "veiculos")
public class Veiculo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "user_id")
    private Long userId;

    private String tipo;
    private String modelo;
    private Double quilometragem;

    @Column(name = "quilometragem_diaria")
    private Double quilometragemDiaria;

    @Column(name = "dias_uso_semana")
    private Integer diasUsoSemana;

    @Column(name = "ultima_revisao")
    private LocalDate ultimaRevisao;

    @Column(name = "ano_fabricacao")
    private Integer anoFabricacao;

    public Veiculo() {}

    public Veiculo(Long id, Long userId, String tipo, String modelo, Double quilometragem, Double quilometragemDiaria, Integer diasUsoSemana, LocalDate ultimaRevisao, Integer anoFabricacao) {
        this.id = id;
        this.userId = userId;
        this.tipo = tipo;
        this.modelo = modelo;
        this.quilometragem = quilometragem;
        this.quilometragemDiaria = quilometragemDiaria;
        this.diasUsoSemana = diasUsoSemana;
        this.ultimaRevisao = ultimaRevisao;
        this.anoFabricacao = anoFabricacao;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public Double getQuilometragem() {
        return quilometragem;
    }

    public void setQuilometragem(Double quilometragem) {
        this.quilometragem = quilometragem;
    }

    public Double getQuilometragemDiaria() {
        return quilometragemDiaria;
    }

    public void setQuilometragemDiaria(Double quilometragemDiaria) {
        this.quilometragemDiaria = quilometragemDiaria;
    }

    public Integer getDiasUsoSemana() {
        return diasUsoSemana;
    }

    public void setDiasUsoSemana(Integer diasUsoSemana) {
        this.diasUsoSemana = diasUsoSemana;
    }

    public LocalDate getUltimaRevisao() {
        return ultimaRevisao;
    }

    public void setUltimaRevisao(LocalDate ultimaRevisao) {
        this.ultimaRevisao = ultimaRevisao;
    }

    public Integer getAnoFabricacao() {
        return anoFabricacao;
    }

    public void setAnoFabricacao(Integer anoFabricacao) {
        this.anoFabricacao = anoFabricacao;
    }
}
