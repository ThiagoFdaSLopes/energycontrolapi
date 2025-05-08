package br.com.energycontrol.EnergyControl.Repository;

import br.com.energycontrol.EnergyControl.Model.TLogs;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TLogsRepository extends JpaRepository<TLogs, Long> {
}
