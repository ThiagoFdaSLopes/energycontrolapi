package br.com.energycontrol.EnergyControl.Model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.io.Serializable;
import java.sql.Date;
import java.sql.Timestamp;

@Entity
@Table(name = "t_logs")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TLogs implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_log")
    private Long idSet;

    @Column(name = "log_mensagem", length = 255, nullable = false)
    @NotNull
    @Size(max = 255)
    private String logMessage;

    @Column(name = "data_inc", nullable = false, updatable = false, insertable = false)
    private Date dateInc;

    @Column(name = "tempo_inc", nullable = false, updatable = false, insertable = false)
    private Timestamp tempoInc;
}