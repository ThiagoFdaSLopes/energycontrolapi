package br.com.energycontrol.EnergyControl.Repository;

import br.com.energycontrol.EnergyControl.Model.Alerta;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AlertaRepository extends JpaRepository<Alerta, Long> {
}
