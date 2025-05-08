package br.com.energycontrol.EnergyControl.Service;

import br.com.energycontrol.EnergyControl.Model.TLogs;
import br.com.energycontrol.EnergyControl.Repository.TLogsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TLogsService {
    private final TLogsRepository repository;

    @Autowired
    public TLogsService(TLogsRepository repository) {
        this.repository = repository;
    }

    public Optional<TLogs> searchById(Long id) {
        return repository.findById(id);
    }

    public List<TLogs> listAll() {
        return repository.findAll();
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }
}
