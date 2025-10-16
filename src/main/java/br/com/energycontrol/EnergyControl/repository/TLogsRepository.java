package br.com.energycontrol.EnergyControl.repository;

import br.com.energycontrol.EnergyControl.model.TLogs;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TLogsRepository extends JpaRepository<TLogs, Long> {
}
