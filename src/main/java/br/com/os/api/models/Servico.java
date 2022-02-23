package br.com.os.api.models;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

@Data
@Entity
@Table (name = "SERVICOS")
public class Servico {
	
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(length = 100, nullable = false)
	private String descricao;
	
	@Column(precision = 18, scale = 5, nullable = false)
	private BigDecimal valor;
	
	private boolean ativo = true;
	
	@DateTimeFormat(pattern = "dd/mm/yyyy HH:mm")
	private LocalDateTime dataCadastro = LocalDateTime.now();
	
	@DateTimeFormat(pattern = "dd/mm/yyyy HH:mm")
	private LocalDateTime dataAtualizacao;
	
	@PrePersist
	private void post() {
		dataAtualizacao = LocalDateTime.now();
	}
	
	@PreUpdate
	private void update() {
		post();
	}
	

}
