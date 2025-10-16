package br.com.energycontrol.EnergyControl.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
        import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "t_limite")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TLimite implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_lmt")
    private Long idLmt;

    @Column(name = "lt_consumo", nullable = false)
    private BigDecimal ltConsumo;

    @Column(name = "hr_inicio")
    private LocalDateTime hrInicio;

    @Column(name = "hr_fim")
    private LocalDateTime hrFim;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "t_equipamentos_id_equip", nullable = false)
    @JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
    private TEquipamento equipamento;
}
