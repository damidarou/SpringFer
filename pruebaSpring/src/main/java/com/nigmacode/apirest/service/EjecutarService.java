package com.nigmacode.apirest.service;

import java.util.List;
import java.util.Optional;

import com.nigmacode.apirest.entity.Ejecutar;

public interface EjecutarService {
    public List<Ejecutar> findAll();

    public Optional<Ejecutar> findById(int cod_ejecuta);

    public void save(Ejecutar ejecutar);

    public void deleteById(int cod_ejecuta);

    public List<Ejecutar> findByExample(Ejecutar ejecutar);
}
