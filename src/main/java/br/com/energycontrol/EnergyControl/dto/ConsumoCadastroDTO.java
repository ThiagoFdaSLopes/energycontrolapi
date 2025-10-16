package br.com.energycontrol.EnergyControl.dto;

import br.com.energycontrol.EnergyControl.model.TEquipamento;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotBlank;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

public record ConsumoCadastroDTO(
        Long id,

        @NotBlank(message = "A data e hora deve ser informado yyyy-MM-dd HH:mm:ss")
        @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
        @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
        LocalDateTime dataHora,

        @NotBlank(message = "O KW do consumo do equipamento deve ser informado")
        Double kwConsumo,

        @NotBlank(message = "O ID do equipamento deve ser informado")
        TEquipamento equipamento
) {
}