package com.devsu.hackerearth.backend.client.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.devsu.hackerearth.backend.client.model.dto.ClientDto;
import com.devsu.hackerearth.backend.client.model.dto.PartialClientDto;
import com.devsu.hackerearth.backend.client.service.ClientService;

@RestController
@RequestMapping("/api/clients")
public class ClientController {

	@Autowired
	private ClientService clientService;

	public ClientController(ClientService clientService) {
		this.clientService = clientService;
	}

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public ResponseEntity<List<ClientDto>> getAll() {
		// api/clients
		// Get all clients
		return ResponseEntity.ok(clientService.getAll());
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<ClientDto> get(@PathVariable Long id) {
		// api/clients/{id}
		// Get clients by id
		return ResponseEntity.ok(clientService.getById(id));
	}

	@RequestMapping(value = "/", method = RequestMethod.POST)
	public ResponseEntity<ClientDto> create(@RequestBody ClientDto clientDto) {
		// api/clients
		// Create client
		return ResponseEntity.status(HttpStatus.CREATED).body(clientService.create(clientDto));
	}

	@RequestMapping(value = "/", method = RequestMethod.PUT)
	public ResponseEntity<ClientDto> update(@PathVariable Long id, @RequestBody ClientDto clientDto) {
		// api/clients/{id}
		// Update client
		return ResponseEntity.ok(clientService.update(clientDto));
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	public ResponseEntity<ClientDto> partialUpdate(@PathVariable Long id,
			@RequestBody PartialClientDto partialClientDto) {
		// api/accounts/{id}
		// Partial update accounts
		return ResponseEntity.ok(clientService.partialUpdate(id, partialClientDto));
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Void> delete(@PathVariable Long id) {
		// api/clients/{id}
		// Delete client
		try {
			clientService.deleteById(id);
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.noContent().build();
	}
}
