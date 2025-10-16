package br.com.energycontrol.EnergyControl.model;

import jakarta.persistence.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

@Entity
@Table(name = "T_CONSUMO")
public class Consumo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_CON_ENG")
    private Long id;

    @Column(name = "DT_HORA", nullable = false)
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime dataHora;

    @Column(name = "KW_CONSUMO", nullable = false)
    private Double kwConsumo;

    @ManyToOne
    @JoinColumn(name = "T_EQUIPAMENTOS_ID_EQUIP", nullable = false)
    private TEquipamento equipamento;

    public Consumo() {
    }

    public Consumo(LocalDateTime dataHora, Double kwConsumo, TEquipamento equipamento) {
        this.dataHora = dataHora;
        this.kwConsumo = kwConsumo;
        this.equipamento = equipamento;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getDataHora() {
        return dataHora;
    }

    public void setDataHora(LocalDateTime dataHora) {
        this.dataHora = dataHora;
    }

    public Double getKwConsumo() {
        return kwConsumo;
    }

    public void setKwConsumo(Double kwConsumo) {
        this.kwConsumo = kwConsumo;
    }

    public TEquipamento getEquipamento() {
        return equipamento;
    }

    public void setEquipamento(TEquipamento equipamento) {
        this.equipamento = equipamento;
    }

    @Override
    public String toString() {
        return "Consumo{" +
                "id=" + id +
                ", dataHora=" + dataHora +
                ", kwConsumo=" + kwConsumo +
                ", equipamento=" + (equipamento != null ? equipamento.getIdEquip() : null) +
                '}';
    }
}