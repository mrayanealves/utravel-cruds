package br.ufrn.imd.utravel.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import br.ufrn.imd.utravel.model.Pessoa;
import br.ufrn.imd.utravel.repository.PessoaRepository;

@Service
public class PessoaService {
	private final PessoaRepository pessoaRepository;
	
	@Autowired
	private PessoaService(PessoaRepository pessoaRepository) {
		this.pessoaRepository = pessoaRepository;
	}
	
	public List<Pessoa> findAll(){
		return pessoaRepository.findAll();
	}

	public ResponseEntity<Pessoa> findById(Integer id){
		Optional<Pessoa> pessoa = pessoaRepository.findById(id);

		if (!pessoa.isPresent()){
			return ResponseEntity.notFound().build();
		}

		return ResponseEntity.ok(pessoa.get());
	}
}
