package br.ufrn.imd.utravel.service;

import java.util.List;
import java.util.Optional;

import br.ufrn.imd.utravel.exception.EntidadeNaoEncontradaException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import br.ufrn.imd.utravel.model.Pessoa;
import br.ufrn.imd.utravel.repository.PessoaRepository;

@Service
public class PessoaService {
	@Autowired
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

	public Pessoa save(Pessoa pessoa){
		return pessoaRepository.save(pessoa);
	}

	public Pessoa update(Integer id, Pessoa pessoa){
		Optional<Pessoa> pessoaFind = pessoaRepository.findById(id);
		if (!pessoaFind.isPresent()){
			throw new EntidadeNaoEncontradaException("Não foi possível encontrar uma pessoa com este id.");
		}

		pessoa.setId(id);

		if (!pessoaFind.get().getCpf().equals(pessoa.getCpf())){
			if ((pessoa.getCpf() == null)){
				pessoa.setCpf(pessoaFind.get().getCpf());
			}
		}

		if (!pessoaFind.get().getNome().equals(pessoa.getNome())){
			if ((pessoa.getNome() == null)){
				pessoa.setNome(pessoaFind.get().getNome());
			}
		}

		return pessoaRepository.update(pessoa);
	}

	public String delete(Integer id){
		return pessoaRepository.delete(id);
	}
}
