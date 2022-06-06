package com.nigmacode.apirest.dao;
import java.util.List;
import com.nigmacode.apirest.entity.Ejecutar;

public interface EjecutarDAO {

    public List<Ejecutar> findAll();

    public Ejecutar findById(int cod_ejecuta);

    public void save(Ejecutar ejecutar);

    public List<Ejecutar> findByParameters(Ejecutar ejecutar);

    public void deleteById(int cod_ejecuta);

}
