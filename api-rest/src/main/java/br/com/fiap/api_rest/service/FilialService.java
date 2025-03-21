package br.com.fiap.api_rest.service;

import br.com.fiap.api_rest.model.Filial;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FilialService {

    @Autowired
    private JpaRepository<Filial, Long> filialRepository;

    public Filial save(Filial filial) {
        return filialRepository.save(filial);
    }

    public List<Filial> findAll() {
        return filialRepository.findAll();
    }

    public Optional<Filial> findById(Long id) {
        return filialRepository.findById(id);
    }

    public boolean existsById(Long id) {
        return filialRepository.existsById(id);
    }

    public void deleteById(Long id) {
        filialRepository.deleteById(id);
    }

    public List<Filial> findByNome(String nome) {
        return filialRepository.findAll().stream()
                .filter(filial -> filial.getNome().equalsIgnoreCase(nome))
                .toList();
    }
}
