package br.com.energycontrol.EnergyControl.Controller;

import br.com.energycontrol.EnergyControl.Model.TLimite;
import br.com.energycontrol.EnergyControl.Service.TLimiteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/limites")
public class TLimiteController {

    private final TLimiteService service;

    @Autowired
    public TLimiteController(TLimiteService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<TLimite> criarLimite(@RequestBody TLimite limite) {
        return ResponseEntity.ok(service.save(limite));
    }

    @GetMapping("/{id}")
    public ResponseEntity<TLimite> obterLimite(@PathVariable Long id) {
        return service.searchById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping
    public ResponseEntity<List<TLimite>> listarLimites() {
        List<TLimite> lista = service.listAll();
        return ResponseEntity.ok(lista);
    }

    @PutMapping("/{id}")
    public ResponseEntity<TLimite> atualizarLimite(@PathVariable Long id, @RequestBody TLimite limite) {
        try {
            return ResponseEntity.ok(service.update(id, limite));
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarLimite(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
