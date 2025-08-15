package com.revisao_preventiva.revisao_preventiva.model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

@Data
@Entity
@Table(name = "revisoes")
public class Revisao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "veiculo_id")
    private Long veiculoId;

    @Column(name = "status_revisao")
    private String statusRevisao;

    @Column(name = "data_prevista_revisao")
    private LocalDate dataPrevistaRevisao;

    @Column(name = "km_fazer_revisao")
    private Double kmFazerRevisao;

    public Revisao() {}

    public Revisao(Long id, Long veiculoId, String statusRevisao, LocalDate dataPrevistaRevisao, Double kmFazerRevisao) {
        this.id = id;
        this.veiculoId = veiculoId;
        this.statusRevisao = statusRevisao;
        this.dataPrevistaRevisao = dataPrevistaRevisao;
        this.kmFazerRevisao = kmFazerRevisao;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getVeiculoId() {
        return veiculoId;
    }

    public void setVeiculoId(Long veiculoId) {
        this.veiculoId = veiculoId;
    }

    public String getStatusRevisao() {
        return statusRevisao;
    }

    public void setStatusRevisao(String statusRevisao) {
        this.statusRevisao = statusRevisao;
    }

    public LocalDate getDataPrevistaRevisao() {
        return dataPrevistaRevisao;
    }

    public void setDataPrevistaRevisao(LocalDate dataPrevistaRevisao) {
        this.dataPrevistaRevisao = dataPrevistaRevisao;
    }

    public Double getKmFazerRevisao() {
        return kmFazerRevisao;
    }

    public void setKmFazerRevisao(Double kmFazerRevisao) {
        this.kmFazerRevisao = kmFazerRevisao;
    }
}
