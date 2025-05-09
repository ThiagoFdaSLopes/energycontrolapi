package br.com.energycontrol.EnergyControl.Service;

import br.com.energycontrol.EnergyControl.Dto.ConsumoCadastroDTO;
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

    public Consumo converterParaEntidade(ConsumoCadastroDTO dto) {
        Consumo consumo = new Consumo();
        consumo.setDataHora(dto.dataHora());
        consumo.setKwConsumo(dto.kwConsumo());
        consumo.setEquipamento(dto.equipamento());
        return consumo;
    }

    public void deletar(Long id) {
        repository.deleteById(id);
    }

    public Consumo atualizarConsumo(Long id, ConsumoCadastroDTO dto) {
        Optional<Consumo> consumoExistente = repository.findById(id);

        if (consumoExistente.isPresent()) {
            Consumo consumoParaAtualizar = consumoExistente.get();
            consumoParaAtualizar.setDataHora(dto.dataHora());
            consumoParaAtualizar.setKwConsumo(dto.kwConsumo());
            consumoParaAtualizar.setEquipamento(dto.equipamento());
            return repository.save(consumoParaAtualizar);
        } else {

            return null;
        }
    }

}