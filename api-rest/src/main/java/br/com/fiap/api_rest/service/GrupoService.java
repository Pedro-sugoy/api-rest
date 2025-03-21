package br.com.fiap.api_rest.service;

import br.com.fiap.api_rest.model.Grupo;
import br.com.fiap.api_rest.repository.GrupoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class GrupoService {

    @Autowired
    private GrupoRepository grupoRepository;

    public Grupo save(Grupo grupo) {
        return grupoRepository.save(grupo);
    }

    public List<Grupo> findAll() {
        return grupoRepository.findAll();
    }

    public Optional<Grupo> findById(Long id) {
        return grupoRepository.findById(id);
    }

    public boolean existsById(Long id) {
        return grupoRepository.existsById(id);
    }

    public void deleteById(Long id) {
        grupoRepository.deleteById(id);
    }
}
