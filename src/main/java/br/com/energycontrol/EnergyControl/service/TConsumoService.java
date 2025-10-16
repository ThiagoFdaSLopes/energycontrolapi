package br.com.energycontrol.EnergyControl.service;

import br.com.energycontrol.EnergyControl.dto.ConsumoCadastroDTO;
import br.com.energycontrol.EnergyControl.model.Consumo;
import br.com.energycontrol.EnergyControl.model.TEquipamento;
import br.com.energycontrol.EnergyControl.model.TSetor;
import br.com.energycontrol.EnergyControl.repository.TConsumoRepository;
import br.com.energycontrol.EnergyControl.repository.TEquipamentoRepository;
import br.com.energycontrol.EnergyControl.repository.TSetorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TConsumoService {

    private final TConsumoRepository repository;
    private final TEquipamentoRepository equipamentoRepository;
    private final TSetorRepository setorRepository;

    @Autowired
    public TConsumoService(TConsumoRepository repository, TEquipamentoRepository equipamentoRepository, TSetorRepository setorRepository) {
        this.repository = repository;
        this.equipamentoRepository = equipamentoRepository;
        this.setorRepository = setorRepository;
    }

    public List<Consumo> listarTodos() {
        return repository.findAll();
    }

    public Optional<Consumo> buscarPorId(Long id) {
        return repository.findById(id);
    }

    public Consumo salvar(Consumo consumo) {
        Long idEquip = consumo.getEquipamento().getIdEquip();
        TEquipamento equipamento = equipamentoRepository.findById(idEquip)
                .orElseThrow(() -> new RuntimeException("Equipamento não encontrado com o id " + idEquip));

        Long idSetor = equipamento.getSetor().getIdSet();
        TSetor setor = setorRepository.findById(idSetor)
                .orElseThrow(() -> new RuntimeException("Setor não encontrado com o id " + idSetor));

        equipamento.setSetor(setor);
        consumo.setEquipamento(equipamento);
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