package br.com.fiap.api_rest.repository;

import br.com.fiap.api_rest.model.Grupo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EnderecoRepository extends JpaRepository<Grupo, Long> {
}
