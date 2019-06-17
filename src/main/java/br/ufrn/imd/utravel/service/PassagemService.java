package br.ufrn.imd.utravel.service;

import br.ufrn.imd.utravel.exception.EntidadeNaoEncontradaException;
import br.ufrn.imd.utravel.model.Passagem;
import br.ufrn.imd.utravel.repository.PassagemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PassagemService implements GenericService<Passagem>{
	private final PassagemRepository passagemRepository;

	@Autowired
	private PassagemService(PassagemRepository passagemRepository) {
		this.passagemRepository = passagemRepository;
	}

	@Override
	public List<Passagem> findAll(){
		return passagemRepository.findAll();
	}

	@Override
	public Optional<Passagem> findById(Integer id) {
		return passagemRepository.findById(id);
	}

	@Override
	public Passagem save(Passagem passagem){
		return passagemRepository.save(passagem);
	}

	@Override
	public Passagem update(Integer id, Passagem passagem){
		Optional<Passagem> passagemFind = passagemRepository.findById(id);
		if (!passagemFind.isPresent()){
			throw new EntidadeNaoEncontradaException("Não foi possível encontrar uma passagem com este id.");
		}

		return passagemRepository.update(passagem);
	}

	@Override
	public String delete(Integer id){
		return passagemRepository.delete(id);
	}
}
