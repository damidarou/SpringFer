package com.nigmacode.apirest.service;

import java.util.List;

import com.nigmacode.apirest.entity.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nigmacode.apirest.dao.EjecutarDAO;
import com.nigmacode.apirest.entity.Ejecutar;

@Service
public class EjecutarServiceImpl implements EjecutarService{

    @Autowired
    EjecutarDAO EjecutarDAO;


    @Override
    public List<Ejecutar> findAll() {
        List<Ejecutar> listUsers= EjecutarDAO.findAll();
        return listUsers;
    }

    @Override
    public Ejecutar findById(int cod_ejecuta) {
        Ejecutar ejecutar = EjecutarDAO.findById(cod_ejecuta);
        return ejecutar;
    }

    @Override
    public void save(Ejecutar ejecutar) {
        EjecutarDAO.save(ejecutar);
    }

    @Override
    public void deleteById(int cod_ejecuta) {
        EjecutarDAO.deleteById(cod_ejecuta);
    }

    @Override
    public List<Ejecutar> findByParameters(Ejecutar ejecutar) {
        List<Ejecutar> listUsers = EjecutarDAO.findByParameters(ejecutar);
        return listUsers;
    }
}
