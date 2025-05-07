package br.com.energycontrol.EnergyControl.Repository;

import br.com.energycontrol.EnergyControl.Model.TLimite;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TLimiteRepository extends JpaRepository<TLimite, Long> {
}
