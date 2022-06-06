package com.nigmacode.apirest.service;

import java.util.List;
import com.nigmacode.apirest.entity.Ejecutar;
import com.nigmacode.apirest.entity.Test;

public interface EjecutarService {
    public List<Ejecutar> findAll();

    public Ejecutar findById(int cod_ejecuta);

    public void save(Ejecutar ejecutar);

    public void deleteById(int cod_ejecuta);

    public List<Ejecutar> findByParameters(Ejecutar ejecutar);
}
