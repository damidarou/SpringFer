package com.nigmacode.apirest.dao;
import java.util.List;

import javax.persistence.EntityManager;

import com.nigmacode.apirest.entity.Test;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.nigmacode.apirest.entity.Ejecutar;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class EjecutarDAOImpl implements EjecutarDAO{
    @Autowired
    EntityManager entityManager;


    @Override
    public List<Ejecutar> findAll() {
        Session currentSession = entityManager.unwrap(Session.class);
        Query<Ejecutar> theQuery = currentSession.createQuery("select t from Ejecutar t", Ejecutar.class);
        List<Ejecutar> ejecutar = theQuery.getResultList();

        return ejecutar;
    }

    @Override
    public Ejecutar findById(int cod_ejecuta) {
        Session currentSession = entityManager.unwrap(Session.class);

        Ejecutar ejecutar = currentSession.get(Ejecutar.class, cod_ejecuta);

        return ejecutar;
    }

    @Override
    @Transactional
    public void save(Ejecutar ejecutar) {
        Session currentSession = entityManager.unwrap(Session.class);
        currentSession.saveOrUpdate(ejecutar);
    }

    @Override
    public List<Ejecutar> findByParameters(Ejecutar ejecutar) {
        Session currentSession = entityManager.unwrap(Session.class);

        String f1 = "select t from Ejecutar t where ";
        String f2 = "cod_ejecuta="+ejecutar.getCod_ejecuta();
        String f3 = "id_user="+ejecutar.getId_user();
        String f4 = "id_test="+ejecutar.getId_test();
        String f5 = "fecha_modificacion_test = '"+ejecutar.getFecha()+"'";
        String f6 = "resultado like '"+ejecutar.getResultado()+"'";

        if (ejecutar.getCod_ejecuta()==0 && ejecutar.getId_user()==0 && ejecutar.getId_test()==0 && ejecutar.getResultado()==null && ejecutar.getFecha()==null){
            f1="select t from Ejecutar t";
        }

        if(ejecutar.getCod_ejecuta()!=0){
            f1 = f1+f2;
        }
        if(ejecutar.getId_user()!=0){
            if(f1.contentEquals("select t from Ejecutar t where ")){
                f1 = f1 + f3;
            } else {
                f1 = f1 +" and "+f3;
            }
        }
        if(ejecutar.getId_test()!=0){
            if(f1.contentEquals("select t from Ejecutar t where ")){
                f1 = f1+f4;
            } else {
                f1 = f1 +" and "+f4;
            }
        }
        if(ejecutar.getResultado()!=null){
            if(f1.contentEquals("select t from Ejecutar t where ")){
                f1 = f1+f6;
            } else {
                f1 = f1 +" and "+f6;
            }
        }
        if(ejecutar.getFecha()!=null){
            if(f1.contentEquals("select t from Ejecutar t where ")){
                f1 = f1+f5;
            } else {
                f1 = f1 +" and "+f5;
            }
        }
        Query<Ejecutar> theQuery = currentSession.createQuery(f1);
        List<Ejecutar> ejecuta = theQuery.getResultList();
        return ejecuta;
    }

    @Override
    @Transactional
    public void deleteById(int cod_ejecuta) {
        Session currentSession = entityManager.unwrap(Session.class);
        Query<Ejecutar> theQuery = currentSession.createQuery("delete from Ejecutar where cod_ejecuta=:idEjecuta") ;
        theQuery.setParameter("idEjecuta", cod_ejecuta);
        theQuery.executeUpdate();
    }
}
