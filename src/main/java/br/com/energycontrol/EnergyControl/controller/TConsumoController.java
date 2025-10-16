package br.com.energycontrol.EnergyControl.controller;

import br.com.energycontrol.EnergyControl.dto.ConsumoCadastroDTO;
import br.com.energycontrol.EnergyControl.model.Consumo;
import br.com.energycontrol.EnergyControl.service.TConsumoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/consumo")
public class TConsumoController {

    private final TConsumoService consumoService;

    @Autowired
    public TConsumoController(TConsumoService consumoService) {
        this.consumoService = consumoService;
    }

    @GetMapping
    public ResponseEntity<List<Consumo>> listarTodos() {
        List<Consumo> consumos = consumoService.listarTodos();
        return new ResponseEntity<>(consumos, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Consumo> buscarPorId(@PathVariable Long id) {
        Optional<Consumo> consumo = consumoService.buscarPorId(id);
        return consumo.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping
    public ResponseEntity<Consumo> salvar(@RequestBody ConsumoCadastroDTO consumoCadastroDTO) {
        Consumo novoConsumo = consumoService.converterParaEntidade(consumoCadastroDTO);
        Consumo salvo = consumoService.salvar(novoConsumo);
        return new ResponseEntity<>(novoConsumo, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        consumoService.deletar(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Consumo> atualizar(@PathVariable Long id, @RequestBody ConsumoCadastroDTO consumoCadastroDTO) {
        Optional<Consumo> consumoExistente = consumoService.buscarPorId(id);

        if (consumoExistente.isPresent()) {
            Consumo consumoParaAtualizar = consumoService.converterParaEntidade(consumoCadastroDTO);
            consumoParaAtualizar.setId(id);
            Consumo consumoAtualizado = consumoService.salvar(consumoParaAtualizar);
            return new ResponseEntity<>(consumoAtualizado, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}