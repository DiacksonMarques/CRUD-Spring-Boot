package com.crmApre.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.crmApre.model.Client;
import com.crmApre.repository.ClientRepository;


@RestController
public class ClientController {
	
	@Autowired
	private ClientRepository clientRepository;
	
	@GetMapping("/client")
	public List<Client> list() {
		return clientRepository.findAll();
	}
	
	@PostMapping("/client")
	@ResponseStatus(HttpStatus.CREATED)
	public Client add(@RequestBody Client client) {
		return clientRepository.save(client);
	}
	
	@PutMapping("/client/{id}")
	public Client update(@RequestBody Client newClient, @PathVariable Long id) {
		return clientRepository.findById(id).
				map(client -> {
					client.setNome(newClient.getNome());
					return clientRepository.save(client);
				})
				.orElseGet(() -> {
					newClient.setId(id);
					return clientRepository.save(newClient);
				});
	}
	
	@DeleteMapping("/client/{id}")
	public void delete(@PathVariable Long id) {
		clientRepository.deleteById(id);
	}
}