package com.adriano.client.services;

import com.adriano.client.dto.ClientDTO;
import com.adriano.client.entities.Client;
import com.adriano.client.repositories.ClientRepository;
import com.adriano.client.services.exceptions.ResourceNotFoundException;
import com.adriano.client.services.helpers.Helper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class ClientService {

    @Autowired
    private ClientRepository clientRepository;

    @Transactional(readOnly = true)
    public Page<ClientDTO> findAllPage(PageRequest pageRequest) {
        Page<Client> list = clientRepository.findAll(pageRequest);
        return list.map(ClientDTO::new);
    }

    @Transactional(readOnly = true)
    public ClientDTO findById(Long id) {
        Optional<Client> optional = clientRepository.findById(id);
        Client client = optional.orElseThrow(() -> new ResourceNotFoundException("Entity not found"));
        return new ClientDTO(client);
    }

    @Transactional
    public ClientDTO insert(ClientDTO dto) {
        Client entity = new Client();
        entity = clientRepository.save(Helper.copyDtoToEntity(dto, entity));
        return new ClientDTO(entity);
    }

}
