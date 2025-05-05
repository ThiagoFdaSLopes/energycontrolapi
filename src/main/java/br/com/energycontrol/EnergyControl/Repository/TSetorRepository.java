package br.com.energycontrol.EnergyControl.Repository;

import br.com.energycontrol.EnergyControl.Model.TSetor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TSetorRepository extends JpaRepository<TSetor, Long> {
}

