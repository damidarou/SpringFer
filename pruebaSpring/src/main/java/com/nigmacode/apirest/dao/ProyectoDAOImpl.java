package com.nigmacode.apirest.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

import com.nigmacode.apirest.entity.Caso_uso;
import com.nigmacode.apirest.entity.Test;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.nigmacode.apirest.entity.Proyecto;

@Repository

public class ProyectoDAOImpl implements ProyectoDAO{
    @Autowired
    private EntityManager entityManager;


    @Override
    public List<Proyecto> findAll() {
        Session currentSession = entityManager.unwrap(Session.class);
        Query<Proyecto> theQuery = currentSession.createQuery("from Proyecto", Proyecto.class);
        List<Proyecto> proyecto = theQuery.getResultList();
        return proyecto;

    }

    @Override
    public Proyecto findById(int id) {
        Session currentSession = entityManager.unwrap(Session.class);
        Proyecto proyecto = currentSession.get(Proyecto.class, id);
        return proyecto;
    }

    @Override
    @Transactional
    public void save(Proyecto proyecto) {
        Session currentSession = entityManager.unwrap(Session.class);
        currentSession.saveOrUpdate(proyecto);

    }

    @Override
    @Transactional
    public void deleteById(int id) {
        Session currentSession = entityManager.unwrap(Session.class);
        Query<Proyecto> theQuery = currentSession.createQuery("delete from Proyecto where id=:cod_proyecto");
        theQuery.setParameter("cod_proyecto", id);
        theQuery.executeUpdate();

    }

    @Override
    public Proyecto getByNombre(String nombre_proyecto) {
        Session currentSession = entityManager.unwrap(Session.class);
        Query<Proyecto> theQuery = currentSession.createQuery("from Proyecto where nombre_proyecto like :cod_proyecto");
        theQuery.setParameter("cod_proyecto", nombre_proyecto);
        Proyecto proyecto = theQuery.getSingleResult();
        return proyecto;
    }

    @Override
    public List<Proyecto> findByJSON(Proyecto proyecto) {
        Session currentSession = entityManager.unwrap(Session.class);
        String json = "from Proyecto where ";
        if (proyecto.getCod_proyecto()==0 & proyecto.getNombre_proyecto()==null & proyecto.getCod_usuario()==0 & proyecto.getVersion_proyecto()==0) {
            throw new RuntimeException("Project not found");
        }
        if (proyecto.getCod_proyecto()!=0 ) {
            json = json + "cod_proyecto = " + proyecto.getCod_proyecto();
        }
        if (proyecto.getNombre_proyecto()!=null) {
            if (json.contentEquals("from Proyecto where " )){
                json = json + "nombre_proyecto like '"+proyecto.getNombre_proyecto()+"'";
            }
            else
                json = json + "and nombre_proyecto like '"+proyecto.getNombre_proyecto()+"'";
        }

        if (proyecto.getCod_usuario()!=0) {
            if (json.contentEquals("from Proyecto where ")){
                json = json + "cod_usuario = " + proyecto.getCod_usuario();
            }
            else json = json + "and cod_usuario = " +proyecto.getCod_usuario();
        }

        if (proyecto.getVersion_proyecto()!=0) {
            if (json.contentEquals("from Proyecto where ")){
                json = json + "version_proyecto = " + proyecto.getVersion_proyecto();
            }
            else json = json + " and version_proyecto = " +proyecto.getVersion_proyecto();
        }

        Query<Proyecto> theQuery = currentSession.createQuery(json, Proyecto.class);
        List<Proyecto> proyectos = theQuery.getResultList();
        return proyectos;
    }
}
