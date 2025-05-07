package br.com.energycontrol.EnergyControl.Dto;

import java.sql.Timestamp;

public record ConsumoCadastroDTO(
        Long id,
        Timestamp dataHora,
        Double kwConsumo
        //TEquipamento equipamento
){

}
