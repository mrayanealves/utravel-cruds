package br.ufrn.imd.utravel.service;

import java.util.List;
import java.util.Optional;

import br.ufrn.imd.utravel.exception.EntidadeNaoEncontradaException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.ufrn.imd.utravel.model.Pessoa;
import br.ufrn.imd.utravel.repository.PessoaRepository;

@Service
public class PessoaService implements GenericService<Pessoa>{
	private final PessoaRepository pessoaRepository;
	
	@Autowired
	private PessoaService(PessoaRepository pessoaRepository) {
		this.pessoaRepository = pessoaRepository;
	}

	@Override
	public List<Pessoa> findAll(){
		return pessoaRepository.findAll();
	}

	@Override
	public Optional<Pessoa> findById(Integer id) {
		return pessoaRepository.findById(id);
	}

	@Override
	public Pessoa save(Pessoa pessoa){
		return pessoaRepository.save(pessoa);
	}

	@Override
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

	@Override
	public String delete(Integer id){
		return pessoaRepository.delete(id);
	}
}
