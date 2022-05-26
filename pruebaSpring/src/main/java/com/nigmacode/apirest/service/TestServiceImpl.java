package com.nigmacode.apirest.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nigmacode.apirest.dao.TestDAO;
import com.nigmacode.apirest.entity.Test;

@Service
public class TestServiceImpl implements TestService{

    @Autowired
    private TestDAO TestDAO;

    @Override
    public List<Test> findAll() {
        List<Test> listUsers= TestDAO.findAll();
        return listUsers;
    }

    @Override
    public Test findById(int cod_test) {
        Test test = TestDAO.findById(cod_test);
        return test;
    }

    @Override
    public Test findByName(String nombre) {
        Test test = TestDAO.findByName(nombre);
        return test;
    }

    @Override
    public List<Test> findByParameters(Test test) {
        List<Test> listUsers = TestDAO.findByParameters(test);
        return listUsers;
    }

    @Override
    public void save(Test test) {
        TestDAO.save(test);
    }

    @Override

    public void deleteById(int cod_test) {
        TestDAO.deleteById(cod_test);
    }


}
