package com.example.mv.resource;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.example.mv.event.RecursoCriadoEvento;
import com.example.mv.model.Lanche;
import com.example.mv.model.Usuario;
import com.example.mv.repository.LancheRepository;

@RestController
@RequestMapping("/lanches")
public class LancheResource {

	@Autowired
	private LancheRepository lancheRepository;

	@Autowired
	private ApplicationEventPublisher publisher;

	@GetMapping("/listar")
	public List<Lanche> listar() {
		return lancheRepository.findAll();
	}

	@PostMapping("/adicionar")
	public ResponseEntity<Lanche> criar(@Valid @RequestBody Lanche lanche, HttpServletResponse response) {
		Lanche lancheSalvo = lancheRepository.save(lanche);
		publisher.publishEvent(new RecursoCriadoEvento(this, response, lancheSalvo.getId()));
		return ResponseEntity.status(HttpStatus.CREATED).body(lancheSalvo);
	}

	@GetMapping("/{id}")
	public Optional<Lanche> buscarPeloID(@PathVariable Long codigo) {
		return lancheRepository.findById(codigo);
	}

	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deletar(@PathVariable Long id) {
		lancheRepository.deleteById(id);
	}
}
