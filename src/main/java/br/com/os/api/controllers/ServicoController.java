package br.com.os.api.controllers;

import java.net.URI;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
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

import br.com.os.api.dtos.ServicoDTO;
import br.com.os.api.exceptions.ResourceNotFoundException;
import br.com.os.api.models.Servico;
import br.com.os.api.repositories.ServicoRepository;

@RequestMapping("api/servicos")
@RestController
public class ServicoController {
	
	@Autowired
	private ServicoRepository servicoRepository;
	
	@GetMapping
	public ResponseEntity<List<ServicoDTO>> findAll(){
		List<ServicoDTO> list = servicoRepository.findAll(Sort.by("descricao"))
									.stream()
									.map(sv -> ServicoDTO.toDTO(sv))
									.collect(Collectors.toList());
		
		return list.isEmpty() ? ResponseEntity.noContent().build()
							  : ResponseEntity.ok(list);
	}
	
	@GetMapping("{id}")
	public ResponseEntity<ServicoDTO> findById(@PathVariable("id") Long id) {
		Servico servico = servicoRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException(String.valueOf(id)));
		return ResponseEntity.ok(ServicoDTO.toDTO(servico));
	}
	
	@PostMapping
	public ResponseEntity<ServicoDTO> post(@Valid @RequestBody ServicoDTO dto){
		Servico newServico = servicoRepository.save(dto.toModel());
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("{id}").buildAndExpand(newServico.getId()).toUri();
		return ResponseEntity.created(uri).body(ServicoDTO.toDTO(newServico));
	}
	
	@DeleteMapping("{id}")
	public ResponseEntity<ServicoDTO> delete(@PathVariable("id") Long id) {
		Servico servico = servicoRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException(String.valueOf(id)));
		servicoRepository.delete(servico);
		return ResponseEntity.noContent().build();
	}
	
	@PutMapping("{id}")
	public ResponseEntity<ServicoDTO> update(@PathVariable("id") Long id, @RequestBody @Valid ServicoDTO obj) {
		Servico newObj = servicoRepository.save(updateData(id, obj));
		return ResponseEntity.ok(ServicoDTO.toDTO(newObj));
	}

	private Servico updateData(Long id, ServicoDTO obj) {
		Servico servico = servicoRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException(String.valueOf(id)));
		servico.setDescricao(obj.getDescricao().toUpperCase());
		servico.setDataAtualizacao(LocalDateTime.now());
		servico.setValor(obj.getValor());
		servico.setAtivo(obj.isAtivo());
		servico.setDataCadastro(servico.getDataCadastro());		
		return servico;
	}

}
