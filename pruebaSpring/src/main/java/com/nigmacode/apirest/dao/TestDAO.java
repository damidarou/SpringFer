package com.nigmacode.apirest.dao;

import java.util.List;

import com.nigmacode.apirest.entity.Test;

public interface TestDAO {

    public List<Test> findAll();

    public Test findById(int cod_test);

    public void save(Test test);

    public List<Test> findByParameters(Test test);

    public void deleteById(int cod_test);

    public Test findByName(String nombre);

}
