package com.devsu.hackerearth.backend.client.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.devsu.hackerearth.backend.client.model.Client;
import com.devsu.hackerearth.backend.client.model.dto.ClientDto;
import com.devsu.hackerearth.backend.client.model.dto.PartialClientDto;
import com.devsu.hackerearth.backend.client.repository.ClientRepository;

@Service
public class ClientServiceImpl implements ClientService {

	@Autowired
	private ClientRepository clientRepository;

	@Override
	public List<ClientDto> getAll() {
		// Get all clients
		return clientRepository.findAll()
				.stream()
				.map(c -> new ClientDto(
						c.getId(),
						c.getDni(),
						c.getName(),
						c.getPassword(),
						c.getGender(),
						c.getAge(),
						c.getAddress(),
						c.getPhone(),
						c.isActive()))
				.collect(Collectors.toList());
	}

	@Override
	public ClientDto getById(Long id) {
		// Get clients by id
		ClientDto clientDto = null;
		Client c = clientRepository.findById(id).orElseGet(null);
		if (c != null) {
			clientDto = new ClientDto(
					c.getId(),
					c.getDni(),
					c.getName(),
					c.getPassword(),
					c.getGender(),
					c.getAge(),
					c.getAddress(),
					c.getPhone(),
					c.isActive());

		}
		return clientDto;
	}

	@Override
	@Transactional
	public ClientDto create(ClientDto clientDto) {
		// Create client
		if (clientDto != null && clientDto.getId() == 0) {
			Client c = new Client();
			c.setId(clientDto.getId());
			c.setActive(clientDto.isActive());
			c.setAddress(clientDto.getAddress());
			c.setAge(clientDto.getAge());
			c.setDni(clientDto.getDni());
			c.setPhone(clientDto.getPhone());
			c.setGender(clientDto.getGender());
			c.setName(clientDto.getName());
			c.setPassword(clientDto.getPassword());
			clientRepository.save(c);
			clientDto.setId(c.getId());
		}
		return clientDto;
	}

	@Override
	public ClientDto update(ClientDto clientDto) {
		// Update client
		if (clientDto != null && clientDto.getId() > 0) {
			Client c = new Client();
			c.setId(clientDto.getId());
			c.setActive(clientDto.isActive());
			c.setAddress(clientDto.getAddress());
			c.setAge(clientDto.getAge());
			c.setDni(clientDto.getDni());
			c.setPhone(clientDto.getPhone());
			c.setGender(clientDto.getGender());
			c.setName(clientDto.getName());
			c.setPassword(clientDto.getPassword());
			clientRepository.save(c);
			clientDto.setId(c.getId());
		}
		return clientDto;
	}

	@Override
	public ClientDto partialUpdate(Long id, PartialClientDto partialClientDto) {
		// Partial update account
		ClientDto clientDto = null;
		Client c = clientRepository.findById(id).orElseGet(null);
		if (c != null) {
			c.setActive(partialClientDto.isActive());
			clientRepository.save(c);
			clientDto = new ClientDto(
					c.getId(),
					c.getDni(),
					c.getName(),
					c.getPassword(),
					c.getGender(),
					c.getAge(),
					c.getAddress(),
					c.getPhone(),
					c.isActive());
		}

		return clientDto;
	}

	@Override
	public void deleteById(Long id) {
		// Delete client
		clientRepository.deleteById(id);

	}
}
