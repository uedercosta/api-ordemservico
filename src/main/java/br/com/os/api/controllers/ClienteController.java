package br.com.os.api.controllers;

import java.net.URI;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.com.os.api.dtos.ClienteDTO;
import br.com.os.api.exceptions.ResourceNotFoundException;
import br.com.os.api.models.Cliente;
import br.com.os.api.repositories.ClienteRepository;

@RestController
@RequestMapping("api/clientes")
public class ClienteController {
	
	@Autowired
	private ClienteRepository clienteRepository;

	@GetMapping
	public ResponseEntity<List<ClienteDTO>> findAll(){
		List<ClienteDTO> list = clienteRepository.findAll()
									.stream().map(cl -> ClienteDTO.toDTO(cl))
									.collect(Collectors.toList());
		return list.isEmpty() ? ResponseEntity.noContent().build()
				              : ResponseEntity.ok(list);
	}
	
	@GetMapping("{id}")
	public ResponseEntity<ClienteDTO> findById(@PathVariable ("id") Long id){
		Cliente cliente = clienteRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException(id));
		return ResponseEntity.ok(ClienteDTO.toDTO(cliente));
	}
	
	@DeleteMapping("{id}")
	public ResponseEntity<ClienteDTO> delete(@PathVariable ("id") Long id){
		Cliente cliente = clienteRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException(id));
		clienteRepository.delete(cliente);
		return ResponseEntity.noContent().build();
	}
	
	@PostMapping
	public ResponseEntity<ClienteDTO> post(@Valid @RequestBody ClienteDTO obj){
		Cliente newObj = clienteRepository.save(obj.toModel());
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
					.path("{id}").buildAndExpand(newObj.getId()).toUri();
		return ResponseEntity.created(uri).body(ClienteDTO.toDTO(newObj));
	}

	@PutMapping("{id}")
	public ResponseEntity<ClienteDTO> update(@PathVariable("id") Long id, @Valid @RequestBody ClienteDTO obj){
		Cliente newObj = clienteRepository.save(updateData(id, obj));
		return ResponseEntity.ok(ClienteDTO.toDTO(newObj));
	}

	private Cliente updateData(Long id, ClienteDTO obj) {
		Cliente cliente = clienteRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException(id));
		cliente.setAtivo(obj.isAtivo());
		cliente.setNome(obj.getNome().toUpperCase());
		cliente.setCpf(obj.getCpf());
		cliente.setDataAtualizacao(LocalDateTime.now());
		cliente.setDataCadastro(cliente.getDataCadastro());
		cliente.setDataNascimento(obj.getDataNascimento());
		cliente.setObservacoes(obj.getObservacoes().toUpperCase());
		return cliente;
	}
}
