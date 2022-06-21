package com.nigmacode.apirest.service;

import java.util.List;
import java.util.Optional;

import com.nigmacode.apirest.repository.ProyectoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import com.nigmacode.apirest.entity.Proyecto;

import javax.transaction.Transactional;

//Proyecto pre dynamic query
//Proyecto post dynamic query

@Service

public class ProyectoServiceImpl implements ProyectoService{
    @Autowired
    private ProyectoRepository proyectoRepository;

    @Override
    public List<Proyecto> findAll(){
        List<Proyecto> listUsers = proyectoRepository.findAll();
        return listUsers;
    }

    @Override
    public Optional<Proyecto> findById(int id) {
        Optional<Proyecto> proyecto = proyectoRepository.findById(id);
        return proyecto;
    }

    @Override
    public void save(Proyecto proyecto) {
                proyectoRepository.save(proyecto);
    }

    @Override
    public void deleteById(int id) {
        proyectoRepository.deleteById(id);
    }

    @Override
    public List<Proyecto> findByNombre(String nombre) {
        List<Proyecto> proyectos = proyectoRepository.findByNombre(nombre);
        return proyectos;
    }

    @Override
    public List<Proyecto> findByProyecto(Proyecto proyecto) {
        proyecto.setVersion_proyecto(1.0);
        Example<Proyecto> proyectoExample= Example.of(proyecto);
        List<Proyecto> proyectos = proyectoRepository.findAll(proyectoExample);
        return proyectos;
    }
}
