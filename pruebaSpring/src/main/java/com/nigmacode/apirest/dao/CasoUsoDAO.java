package com.nigmacode.apirest.dao;

import java.util.List;

import com.nigmacode.apirest.entity.Caso_uso;

import javax.transaction.Transactional;

public interface CasoUsoDAO {

    public List<Caso_uso> findAllCasoUso();

    public Caso_uso findByIdCasoUso(int id);

    Caso_uso findByNombreCasoUso(String nombre_caso_uso);

    public void saveCasoUso(Caso_uso caso_uso);

    public void deletedByIdCasoUso(int id);

    @Transactional
    void deletedByNombreCasoUso(String nombre_caso_uso);

    public List<Caso_uso> findByJSONCasoUso(Caso_uso user);

}
