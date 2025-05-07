package br.com.energycontrol.EnergyControl.Service;

import br.com.energycontrol.EnergyControl.Model.Consumo;
import br.com.energycontrol.EnergyControl.Repository.TConsumoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TConsumoService {

    private final TConsumoRepository repository;

    @Autowired
    public TConsumoService(TConsumoRepository repository) {
        this.repository = repository;
    }

    public List<Consumo> listarTodos() {
        return repository.findAll();
    }

    public Optional<Consumo> buscarPorId(Long id) {
        return repository.findById(id);
    }

    public Consumo salvar(Consumo consumo) {
        return repository.save(consumo);
    }

    public void deletar(Long id) {
        repository.deleteById(id);
    }
}