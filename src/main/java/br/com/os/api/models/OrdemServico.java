package br.com.os.api.models;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;

import br.com.os.api.models.enums.StatusOS;
import lombok.Data;

@Data
@Entity
@Table (name = "ORDEM_SERVICOS")
public class OrdemServico {

	@Id @GeneratedValue (strategy = GenerationType.IDENTITY)
	private Long id;
	
	@DateTimeFormat(pattern = "dd/MM/yyyy HH:mm")
	private LocalDateTime dataCadastro = LocalDateTime.now();
	
	@ManyToOne(fetch = FetchType.LAZY)
	private Cliente cliente;
	
	@Enumerated(EnumType.STRING)
	private StatusOS status;
	
	@Column(length = 1000)
	private String observacoes;

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "ordemServico")
	private List<OrdemServicoItem> items = new ArrayList<>(); 

}
