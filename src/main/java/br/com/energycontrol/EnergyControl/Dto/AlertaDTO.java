package br.com.energycontrol.EnergyControl.DTO; // Ajuste o pacote conforme a estrutura do seu projeto

import jakarta.validation.constraints.NotBlank;

import java.sql.Timestamp;

public record AlertaDTO(
        Long id,

        @NotBlank(message = "A hora deve ser informado")
        Timestamp dataHora,

        @NotBlank(message = "A descrição não pode estar em branco")
        String descricaoAlerta,

        @NotBlank(message = "O ID do Limite precisa ser informado")
        Long limiteId) {
}