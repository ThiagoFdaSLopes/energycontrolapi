package br.com.energycontrol.EnergyControl.repository;

import br.com.energycontrol.EnergyControl.model.TEquipamento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TEquipamentoRepository extends JpaRepository<TEquipamento, Long> {
}
