package br.com.fiap.api_rest.controller;

import br.com.fiap.api_rest.dto.ClienteRequest;
import br.com.fiap.api_rest.dto.ClienteResponse;
import br.com.fiap.api_rest.model.Cliente;
import br.com.fiap.api_rest.repository.ClienteRepository;
import br.com.fiap.api_rest.service.ClienteService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
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

    @Operation(summary = "Cria um novo cliente")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Cliente cadastrado com sucesso ",
                content = {@Content(mediaType = "application/json", schema = @Schema(implementation = Cliente.class))}),
            @ApiResponse(responseCode = "400", description = "Atributos informados são inválidos",
                content = @Content(schema = @Schema()))
    })
    @PostMapping
    public ResponseEntity<Cliente> createCliente(@Valid @RequestBody ClienteRequest cliente) {
        Cliente clienteSalvo = clienteRepository.save(clienteService.requestToCliente(cliente));
        return new ResponseEntity<>(clienteSalvo, HttpStatus.CREATED);
    }

    @Operation(summary = "Retorna uma lista de clientes")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Cliente cadastrado com sucesso ",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = Cliente.class))}),
            @ApiResponse(responseCode = "400", description = "Atributos informados são inválidos",
                    content = @Content(schema = @Schema()))
    })
    @GetMapping
    public ResponseEntity<Page<ClienteResponse>> readClientes(@RequestParam(defaultValue = "0") Integer page) {
        Pageable pageable = PageRequest
                .of(0,page > 0 ? page : 2, Sort.by("categoria")
                        .ascending()
                .and(Sort.by("nome").ascending()));
        List<Cliente> clientes = clienteRepository.findAll();
        return new ResponseEntity<>(clienteService.findAll(pageable), HttpStatus.OK);
    }

    //PathVariable = parametro diretamente na url ex: /cliente/
    //RequestParam = paramtro como o qurey ex: /cliente/?id=1
    @Operation(summary = "Retorna um cliente por id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Cliente encontrado com sucesso",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = Cliente.class))}),
            @ApiResponse(responseCode = "404", description = "Nenhum cliente encontrado",
                    content = @Content(schema = @Schema()))
    })
    @GetMapping("/{id}")
    public ResponseEntity <ClienteResponse> readCliente(@PathVariable Long id) {
        Optional<Cliente> cliente = clienteRepository.findById(id);
        if (cliente.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(clienteService.clienteToResponse(cliente.get(), false), HttpStatus.OK);
    }

    @Operation(summary = "Atualiza um cliente por id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Cliente encontrado e atualizado com sucesso ",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = Cliente.class))}),
            @ApiResponse(responseCode = "400", description = "Nenum cliente encontrado para atualizar",
                    content = @Content(schema = @Schema()))
    })
    @PutMapping("/{id}")
    public ResponseEntity<Cliente> updateCliente(@PathVariable Long id, @RequestBody Cliente cliente){
        Optional<Cliente> clienteExistente = clienteRepository.findById(id);
        if (clienteExistente.isEmpty()){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        cliente.setId(clienteExistente.get().getId());
        Cliente clienteAtualizado = clienteRepository.save(cliente);
        return new ResponseEntity<>(clienteAtualizado, HttpStatus.CREATED);
    }

    @Operation(summary = "exclui um cliente por id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Cliente excluido com sucesso",
                    content = @Content(schema = @Schema())),
            @ApiResponse(responseCode = "404", description = "nenhum cliente encontrado",
                    content = @Content(schema = @Schema()))
    })
    @DeleteMapping("/{id}")
    public  ResponseEntity<Void> deleteCliente(@PathVariable Long id){
        clienteRepository.deleteById(id);
        Optional<Cliente> clienteExistente = clienteRepository.findById(id);
        if (clienteExistente.isEmpty()){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
