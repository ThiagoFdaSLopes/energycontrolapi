package br.com.energycontrol.EnergyControl.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Entity
@Table(name = "t_equipamentos")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TEquipamento implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_equip")
    private Long idEquip;

    @Column(name = "nm_equipamento", length = 200, nullable = false)
    private String nmEquipamento;

    @Column(name = "tipo", length = 50, nullable = false)
    private String tipo;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "t_setor_id_set", nullable = false)
    @JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
    private TSetor setor;
}
