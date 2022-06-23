package com.nigmacode.apirest.service;

import java.util.List;
import java.util.Optional;

import com.nigmacode.apirest.entity.Test;
import com.nigmacode.apirest.repository.EjecutarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import com.nigmacode.apirest.dao.EjecutarDAO;
import com.nigmacode.apirest.entity.Ejecutar;

@Service
public class EjecutarServiceImpl implements EjecutarService{

    @Autowired
    EjecutarDAO EjecutarDAO;
    @Autowired
    EjecutarRepository ejecutarRepository;


    @Override
    public List<Ejecutar> findAll() {

        return ejecutarRepository.findAll();
    }

    @Override
    public Optional<Ejecutar> findById(int cod_ejecuta) {

        return ejecutarRepository.findById(cod_ejecuta);
    }

    @Override
    public void save(Ejecutar ejecutar) {
        ejecutarRepository.save(ejecutar);
    }

    @Override
    public void deleteById(int cod_ejecuta) {
        ejecutarRepository.deleteById(cod_ejecuta);
    }

    @Override
    public List<Ejecutar> findByExample(Ejecutar ejecutar) {
        Example<Ejecutar> ejecutarExample= Example.of(ejecutar);
        List<Ejecutar> ejecutarList = ejecutarRepository.findAll(ejecutarExample);
        return ejecutarList;
    }
}
