package br.com.energycontrol.EnergyControl.Service;

import br.com.energycontrol.EnergyControl.Dto.AlertaDTO;
import br.com.energycontrol.EnergyControl.Model.Alerta;
import br.com.energycontrol.EnergyControl.Model.TLimite;
import br.com.energycontrol.EnergyControl.Repository.AlertaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AlertaService {

    private final TLimiteService tLimiteService;
    private final AlertaRepository alertaRepository;

    @Autowired
    public AlertaService(AlertaRepository alertaRepository, TLimiteService tLimiteService) {
        this.alertaRepository = alertaRepository;
        this.tLimiteService = tLimiteService;
    }

    public List<Alerta> listarTodos() {
        return alertaRepository.findAll();
    }

    public Optional<Alerta> buscarPorId(Long id) {
        return alertaRepository.findById(id);
    }

    public Alerta salvar(Alerta alerta) {
        return alertaRepository.save(alerta);
    }

    public void deletar(Long id) {
        alertaRepository.deleteById(id);
    }

    public Alerta atualizar(Long id, AlertaDTO alertaDTO) {
        Optional<Alerta> alertaExistente = alertaRepository.findById(id);

        if (alertaExistente.isPresent()) {
            Alerta alertaParaAtualizar = alertaExistente.get();
            alertaParaAtualizar.setDataHora(alertaDTO.dataHora());
            alertaParaAtualizar.setDescricaoAlerta(alertaDTO.descricaoAlerta());

            // Busca a entidade TLimite pelo ID fornecido no DTO
            TLimite limite = tLimiteService.searchById(alertaDTO.limiteId())
                    .orElseThrow(() -> new RuntimeException("Limite não encontrado com ID: " + alertaDTO.limiteId()));
            alertaParaAtualizar.setLimite(limite);

            return alertaRepository.save(alertaParaAtualizar);
        } else {
            return null; // Ou lançar uma exceção informando que o alerta não foi encontrado
        }
    }

}