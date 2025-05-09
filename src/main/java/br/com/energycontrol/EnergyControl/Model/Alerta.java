package br.com.energycontrol.EnergyControl.Model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "T_ALERTAS")
public class Alerta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_ALT")
    private Long idAlerta;

    @Column(name = "DT_HORA", nullable = false)
    private LocalDateTime dataHora;

    @Column(name = "DS_ALERTA", length = 200, nullable = false)
    private String descricaoAlerta;

    @ManyToOne
    @JoinColumn(name = "T_LIMITE_ID_LMT", nullable = false)
    private TLimite limite; // Alterado para TLimite

    public Alerta() {
    }

    public Alerta(LocalDateTime dataHora, String descricaoAlerta, TLimite limite) {
        this.dataHora = dataHora;
        this.descricaoAlerta = descricaoAlerta;
        this.limite = limite;
    }

    public Long getIdAlerta() {
        return idAlerta;
    }

    public void setIdAlerta(Long idAlerta) {
        this.idAlerta = idAlerta;
    }

    public LocalDateTime getDataHora() {
        return dataHora;
    }

    public void setDataHora(LocalDateTime dataHora) {
        this.dataHora = dataHora;
    }

    public String getDescricaoAlerta() {
        return descricaoAlerta;
    }

    public void setDescricaoAlerta(String descricaoAlerta) {
        this.descricaoAlerta = descricaoAlerta;
    }

    public TLimite getLimite() {
        return limite;
    }

    public void setLimite(TLimite limite) {
        this.limite = limite;
    }

    @Override
    public String toString() {
        return "Alerta{" +
                "idAlerta=" + idAlerta +
                ", dataHora=" + dataHora +
                ", descricaoAlerta='" + descricaoAlerta + '\'' +
                ", limite=" + (limite != null ? limite.getIdLmt() : null) +
                '}';
    }
}