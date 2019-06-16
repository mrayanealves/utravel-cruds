package br.ufrn.imd.utravel.controller;

import io.swagger.annotations.Api;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import br.ufrn.imd.utravel.model.Pessoa;
import br.ufrn.imd.utravel.service.PessoaService;

@RestController
@RequestMapping("pessoas")
@Api(value = "Gerenciar pessoas")
public class PessoaController {
	@Autowired
	private PessoaService pessoaService;
	
	@GetMapping
	public ResponseEntity<List<Pessoa>> findAll(){
		return ResponseEntity.ok(pessoaService.findAll());
	}

	@GetMapping("/{id}")
	public ResponseEntity<Pessoa> findById(@PathVariable Integer id){
		return pessoaService.findById(id);
	}

	@PostMapping
	public ResponseEntity<Pessoa> save(@RequestBody Pessoa pessoa){
		return ResponseEntity.ok(pessoaService.save(pessoa));
	}

	@PutMapping("/{id}")
	public ResponseEntity<Pessoa> update(@PathVariable Integer id, @RequestBody Pessoa pessoa){
		return ResponseEntity.ok(pessoaService.update(id, pessoa));
	}
}
