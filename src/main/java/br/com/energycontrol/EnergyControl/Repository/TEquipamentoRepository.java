package br.com.energycontrol.EnergyControl.Repository;

import br.com.energycontrol.EnergyControl.Model.TEquipamento;
import br.com.energycontrol.EnergyControl.Model.TSetor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TEquipamentoRepository extends JpaRepository<TEquipamento, Long> {
}
