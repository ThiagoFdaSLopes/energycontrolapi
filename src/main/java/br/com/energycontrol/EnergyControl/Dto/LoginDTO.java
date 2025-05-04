package br.com.energycontrol.EnergyControl.Dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record LoginDTO(@Email @NotBlank String email, @NotBlank @Size(min = 6, max = 6) String password) { }
