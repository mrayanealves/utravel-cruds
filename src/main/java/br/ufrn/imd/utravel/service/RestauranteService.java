package br.ufrn.imd.utravel.service;

import br.ufrn.imd.utravel.exception.EntidadeNaoEncontradaException;
import br.ufrn.imd.utravel.model.Restaurante;
import br.ufrn.imd.utravel.repository.RestauranteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RestauranteService implements GenericService<Restaurante>{
	private final RestauranteRepository restauranteRepository;

	@Autowired
	private RestauranteService(RestauranteRepository restauranteRepository) {
		this.restauranteRepository = restauranteRepository;
	}

	@Override
	public List<Restaurante> findAll(){
		return restauranteRepository.findAll();
	}

	@Override
	public Optional<Restaurante> findById(Integer id) {
		return restauranteRepository.findById(id);
	}

	@Override
	public Restaurante save(Restaurante restaurante){
		return restauranteRepository.save(restaurante);
	}

	@Override
	public Restaurante update(Integer id, Restaurante restaurante){
		Optional<Restaurante> restauranteFind = restauranteRepository.findById(id);
		if (!restauranteFind.isPresent()){
			throw new EntidadeNaoEncontradaException("Não foi possível encontrar uma restaurante com este id.");
		}

		return restauranteRepository.update(restaurante);
	}

	@Override
	public String delete(Integer id){
		return restauranteRepository.delete(id);
	}
}
