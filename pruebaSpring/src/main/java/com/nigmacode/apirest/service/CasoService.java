package com.nigmacode.apirest.service;

import java.util.List;
import java.util.Optional;

import com.nigmacode.apirest.entity.CasoUso;

public interface CasoService {

    public List<CasoUso> findAll();
    public Optional<CasoUso> findById(int id);
    public void save(CasoUso caso);
    public List<CasoUso> findByNombre(String nombre_caso_uso);
    public void deleteById(int id);
    public List<CasoUso> findByExample(CasoUso user);
}
