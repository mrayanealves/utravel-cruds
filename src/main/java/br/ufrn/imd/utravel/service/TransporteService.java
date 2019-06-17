package br.ufrn.imd.utravel.service;

import br.ufrn.imd.utravel.exception.EntidadeNaoEncontradaException;
import br.ufrn.imd.utravel.model.Transporte;
import br.ufrn.imd.utravel.repository.TransporteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TransporteService implements GenericService<Transporte>{
	private final TransporteRepository transporteRepository;

	@Autowired
	private TransporteService(TransporteRepository transporteRepository) {
		this.transporteRepository = transporteRepository;
	}

	@Override
	public List<Transporte> findAll(){
		return transporteRepository.findAll();
	}

	@Override
	public Optional<Transporte> findById(Integer id) {
		return transporteRepository.findById(id);
	}

	@Override
	public Transporte save(Transporte transporte){
		return transporteRepository.save(transporte);
	}

	@Override
	public Transporte update(Integer id, Transporte transporte){
		Optional<Transporte> transporteFind = transporteRepository.findById(id);
		if (!transporteFind.isPresent()){
			throw new EntidadeNaoEncontradaException("Não foi possível encontrar uma transporte com este id.");
		}

		return transporteRepository.update(transporte);
	}

	@Override
	public String delete(Integer id){
		return transporteRepository.delete(id);
	}
}
