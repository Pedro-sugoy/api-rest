package br.com.fiap.api_rest.controller;

import br.com.fiap.api_rest.model.Filial;
import br.com.fiap.api_rest.service.FilialService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/filiais")
public class FilialController {

    @Autowired
    private FilialService filialService;

    // Criar uma nova filial
    @PostMapping
    public ResponseEntity<Filial> createFilial(@RequestBody Filial filial) {
        Filial savedFilial = filialService.save(filial);
        return new ResponseEntity<>(savedFilial, HttpStatus.CREATED);
    }

    // Buscar todas as filiais
    @GetMapping
    public ResponseEntity<List<Filial>> getAllFiliais() {
        List<Filial> filiais = filialService.findAll();
        return new ResponseEntity<>(filiais, HttpStatus.OK);
    }

    // Buscar uma filial por ID
    @GetMapping("/{id}")
    public ResponseEntity<Filial> getFilialById(@PathVariable Long id) {
        Optional<Filial> filial = filialService.findById(id);
        return filial.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    // Atualizar uma filial existente
    @PutMapping("/{id}")
    public ResponseEntity<Filial> updateFilial(@PathVariable Long id, @RequestBody Filial filial) {
        if (filialService.existsById(id)) {
            filial.setId(id);
            Filial updatedFilial = filialService.save(filial);
            return new ResponseEntity<>(updatedFilial, HttpStatus.OK);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    // Deletar uma filial
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteFilial(@PathVariable Long id) {
        if (filialService.existsById(id)) {
            filialService.deleteById(id);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
}
