package com.nigmacode.apirest.dao;

import java.util.List;
import com.nigmacode.apirest.entity.Proyecto;

public interface ProyectoDAO {
    public List<Proyecto> findAll();
    public Proyecto findById(int id);
    public void save(Proyecto proyecto);
    public void deleteById(int id);

    //public List<User> getByNombre(String nombre_proyecto);
    public Proyecto getByNombre(String nombre_proyecto);

    //List<User> getByJSON(User user);

    public List<Proyecto> findByJSON(Proyecto proyecto);
}
