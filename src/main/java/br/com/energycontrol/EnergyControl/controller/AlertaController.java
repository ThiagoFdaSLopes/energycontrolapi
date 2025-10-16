package br.com.energycontrol.EnergyControl.controller;

import br.com.energycontrol.EnergyControl.dto.AlertaDTO;
import br.com.energycontrol.EnergyControl.model.Alerta;
import br.com.energycontrol.EnergyControl.model.TLimite;
import br.com.energycontrol.EnergyControl.service.AlertaService;
import br.com.energycontrol.EnergyControl.service.TLimiteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/alertas")
public class AlertaController {

    private final AlertaService alertaService;
    private final TLimiteService limiteService;

    @Autowired
    public AlertaController(AlertaService alertaService, TLimiteService limiteService) {
        this.alertaService = alertaService;
        this.limiteService = limiteService;
    }

    @GetMapping
    public ResponseEntity<List<AlertaDTO>> listarTodos() {
        List<Alerta> alertas = alertaService.listarTodos();
        List<AlertaDTO> alertasDTO = alertas.stream()
                .map(this::converterParaDTO)
                .collect(Collectors.toList());
        return new ResponseEntity<>(alertasDTO, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<AlertaDTO> buscarPorId(@PathVariable Long id) {
        Optional<Alerta> alerta = alertaService.buscarPorId(id);
        return alerta.map(this::converterParaDTO)
                .map(dto -> new ResponseEntity<>(dto, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping
    public ResponseEntity<AlertaDTO> salvar(@RequestBody AlertaDTO alertaDTO) {
        Alerta alerta = converterParaEntidade(alertaDTO);
        Alerta novoAlerta = alertaService.salvar(alerta);
        AlertaDTO novoAlertaDTO = converterParaDTO(novoAlerta);
        return new ResponseEntity<>(novoAlertaDTO, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        alertaService.deletar(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    // Métodos de conversão entre Entidade e DTO
    private AlertaDTO converterParaDTO(Alerta alerta) {
        return new AlertaDTO(
                alerta.getIdAlerta(),
                alerta.getDataHora(),
                alerta.getDescricaoAlerta(),
                alerta.getLimite() != null ? alerta.getLimite().getIdLmt() : null
        );
    }

    private Alerta converterParaEntidade(AlertaDTO alertaDTO) {
        Alerta alerta = new Alerta();
        alerta.setIdAlerta(alertaDTO.id());
        alerta.setDataHora(alertaDTO.dataHora());
        alerta.setDescricaoAlerta(alertaDTO.descricaoAlerta());

        // Busca a entidade TLimite pelo ID fornecido no DTO
        TLimite limite = limiteService.searchById(alertaDTO.limiteId())
                .orElseThrow(() -> new RuntimeException("Limite não encontrado com ID: " + alertaDTO.limiteId()));
        alerta.setLimite(limite);

        return alerta;
    }

    @PutMapping("/{id}")
    public ResponseEntity<AlertaDTO> atualizar(@PathVariable Long id, @RequestBody AlertaDTO alertaDTO) {
        Alerta alertaAtualizado = alertaService.atualizar(id, alertaDTO);
        if (alertaAtualizado != null) {
            return new ResponseEntity<>(converterParaDTO(alertaAtualizado), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

}