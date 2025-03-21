package br.com.fiap.api_rest.service;

import br.com.fiap.api_rest.model.Endereco;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EnderecoService {

    @Autowired
    private JpaRepository<Endereco, Long> enderecoRepository;

    public Endereco save(Endereco endereco) {
        return enderecoRepository.save(endereco);
    }

    public List<Endereco> findAll() {
        return enderecoRepository.findAll();
    }

    public Optional<Endereco> findById(Long id) {
        return enderecoRepository.findById(id);
    }

    public boolean existsById(Long id) {
        return enderecoRepository.existsById(id);
    }

    public void deleteById(Long id) {
        enderecoRepository.deleteById(id);
    }
}
