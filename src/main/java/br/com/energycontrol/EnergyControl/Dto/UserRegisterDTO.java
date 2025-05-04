package br.com.energycontrol.EnergyControl.Dto;

import br.com.energycontrol.EnergyControl.Model.UserRole;

public record UserRegisterDTO(Long user_id, String user_name, String email, String user_password, UserRole user_role) {
}
