package br.ufrn.imd.utravel.controller;

import br.ufrn.imd.utravel.model.Restaurante;
import br.ufrn.imd.utravel.service.RestauranteService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("restaurantes")
@Api(value = "Gerenciar restaurantes")
public class RestauranteController implements GenericController<Restaurante>{
	private final RestauranteService restauranteService;

	@Autowired
	public RestauranteController(RestauranteService restauranteService) {
		this.restauranteService = restauranteService;
	}

	@Override
	public ResponseEntity<List<Restaurante>> findAll(){
		return ResponseEntity.ok(restauranteService.findAll());
	}

    @Override
	public ResponseEntity<Restaurante> findById(@PathVariable Integer id){
		Optional<Restaurante> restaurante = restauranteService.findById(id);

		if (!restaurante.isPresent()) {
			return ResponseEntity.notFound().build();
		}
		
    	return ResponseEntity.ok(restaurante.get());
	}

    @Override
	public ResponseEntity<Restaurante> save(@RequestBody Restaurante restaurante){
		return ResponseEntity.ok(restauranteService.save(restaurante));
	}

    @Override
	public ResponseEntity<Restaurante> update(@PathVariable Integer id, @RequestBody Restaurante restaurante){
		return ResponseEntity.ok(restauranteService.update(id, restaurante));
	}

    @Override
    public ResponseEntity<String> delete(@PathVariable Integer id){
	    return ResponseEntity.ok(restauranteService.delete(id));
    }
}
