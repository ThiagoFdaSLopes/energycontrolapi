package br.com.energycontrol.EnergyControl.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
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

    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "nm_setor", length = 100, nullable = false)
    private String nmSetor;

    @NotNull
    @Column(name = "nr_andar", nullable = false)
    private Integer nrAndar;
}