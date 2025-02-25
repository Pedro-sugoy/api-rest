package br.com.fiap.api_rest.controller;

import br.com.fiap.api_rest.dto.ClienteRequest;
import br.com.fiap.api_rest.dto.ClienteResponse;
import br.com.fiap.api_rest.model.Cliente;
import br.com.fiap.api_rest.repository.ClienteRepository;
import br.com.fiap.api_rest.service.ClienteService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.apache.logging.log4j.ThreadContext.isEmpty;

@RestController
@RequestMapping("/clientes")
public class ClienteController {
    @Autowired
    ClienteRepository clienteRepository;
    @Autowired
    ClienteService clienteService;

    //crud
    // create read update delete
    // post get put delete

    @PostMapping
    public ResponseEntity<Cliente> createCliente(@RequestBody ClienteRequest cliente) {
        Cliente clienteSalvo = clienteRepository.save(clienteService.requestToCliente(cliente));
        return new ResponseEntity<>(clienteSalvo, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<Page<ClienteResponse>> readClientes(@RequestParam int page) {
        Pageable pageable = PageRequest
                .of(0,page > 0 ? page : 2, Sort.by("categoria")
                        .ascending()
                .and(Sort.by("nome").ascending()));
        List<Cliente> clientes = clienteRepository.findAll();
        return new ResponseEntity<>(clienteService.findAll(pageable), HttpStatus.OK);
    }

    //PathVariable = parametro diretamente na url ex: /cliente/
    //RequestParam = paramtro como o qurey ex: /cliente/?id=1
    @GetMapping("/{id}")
    public ResponseEntity <ClienteResponse> readCliente(@PathVariable Long id) {
        Optional<Cliente> cliente = clienteRepository.findById(id);
        if (cliente.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(clienteService.clienteToResponse(cliente.get()), HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Cliente> updateCliente(@PathVariable Long id, @RequestBody Cliente cliente){
        Optional<Cliente> clienteExistente = clienteRepository.findById(id);
        if (clienteExistente.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        cliente.setId(clienteExistente.get().getId());
        Cliente clienteAtualizado = clienteRepository.save(cliente);
        return new ResponseEntity<>(clienteAtualizado, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public  ResponseEntity<Void> deleteCliente(@PathVariable Long id){
        clienteRepository.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
