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
public class PessoaController implements GenericController<Pessoa>{
	@Autowired
	private PessoaService pessoaService;

    @Override
	public ResponseEntity<List<Pessoa>> findAll(){
		return ResponseEntity.ok(pessoaService.findAll());
	}

    @Override
	public ResponseEntity<Pessoa> findById(@PathVariable Integer id){
		Optional<Pessoa> pessoa = pessoaService.findById(id);

		if (!pessoa.isPresent()) {
			return ResponseEntity.notFound().build();
		}
		
    	return ResponseEntity.ok(pessoa.get());
	}

    @Override
	public ResponseEntity<Pessoa> save(@RequestBody Pessoa pessoa){
		return ResponseEntity.ok(pessoaService.save(pessoa));
	}

    @Override
	public ResponseEntity<Pessoa> update(@PathVariable Integer id, @RequestBody Pessoa pessoa){
		return ResponseEntity.ok(pessoaService.update(id, pessoa));
	}

    @Override
    public ResponseEntity<String> delete(@PathVariable Integer id){
	    return ResponseEntity.ok(pessoaService.delete(id));
    }
}
