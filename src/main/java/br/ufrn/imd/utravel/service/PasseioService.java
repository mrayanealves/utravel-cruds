package br.ufrn.imd.utravel.service;

import br.ufrn.imd.utravel.exception.EntidadeNaoEncontradaException;
import br.ufrn.imd.utravel.model.Passeio;
import br.ufrn.imd.utravel.repository.PasseioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PasseioService implements GenericService<Passeio>{
	private final PasseioRepository passeioRepository;

	@Autowired
	private PasseioService(PasseioRepository passeioRepository) {
		this.passeioRepository = passeioRepository;
	}

	@Override
	public List<Passeio> findAll(){
		return passeioRepository.findAll();
	}

	@Override
	public Optional<Passeio> findById(Integer id) {
		return passeioRepository.findById(id);
	}

	@Override
	public Passeio save(Passeio passeio){
		return passeioRepository.save(passeio);
	}

	@Override
	public Passeio update(Integer id, Passeio passeio){
		Optional<Passeio> passeioFind = passeioRepository.findById(id);
		if (!passeioFind.isPresent()){
			throw new EntidadeNaoEncontradaException("Não foi possível encontrar uma passeio com este id.");
		}

		return passeioRepository.update(passeio);
	}

	@Override
	public String delete(Integer id){
		return passeioRepository.delete(id);
	}
}
