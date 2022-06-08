package com.nigmacode.apirest.dao;

import java.util.List;

import javax.persistence.EntityManager;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.nigmacode.apirest.entity.Test;
import org.springframework.transaction.annotation.Transactional;

@Repository

public class TestDAOImpl implements TestDAO{

    @Autowired
    private EntityManager entityManager;

    @Override

    public List<Test> findAll() {
        Session currentSession = entityManager.unwrap(Session.class);
        Query<Test> theQuery = currentSession.createQuery("select t from Test t", Test.class);
        List<Test> tests = theQuery.getResultList();

        return tests;

    }

    @Override
    public Test findById(int cod_test) {
        Session currentSession = entityManager.unwrap(Session.class);

        Test test = currentSession.get(Test.class, cod_test);

        return test;
    }

    @Override
    public Test findByName(String nombre){
        Session currentSession = entityManager.unwrap(Session.class);
        Query<Test> theQuery = currentSession.createQuery("select t from Test t where nombre like :idTest") ;

        theQuery.setParameter("idTest", nombre);
        Test test = theQuery.getSingleResult();

        return test;
    }

    @Override
    public List<Test> findByParameters (Test test) {
        Session currentSession = entityManager.unwrap(Session.class);
        String f1 = "select t from Test t where ";

        String f2 = "cod_test="+test.getCod_test();
        String f3 = "nombre like '"+test.getNombre()+"'";
        String f4 = "objetivo like '"+test.getObjetivo()+"'";
        String f5 = "estado like '"+test.getEstado()+"'";
        String f6 = "cod_caso_uso="+test.getId_caso_uso();
        String f7 = "cod_usuario="+test.getCod_usuario();

        if (test.getCod_test()==0&&test.getCod_usuario()==0&&test.getId_caso_uso()==0&&test.getNombre()==null&&test.getEstado()==null&&test.getObjetivo()==null){
            f1="select t from Test t";
        }

        if(test.getCod_test()!=0){
            f1 = f1+f2;
        }
        if(test.getNombre()!=null){
            if(f1.contentEquals("select t from Test t where ")){
                f1 = f1 + f3;
            } else {
                f1 = f1 +" and "+f3;
            }
        }
        if(test.getObjetivo()!=null){
            if(f1.contentEquals("select t from Test t where ")){
                f1 = f1+f4;
            } else {
                f1 = f1 +" and "+f4;
            }
        }
        if(test.getEstado()!=null){
            if(f1.contentEquals("select t from Test t where ")){
                f1 = f1+f5;
            } else {
                f1 = f1 +" and "+f5;
            }
        }
        if(test.getId_caso_uso()!=0){
            if(f1.contentEquals("select t from Test t where ")){
                f1 = f1+f6;
            } else {
                f1 = f1 +" and "+f6;
            }
        }
        if(test.getCod_usuario()!=0){
            if(f1.contentEquals("select t from Test t where ")){
                f1 = f1+f7;
            } else {
                f1 = f1 +" and "+f7;
            }
        }

        Query<Test> theQuery = currentSession.createQuery(f1);
        List<Test> test1 = theQuery.getResultList();

        return test1;
    }

    @Override
    @Transactional
    public void save(Test test) {
        Session currentSession = entityManager.unwrap(Session.class);

        currentSession.saveOrUpdate(test);

    }

    @Override
    @Transactional
    public void deleteById(int cod_test) {
        Session currentSession = entityManager.unwrap(Session.class);

        Query<Test> theQuery = currentSession.createQuery("delete from Test where cod_test=:idTest") ;

        theQuery.setParameter("idTest", cod_test);
        theQuery.executeUpdate();

    }

}
