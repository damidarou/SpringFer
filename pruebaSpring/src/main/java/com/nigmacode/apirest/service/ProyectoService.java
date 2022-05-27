package com.nigmacode.apirest.service;
import java.util.List;

import com.nigmacode.apirest.entity.Proyecto;
public interface ProyectoService {
    public List<Proyecto> findAll();
    public Proyecto findById(int id);
    public void save (Proyecto user);
    public void deleteById(int id);

    public Proyecto getByNombre(String nombre_proyecto);

    public List<Proyecto> findByJSON(Proyecto user);
}
