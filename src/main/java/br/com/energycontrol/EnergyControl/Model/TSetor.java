package br.com.energycontrol.EnergyControl.Model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Entity
@Table(name = "t_setor")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TSetor implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_set")
    private Long idSet;

    @Column(name = "nm_setor", length = 100, nullable = false)
    private String nmSetor;

    @Column(name = "nr_andar", nullable = false)
    private Integer nrAndar;
}