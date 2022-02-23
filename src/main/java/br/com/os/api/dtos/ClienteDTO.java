package br.com.os.api.dtos;

import java.time.LocalDate;

import javax.validation.constraints.NotBlank;

import org.hibernate.validator.constraints.br.CPF;
import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

import br.com.os.api.models.Cliente;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ClienteDTO {
	
	private Long id;
	
	@NotBlank
	private String nome;
	
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	@JsonFormat(pattern = "dd/MM/yyyy")
	private LocalDate dataNascimento;
	
	@CPF
	@NotBlank
	private String cpf;
	
	private boolean ativo;
	
	private String observacoes;
	
	
	public Cliente toModel() {
		Cliente cliente = new Cliente();
		cliente.setId(this.id);
		cliente.setCpf(this.cpf);
		cliente.setAtivo(this.ativo);
		cliente.setDataNascimento(this.dataNascimento);
		cliente.setNome(this.nome.toUpperCase());
		cliente.setObservacoes(this.observacoes.toUpperCase());
		return cliente;
		
	}
	
	public static ClienteDTO toDTO(Cliente cli) {
		return ClienteDTO.builder()
						.ativo(cli.isAtivo())
						.cpf(cli.getCpf())
						.dataNascimento(cli.getDataNascimento())
						.id(cli.getId())
						.nome(cli.getNome().toUpperCase())
						.observacoes(cli.getObservacoes()).build();
	}

}
