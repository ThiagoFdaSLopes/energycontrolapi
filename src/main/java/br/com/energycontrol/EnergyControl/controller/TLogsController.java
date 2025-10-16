package br.com.energycontrol.EnergyControl.controller;

import br.com.energycontrol.EnergyControl.model.TLogs;
import br.com.energycontrol.EnergyControl.service.TLogsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/logs")
public class TLogsController {

    private final TLogsService service;

    @Autowired
    public TLogsController(TLogsService service) {
        this.service = service;
    }

    @GetMapping("/{id}")
    public ResponseEntity<TLogs> getLogById(@PathVariable Long id) {
        return service.searchById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping
    public ResponseEntity<List<TLogs>> getAllLogs() {
        List<TLogs> logs = service.listAll();
        return ResponseEntity.ok(logs);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteLog(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}