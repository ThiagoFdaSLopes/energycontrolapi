package br.com.energycontrol.EnergyControl.Dto;

import br.com.energycontrol.EnergyControl.Model.TEquipamento;
import jakarta.validation.constraints.NotBlank;

import java.sql.Timestamp;

public record ConsumoCadastroDTO(
        Long id,

        @NotBlank(message = "A data e hora deve ser informado")
        Timestamp dataHora,

        @NotBlank(message = "O KW do consumo do equipamento deve ser informado")
        Double kwConsumo,

        @NotBlank(message = "O ID do equipamento deve ser informado")
        TEquipamento equipamento
) {
}