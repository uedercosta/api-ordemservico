package br.com.os.api.exceptions;

import java.time.LocalDateTime;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ResourceExceptionHandler {
	
	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<ErroApi> resourceNotFoundException(ResourceNotFoundException ex, HttpServletRequest req){
		ErroApi erro = ErroApi.builder()
			.data(LocalDateTime.now())
			.status(404)
			.message(ex.getMessage())
			.path(req.getRequestURI()).build();
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(erro);
	}

}
