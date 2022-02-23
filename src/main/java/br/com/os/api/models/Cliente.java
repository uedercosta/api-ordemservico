package br.com.os.api.models;

import java.time.LocalDate;
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
import org.springframework.format.annotation.DateTimeFormat.ISO;

import lombok.Data;

@Data
@Entity
@Table (name = "CLIENTES")
public class Cliente {
	
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(nullable = false, length = 100)
	private String nome;
	
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate dataNascimento;
	
	@DateTimeFormat(pattern = "dd/MM/yyyy HH:mm", iso = ISO.DATE)
	private LocalDateTime dataCadastro = LocalDateTime.now();

	@DateTimeFormat(pattern = "dd/MM/yyyy HH:mm", iso = ISO.DATE)
	private LocalDateTime dataAtualizacao;
	
	@Column(length = 11, nullable = false)
	private String cpf;
	
	@Column(length = 1000)
	private String observacoes;
	
	private boolean ativo;
	
	@PreUpdate
	private void update() {
		this.nome = nome.toUpperCase();
		this.observacoes = observacoes.toUpperCase();
	}

	@PrePersist
	private void post() {
		update();
	}

}
