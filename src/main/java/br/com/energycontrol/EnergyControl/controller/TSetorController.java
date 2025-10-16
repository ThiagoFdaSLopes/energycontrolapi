package br.com.energycontrol.EnergyControl.controller;

import br.com.energycontrol.EnergyControl.model.TSetor;
import br.com.energycontrol.EnergyControl.service.TSetorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/setores")
public class TSetorController {

    private final TSetorService service;

    @Autowired
    public TSetorController(TSetorService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<TSetor> save(@RequestBody TSetor setor) {
        TSetor criado = service.save(setor);
        return ResponseEntity.ok(criado);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TSetor> searchById(@PathVariable Long id) {
        return service.searchById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping
    public ResponseEntity<List<TSetor>> listAll() {
        List<TSetor> lista = service.listAll();
        return ResponseEntity.ok(lista);
    }

    @PutMapping("/{id}")
    public ResponseEntity<TSetor> update(@PathVariable Long id, @RequestBody TSetor setor) {
        try {
            TSetor atualizado = service.update(id, setor);
            return ResponseEntity.ok(atualizado);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
