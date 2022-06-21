package com.nigmacode.apirest.service;

import java.util.List;
import java.util.Optional;

import com.nigmacode.apirest.repository.CasoUsoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import com.nigmacode.apirest.entity.CasoUso;

@Service
public class CasoServiceImpl implements CasoService{

    @Autowired
    private CasoUsoRepository casoUsoRepository;

    @Override
    public List<CasoUso> findAll() {
        List<CasoUso> listUsers = casoUsoRepository.findAll();
        return listUsers;
    }

    @Override
    public Optional<CasoUso> findById(int id) {
        Optional<CasoUso> caso = casoUsoRepository.findById(id);
        return caso;
    }

    @Override
    public void save(CasoUso caso) {
        casoUsoRepository.save(caso);
    }

    @Override
    public List<CasoUso> findByNombre(String nombre) {
        List<CasoUso> caso = casoUsoRepository.findByNombre(nombre);
        return caso;
    }

    @Override
    public void deleteById(int id) {
        casoUsoRepository.deleteById(id);
    }

    @Override
    public List<CasoUso> findByExample(CasoUso casoUso) {
        Example<CasoUso> casoUsoExample= Example.of(casoUso);
        List<CasoUso> casoUsos = casoUsoRepository.findAll(casoUsoExample);
        return casoUsos;
    }
}
