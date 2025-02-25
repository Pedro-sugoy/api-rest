package br.com.fiap.api_rest.service;

import br.com.fiap.api_rest.dto.ClienteRequest;
import br.com.fiap.api_rest.dto.ClienteResponse;
import br.com.fiap.api_rest.model.Cliente;
import br.com.fiap.api_rest.repository.ClienteRepository;
import jakarta.validation.constraints.Null;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ClienteService {
    public Cliente requestToCliente(ClienteRequest clienterequest){
        return new Cliente(null,
                clienterequest.getNome(),
                clienterequest.getIdade(),
                clienterequest.getSenha(),
                clienterequest.getEmail(),
                clienterequest.getCpf(),
                clienterequest.getCategoria());

    }

    @Autowired
    ClienteRepository clienteRepository;

        public ClienteResponse clienteToResponse(Cliente cliente){
            return new ClienteResponse(cliente.getId(), cliente.getNome());
        }

        public List<ClienteResponse> clientesToResponse(List<Cliente> clientes){
            List<ClienteResponse> clientesResponse = new ArrayList<>();
            for (Cliente cliente : clientes){
                clientesResponse.add(clienteToResponse(cliente));
            }
            return clientesResponse;
            //return clientes.stream().map(this::clienteToResponse).collect(Collectors.toList());
        }

        public Page<ClienteResponse> findAll(Pageable pegeable){
            return clienteRepository.findAll(pegeable).map(this:: clienteToResponse);
    }
}
