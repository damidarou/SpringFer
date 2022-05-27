package com.nigmacode.apirest.service;

import java.util.List;

import com.nigmacode.apirest.entity.Caso_uso;

public interface CasoService {
    public List<Caso_uso> findAll();

    public Caso_uso findById(int id);

    public void save(Caso_uso caso);
    public Caso_uso findByNombre(String nombre_caso_uso);
    public void deleteById(int id);

    List<Caso_uso> findByJSON(Caso_uso user);
}
