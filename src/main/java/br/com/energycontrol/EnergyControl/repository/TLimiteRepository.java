package br.com.energycontrol.EnergyControl.repository;

import br.com.energycontrol.EnergyControl.model.TLimite;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TLimiteRepository extends JpaRepository<TLimite, Long> {
}
