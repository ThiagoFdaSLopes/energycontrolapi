package br.com.energycontrol.EnergyControl.repository;

import br.com.energycontrol.EnergyControl.model.Alerta;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AlertaRepository extends JpaRepository<Alerta, Long> {
}
