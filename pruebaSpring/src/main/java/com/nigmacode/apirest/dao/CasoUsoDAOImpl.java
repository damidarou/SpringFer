package com.nigmacode.apirest.dao;

import com.nigmacode.apirest.entity.Caso_uso;

import java.util.List;
import javax.persistence.EntityManager;
import javax.transaction.Transactional;

import com.nigmacode.apirest.entity.Test;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class CasoUsoDAOImpl implements CasoUsoDAO{

    @Autowired
    private EntityManager entityManager;

    /*Esta función desarrolla un método que funciona como un SELECT * FROM Caso_uso para obtener todos los casos de uso*/
    @Override
    public List<Caso_uso> findAllCasoUso() {
        Session currentSession = entityManager.unwrap(Session.class);
        Query<Caso_uso>  theQuery = currentSession.createQuery("from Caso_uso", Caso_uso.class);
        List<Caso_uso> caso = theQuery.getResultList();
        return caso;
    }

    /*Esta función desarrolla un método que funciona como un SELECT * para el caso de uso cuyo código coincida con el que le pasamos*/
    @Override
    public Caso_uso findByIdCasoUso(int id) {
        Session currentSession = entityManager.unwrap(Session.class);
        Caso_uso caso = currentSession.get(Caso_uso.class, id);
        return caso;
    }

    /*Esta función desarrolla un método que funciona como un SELECT * para el caso de uso cuyo nombre coincida con el que le pasamos*/
    @Override
    public Caso_uso findByNombreCasoUso(String nombre_caso_uso) {
        Session currentSession = entityManager.unwrap(Session.class);
        Query<Caso_uso>  theQuery = currentSession.createQuery("from Caso_uso where nombre_caso_uso like :z" );
        theQuery.setParameter("z", nombre_caso_uso);
        Caso_uso caso = theQuery.getSingleResult();
        return caso;
    }

    /*Esta función sirve para guardar o actualizar los datos de un caso de uso*/
    @Override
    @Transactional
    public void saveCasoUso(Caso_uso caso_uso) {
        Session currentSession = entityManager.unwrap(Session.class);
        currentSession.saveOrUpdate(caso_uso);
    }

    /*Esta función elimina un caso de uso cuyo código le pasemos por parámetro*/
    @Override
    @Transactional
    public void deletedByIdCasoUso(int id) {
        Session currentSession = entityManager.unwrap(Session.class);
        Query<Caso_uso> theQuery = currentSession.createQuery("delete from Caso_uso where id=:cod_caso_uso");
        theQuery.setParameter("cod_caso_uso", id);
        theQuery.executeUpdate();
    }

    /*Esta función elimina un caso de uso cuyo nombre le pasemos por parámetro*/
    @Override
    @Transactional
    public void deletedByNombreCasoUso(String nombre_caso_uso) {
        Session currentSession = entityManager.unwrap(Session.class);
        Query<Caso_uso> theQuery = currentSession.createQuery("delete from Caso_uso where nombre_caso_uso like :z");
        theQuery.setParameter("z", nombre_caso_uso);
        theQuery.executeUpdate();
    }

    /*Esta funcion hace un SELECT * de todos los casos de uso cuyos valores sean como los que les pasemos por parámetros*/
    @Override
    public List<Caso_uso> findByJSONCasoUso(Caso_uso user) {
        Session currentSession = entityManager.unwrap(Session.class);
        String json = "from Caso_uso where ";
        if (user.getCod_caso_uso()==0 & user.getNombre_caso_uso()==null & user.getDescripcion()==null & user.getId_proyecto()==0 & user.getCod_usuario()==0) {
            throw new RuntimeException("caso not found");
        }
        if (user.getCod_caso_uso()!=0 ) {
            json = json + "cod_caso_uso = " + user.getCod_caso_uso();
        }
        if (user.getNombre_caso_uso()!=null) {
            if (json.contentEquals("from Caso_uso where " )){
                json = json + "nombre_caso_uso like '"+user.getNombre_caso_uso()+"'";
            }
            else
                json = json + "and nombre_caso_uso like '"+user.getNombre_caso_uso()+"'";
        }
        if (user.getDescripcion()!=null) {
            if (json.contentEquals("from Caso_uso where ")){
                json = json + "descripcion = '" + user.getDescripcion()+"'";
            }
            else json = json + "and descripcion = '" +user.getDescripcion()+"'";
        }
        if (user.getCod_usuario()!=0) {
            if (json.contentEquals("from Caso_uso where ")){
                json = json + "cod_usuario = '" + user.getCod_usuario()+"'";
            }
            else json = json + "and cod_usuario = '" +user.getCod_usuario()+"'";
        }

        if (user.getId_proyecto()!=0) {
            if (json.contentEquals("from Caso_uso where ")){
                json = json + "id_proyecto = '" + user.getId_proyecto()+"'";
            }
            else json = json + "and id_proyecto = '" +user.getId_proyecto()+"'";
        }

        Query<Caso_uso> theQuery = currentSession.createQuery(json, Caso_uso.class);
        List<Caso_uso> caso = theQuery.getResultList();
        return caso;
    }
}