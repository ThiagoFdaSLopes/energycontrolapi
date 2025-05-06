package br.com.energycontrol.EnergyControl.Service;

import br.com.energycontrol.EnergyControl.Model.TEquipamento;
import br.com.energycontrol.EnergyControl.Repository.TEquipamentoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TEquipamentoService {

    private final TEquipamentoRepository repository;

    @Autowired
    public TEquipamentoService(TEquipamentoRepository repository) {
        this.repository = repository;
    }

    public TEquipamento save(TEquipamento equipamento) {
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
                    existing.setSetor(equipamento.getSetor());
                    return repository.save(existing);
                })
                .orElseThrow(() -> new RuntimeException("Equipamento não encontrado com o id " + id));
    }

}
