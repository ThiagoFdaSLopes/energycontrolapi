package br.com.energycontrol.EnergyControl.Service;

import br.com.energycontrol.EnergyControl.Model.TEquipamento;
import br.com.energycontrol.EnergyControl.Model.TSetor;
import br.com.energycontrol.EnergyControl.Repository.TEquipamentoRepository;
import br.com.energycontrol.EnergyControl.Repository.TSetorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TEquipamentoService {

    private final TEquipamentoRepository repository;
    private final TSetorRepository setorRepo;

    @Autowired
    public TEquipamentoService(TEquipamentoRepository repository, TSetorRepository setorRepo) {
        this.repository = repository;
        this.setorRepo = setorRepo;
    }

    public TEquipamento save(TEquipamento equipamento) {
        Long idSetor = equipamento.getSetor().getIdSet();
        TSetor setor = setorRepo.findById(idSetor)
                .orElseThrow(() -> new RuntimeException("Setor não encontrado com o id " + idSetor));

        equipamento.setSetor(setor);
        return repository.save(equipamento);
    }


    public Optional<TEquipamento> searchById(Long id) {
        return repository.findById(id);
    }

    public List<TEquipamento> listAll() {
        return repository.findAll();
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }

    public TEquipamento update(Long id, TEquipamento equipamento) {
        return repository.findById(id)
                .map(existing -> {
                    existing.setNmEquipamento(equipamento.getNmEquipamento());
                    existing.setTipo(equipamento.getTipo());

                    // Busca o setor completo antes de atribuir
                    Long idSetor = equipamento.getSetor().getIdSet();
                    TSetor setor = setorRepo.findById(idSetor)
                            .orElseThrow(() -> new RuntimeException("Setor não encontrado com o id " + idSetor));
                    existing.setSetor(setor);

                    return repository.save(existing);
                })
                .orElseThrow(() -> new RuntimeException("Equipamento não encontrado com o id " + id));
    }


}
