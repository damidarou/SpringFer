package com.nigmacode.apirest.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nigmacode.apirest.dao.ProyectoDAO;
import com.nigmacode.apirest.entity.Proyecto;

//Proyecto pre dynamic query
//Proyecto post dynamic query

@Service

public class ProyectoServiceImpl implements ProyectoService{
    @Autowired
    private ProyectoDAO userDAO;

    @Override
    public List<Proyecto> findAll(){
        List<Proyecto> listUsers = userDAO.findAll();
        return listUsers;
    }

    @Override
    public Proyecto findById(int id) {
        Proyecto proyecto = userDAO.findById(id);
        return proyecto;
    }

    @Override
    public void save(Proyecto proyecto) {
        userDAO.save(proyecto);
    }

    @Override
    public void deleteById(int id) {
        userDAO.deleteById(id);
    }

    @Override
    public Proyecto getByNombre(String nombre_proyecto) {
        Proyecto getByName = userDAO.getByNombre(nombre_proyecto);
        return  getByName;
    };

    @Override
    public List<Proyecto> findByJSON(Proyecto proyecto){
        List<Proyecto> ListProyects = userDAO.findByJSON(proyecto);
        return ListProyects;
    }
}
