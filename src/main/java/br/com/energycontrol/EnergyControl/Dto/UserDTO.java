package br.com.energycontrol.EnergyControl.Dto;

import br.com.energycontrol.EnergyControl.Model.User;
import br.com.energycontrol.EnergyControl.Model.UserRole;

public record UserDTO(Long user_id, String user_name, String email, UserRole user_role) {
    public UserDTO(User user) {
        this(user.getUser_id(), user.getUser_name(), user.getEmail(), user.getUser_role());
    }
}
