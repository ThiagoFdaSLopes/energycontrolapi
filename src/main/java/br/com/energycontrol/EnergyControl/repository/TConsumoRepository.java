package br.com.energycontrol.EnergyControl.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import br.com.energycontrol.EnergyControl.model.Consumo;

public interface TConsumoRepository extends JpaRepository<Consumo, Long> {
}
