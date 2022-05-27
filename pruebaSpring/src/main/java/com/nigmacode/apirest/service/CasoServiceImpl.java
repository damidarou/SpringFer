package com.nigmacode.apirest.service;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nigmacode.apirest.dao.CasoUsoDAO;
import com.nigmacode.apirest.entity.Caso_uso;
@Service
public class CasoServiceImpl implements CasoService{

    @Autowired
    private CasoUsoDAO casoDAO;

    @Override
    public List<Caso_uso> findAll() {
        List<Caso_uso> listUsers = casoDAO.findAllCasoUso();
        return listUsers;
    }

    @Override
    public Caso_uso findById(int id) {
        Caso_uso caso = casoDAO.findByIdCasoUso(id);
        return caso;
    }

    @Override
    public void save(Caso_uso caso) {
        casoDAO.saveCasoUso(caso);
    }

    @Override
    public Caso_uso findByNombre(String nombre_caso_uso) {
        Caso_uso caso = casoDAO.findByNombreCasoUso(nombre_caso_uso);
        return caso;
    }

    @Override
    public void deleteById(int id) {
        casoDAO.deletedByIdCasoUso(id);
    }

    @Override
    public List<Caso_uso> findByJSON(Caso_uso user) {
        List<Caso_uso> ListCaso=casoDAO.findByJSONCasoUso(user);
        return ListCaso;
    }
}
