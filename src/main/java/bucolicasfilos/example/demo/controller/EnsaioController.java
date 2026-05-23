package bucolicasfilos.example.demo.controller;

import bucolicasfilos.example.demo.model.Ensaio;
import bucolicasfilos.example.demo.service.EnsaioService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/ensaios")
public class EnsaioController {

    private final EnsaioService ensaioService;

    public EnsaioController(EnsaioService ensaioService) {
        this.ensaioService = ensaioService;
    }

    @GetMapping
    public List<Ensaio> listarTodos() {
        return ensaioService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Ensaio> buscarPorId(@PathVariable Long id) {
        return ensaioService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Ensaio criar(@RequestBody Ensaio ensaio) {
        return ensaioService.save(ensaio);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Ensaio> atualizar(@PathVariable Long id, @RequestBody Ensaio ensaio) {
        try {
            return ResponseEntity.ok(ensaioService.update(id, ensaio));
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        ensaioService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
