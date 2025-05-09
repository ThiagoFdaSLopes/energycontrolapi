package br.com.energycontrol.EnergyControl.Controller;

import br.com.energycontrol.EnergyControl.Model.TEquipamento;
import br.com.energycontrol.EnergyControl.Model.TSetor;
import br.com.energycontrol.EnergyControl.Service.TEquipamentoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/equipamentos")
public class TEquipamentoController {

    private final TEquipamentoService service;

    @Autowired
    public TEquipamentoController(TEquipamentoService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<TEquipamento> criarEquipamento(@RequestBody TEquipamento equipamento) {
        return ResponseEntity.ok(service.save(equipamento));
    }

    @GetMapping("/{id}")
    public ResponseEntity<TEquipamento> obterEquipamento(@PathVariable Long id) {
        return service.searchById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping
    public ResponseEntity<List<TEquipamento>> listarEquipamentos() {
        List<TEquipamento> lista = service.listAll();
        return ResponseEntity.ok(lista);
    }

    @PutMapping("/{id}")
    public ResponseEntity<TEquipamento> atualizarEquipamento(@PathVariable Long id, @RequestBody TEquipamento equipamento) {
        try {
            return ResponseEntity.ok(service.update(id, equipamento));
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarEquipamento(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

}
