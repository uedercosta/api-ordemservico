package br.com.os.api.dtos;

import java.math.BigDecimal;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

import br.com.os.api.models.Servico;
import lombok.Data;

@Data
public class ServicoDTO {
	
	private Long id;
	@NotBlank
	@Length(min = 3, max = 100)
	private String descricao;
	private boolean ativo;
	@NotNull
	private BigDecimal valor;
	
	public Servico toModel() {
		Servico sv = new Servico();
		sv.setAtivo(this.ativo);
		sv.setDescricao(this.descricao.toUpperCase());
		sv.setValor(this.valor);
		sv.setId(this.id);
		return sv;
	}
	
	public static ServicoDTO toDTO(Servico sv) {
		ServicoDTO dto = new ServicoDTO();
		dto.setId(sv.getId());
		dto.setDescricao(sv.getDescricao().toUpperCase());
		dto.setValor(sv.getValor());
		dto.setAtivo(sv.isAtivo());
		return dto;
	}

}
