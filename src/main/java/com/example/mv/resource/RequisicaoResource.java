package com.example.mv.resource;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.*;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.example.mv.event.RecursoCriadoEvento;
import com.example.mv.model.Requisicao;
import com.example.mv.repository.RequisicaoRepository;

@RestController
@RequestMapping("/requisicoes")
public class RequisicaoResource {

	@Autowired
	private RequisicaoRepository requisicaoRepository;

	@Autowired
	private ApplicationEventPublisher publisher;

	@GetMapping("/listar")
	public List<Requisicao> listar() {
		return requisicaoRepository.findAll();
	}

	@PostMapping("/adicionar")
	public ResponseEntity<Requisicao> criar(@Valid @RequestBody Requisicao requisicao, HttpServletResponse response) {
		Requisicao requisicaoSalva = requisicaoRepository.save(requisicao);
		publisher.publishEvent(new RecursoCriadoEvento(this, response, requisicaoSalva.getId()));
		return ResponseEntity.status(HttpStatus.CREATED).body(requisicaoSalva);
	}

	@GetMapping("/{id}")
	public Optional<Requisicao> buscarPeloID(@PathVariable Long id) {
		return requisicaoRepository.findById(id);
	}

	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deletar(@PathVariable Long id) {
		requisicaoRepository.deleteById(id);
	}

}
