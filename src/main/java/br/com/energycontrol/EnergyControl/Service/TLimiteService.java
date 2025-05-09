package br.com.energycontrol.EnergyControl.Service;

import br.com.energycontrol.EnergyControl.Model.TLimite;
import br.com.energycontrol.EnergyControl.Model.TEquipamento;
import br.com.energycontrol.EnergyControl.Repository.TLimiteRepository;
import br.com.energycontrol.EnergyControl.Repository.TEquipamentoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TLimiteService {

    private final TLimiteRepository repository;
    private final TEquipamentoRepository equipamentoRepo;

    @Autowired
    public TLimiteService(TLimiteRepository repository,
                          TEquipamentoRepository equipamentoRepo) {
        this.repository     = repository;
        this.equipamentoRepo = equipamentoRepo;
    }

    public TLimite save(TLimite limite) {
        // Busca o equipamento completo antes de salvar
        Long idEquip = limite.getEquipamento().getIdEquip();
        TEquipamento equipamento = equipamentoRepo.findById(idEquip)
                .orElseThrow(() -> new RuntimeException("Equipamento não encontrado com o id " + idEquip));
        limite.setEquipamento(equipamento);
        return repository.save(limite);
    }

    public Optional<TLimite> searchById(Long id) {
        return repository.findById(id);
    }

    public List<TLimite> listAll() {
        return repository.findAll();
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }

    public TLimite update(Long id, TLimite dto) {
        return repository.findById(id)
                .map(existing -> {
                    existing.setLtConsumo(dto.getLtConsumo());
                    existing.setHrInicio(dto.getHrInicio());
                    existing.setHrFim(dto.getHrFim());

                    // Se veio equipamento no payload, busca o objeto completo para atribuir
                    if (dto.getEquipamento() != null) {
                        Long idEquip = dto.getEquipamento().getIdEquip();
                        TEquipamento equipamento = equipamentoRepo.findById(idEquip)
                                .orElseThrow(() -> new RuntimeException("Equipamento não encontrado com o id " + idEquip));
                        existing.setEquipamento(equipamento);
                    }

                    return repository.save(existing);
                })
                .orElseThrow(() -> new RuntimeException("Limite não encontrado com o id " + id));
    }
}
