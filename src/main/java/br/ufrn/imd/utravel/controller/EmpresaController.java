package br.ufrn.imd.utravel.controller;

import br.ufrn.imd.utravel.model.Empresa;
import br.ufrn.imd.utravel.model.Pessoa;
import br.ufrn.imd.utravel.service.EmpresaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("empresas")
public class EmpresaController implements GenericController<Empresa> {
    private final EmpresaService empresaService;

    @Autowired
    public EmpresaController(EmpresaService empresaService) {
        this.empresaService = empresaService;
    }

    @Override
    public ResponseEntity<List<Empresa>> findAll() {
        return ResponseEntity.ok(empresaService.findAll());
    }

    @Override
    public ResponseEntity<Empresa> findById(Integer id) {
        Optional<Empresa> empresa = empresaService.findById(id);

        if (!empresa.isPresent()) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(empresa.get());
    }

    @Override
    public ResponseEntity<Empresa> save(Empresa empresa) {
        return ResponseEntity.ok(empresaService.save(empresa));
    }

    @Override
    public ResponseEntity<Empresa> update(Integer id, Empresa empresa) {
        return ResponseEntity.ok(empresaService.update(id, empresa));
    }

    @Override
    public ResponseEntity<String> delete(Integer id) {
        return ResponseEntity.ok(empresaService.delete(id));
    }
}
