package br.com.fiap.api_rest.repository;

import br.com.fiap.api_rest.model.Categoria;
import br.com.fiap.api_rest.model.Cliente;
import lombok.Data;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Long> {
    List<Cliente> findByNome(String nome);
    List<Cliente> findByNomeIs(String nome);
    List<Cliente> findByNomeEquals(String nome);
    List<Cliente> findByNomeEqualsIgnoreCase(String nome);
    List<Cliente> findByNomeNot(String nome);
    List<Cliente> findByNomeIsNot(String nome);
    List<Cliente> findByNomeIsNotEquals(String nome);

    List<Cliente> findByIdade(int idade);
    List<Cliente> findByEmailIsNull();
    List<Cliente> findByEmailIsNotNull();

    List<Cliente> findBySenha(String senha);
    List<Cliente> findBySenhaIsNull(String senha);
    List<Cliente> findBySenhaIsNotNull(String senha);

    List<Cliente> findByCpf(int cpf);
    List<Cliente> findByCpfEquals(int cpf);
    List<Cliente> findByCpfEqualsIgnoreCase(int cpf);
    List<Cliente> findByCpfNot(int cpf);
    List<Cliente> findByCpfIsNot(int cpf);
    List<Cliente> findByCpfIsNotEquals(int cpf);

    List<Cliente> findByCategoria(Categoria categoria);
    List<Cliente> findByCategoriaId(Long categoria);
    List<Cliente> findByCategoria_Name(String categoria);

    List<Cliente> findByDataNascimento(LocalDate dataNascimento);
    List<Cliente> findByDataNascimentoBetween(LocalDate startDate, LocalDate endDate);

    List<Cliente> findByVia(Boolean via);
    List<Cliente> findByViaTrue();
    List<Cliente> findByViaFalse();
}
