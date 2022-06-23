package com.nigmacode.apirest.service;
import java.util.List;
import java.util.Optional;

import com.nigmacode.apirest.entity.Proyecto;
public interface ProyectoService {

    public List<Proyecto> findAll();
    public Optional<Proyecto> findById(int id);
    public void save (Proyecto proyecto);
    public void deleteById(int id);
    public List<Proyecto> findByNombre(String nombre);
    public List<Proyecto> findByExample(Proyecto proyecto);
}
