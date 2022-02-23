package br.com.os.api.models;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.Min;

import lombok.Data;

@Data
@Entity
@Table(name="ORDEM_SERVICOS_ITEM")
public class OrdemServicoItem {
	
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@ManyToOne(fetch = FetchType.LAZY)
	private OrdemServico ordemServico;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn
	private Servico servico;
	
	@Min(value = 1)
	private Integer quantidade;
	
	@Column(precision = 18, scale = 5, nullable = false)
	private BigDecimal valorUnitario;

	@Column(precision = 18, scale = 5, nullable = false)
	private BigDecimal desconto;

	@Column(precision = 18, scale = 5, nullable = false)
	private BigDecimal valorLiquido;

}
