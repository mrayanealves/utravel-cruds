package br.ufrn.imd.utravel.service;

import br.ufrn.imd.utravel.model.Empresa;
import br.ufrn.imd.utravel.repository.EmpresaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmpresaService implements GenericService<Empresa> {
    private final EmpresaRepository empresaRepository;

    @Autowired
    public EmpresaService(EmpresaRepository empresaRepository) {
        this.empresaRepository = empresaRepository;
    }

    @Override
    public List<Empresa> findAll() {
        return empresaRepository.findAll();
    }

    @Override
    public ResponseEntity<Empresa> findById(Integer id) {
        return ResponseEntity.ok(empresaRepository.findById(id).get());
    }

    @Override
    public Empresa save(Empresa empresa) {
        return empresaRepository.save(empresa);
    }

    @Override
    public Empresa update(Integer id, Empresa empresa) {
        return empresaRepository.update(empresa);
    }

    @Override
    public String delete(Integer id) {
        empresaRepository.delete(id);
        return "Sucesso";
    }
}
