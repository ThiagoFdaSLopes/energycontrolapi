package br.com.energycontrol.EnergyControl.dto;

import br.com.energycontrol.EnergyControl.model.UserRole;

public record UserRegisterDTO(Long user_id, String user_name, String email, String user_password, UserRole user_role) {
}
