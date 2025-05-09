package br.com.energycontrol.EnergyControl.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import br.com.energycontrol.EnergyControl.Model.Consumo;

public interface TConsumoRepository extends JpaRepository<Consumo, Long> {
}
