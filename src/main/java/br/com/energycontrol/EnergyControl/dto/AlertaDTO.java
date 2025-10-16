package br.com.energycontrol.EnergyControl.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotBlank;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;

public record AlertaDTO(
        Long id,

        @NotBlank(message = "A hora deve ser informado")
        @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
        @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
        LocalDateTime dataHora,

        @NotBlank(message = "A descrição não pode estar em branco")
        String descricaoAlerta,

        @NotBlank(message = "O ID do Limite precisa ser informado")
        Long limiteId) {
}