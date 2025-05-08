package br.com.energycontrol.EnergyControl.Service;

import br.com.energycontrol.EnergyControl.Model.TLimite;
import br.com.energycontrol.EnergyControl.Repository.TLimiteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TLimiteService {

    private final TLimiteRepository repository;

    @Autowired
    public TLimiteService(TLimiteRepository repository) {
        this.repository = repository;
    }

    public TLimite save(TLimite limite) {
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

    public TLimite update(Long id, TLimite limite) {
        return repository.findById(id)
                .map(existing -> {
                    existing.setLtConsumo(limite.getLtConsumo());
                    existing.setHrInicio(limite.getHrInicio());
                    existing.setHrFim(limite.getHrFim());
                    existing.setEquipamento(limite.getEquipamento());
                    return repository.save(existing);
                })
                .orElseThrow(() -> new RuntimeException("Limite não encontrado com o id " + id));
    }
}
