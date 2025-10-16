package br.com.energycontrol.EnergyControl.service;

import br.com.energycontrol.EnergyControl.repository.TSetorRepository;
import br.com.energycontrol.EnergyControl.model.TSetor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TSetorService {

    private final TSetorRepository repository;

    @Autowired
    public TSetorService(TSetorRepository repository, TSetorRepository tSetorRepository) {
        this.repository = repository;
    }

    public TSetor save(TSetor setor) {
        return repository.save(setor);
    }

    public Optional<TSetor> searchById(Long id) {
        return repository.findById(id);
    }

    public List<TSetor> listAll() {
        return repository.findAll();
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }

    public TSetor update(Long id, TSetor setor){
        return repository.findById(id).map(existing -> {
                    existing.setNmSetor(setor.getNmSetor());
                    existing.setNrAndar(setor.getNrAndar());

                    return repository.save(existing);
                }).orElseThrow(() -> new RuntimeException("Setor não encontrado com o id" + id));
    }
}
