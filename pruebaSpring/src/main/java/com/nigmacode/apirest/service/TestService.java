package com.nigmacode.apirest.service;

import java.util.List;
import java.util.Optional;

import com.nigmacode.apirest.entity.Test;

public interface TestService {

    public List<Test> findAll();

    public Optional<Test> findById(int cod_test);

    public void save(Test test);

    public void deleteById(int cod_test);

   public Test findByNombre(String nombre);

    public List<Test> findByParameters(Test test);
}

