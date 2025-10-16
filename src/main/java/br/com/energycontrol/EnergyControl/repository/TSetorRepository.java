package br.com.energycontrol.EnergyControl.repository;

import br.com.energycontrol.EnergyControl.model.TSetor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TSetorRepository extends JpaRepository<TSetor, Long> {
}

