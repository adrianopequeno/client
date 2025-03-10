package com.adriano.client.resources;

import com.adriano.client.dto.ClientDTO;
import com.adriano.client.dto.ClientPageTDO;
import com.adriano.client.services.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value="/clients")
public class ClientResource {

    @Autowired
    private ClientService clientService;

    @GetMapping
    public ResponseEntity<ClientPageTDO<ClientDTO>> findAllPage(
                @RequestParam(value = "page", defaultValue = "0") Integer page,
                @RequestParam(value = "linesPerPage", defaultValue = "12") Integer linesPerPage,
                @RequestParam(value = "direction", defaultValue = "ASC") String direction,
                @RequestParam(value = "orderBy", defaultValue = "name") String orderBy){
        PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);

        Page<ClientDTO> list = clientService.findAllPage(pageRequest);
        return ResponseEntity.ok(new ClientPageTDO<>(list));
    }
}
