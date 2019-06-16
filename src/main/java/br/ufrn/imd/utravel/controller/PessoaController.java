package br.ufrn.imd.utravel.controller;

import io.swagger.annotations.Api;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
