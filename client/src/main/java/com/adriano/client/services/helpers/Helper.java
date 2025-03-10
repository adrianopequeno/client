package com.adriano.client.services.helpers;

import com.adriano.client.dto.ClientDTO;
import com.adriano.client.entities.Client;

public class Helper {
    public static Client copyDtoToEntity(ClientDTO dto, Client entity) {
        entity.setName(dto.getName());
        entity.setCpf(dto.getCpf());
        entity.setIncome(dto.getIncome());
        entity.setBirthDate(dto.getBirthDate());
        entity.setChildren(dto.getChildren());
        return entity;
    }
}
