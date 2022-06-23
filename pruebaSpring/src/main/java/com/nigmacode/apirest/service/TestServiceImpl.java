package com.nigmacode.apirest.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import com.nigmacode.apirest.repository.TestRepository;
import com.nigmacode.apirest.entity.Test;

@Service
public class TestServiceImpl implements TestService{

    @Autowired
    private TestRepository testRepository;

    @Override
    public List<Test> findAll() {
        List<Test> listUsers= testRepository.findAll();
        return listUsers;
    }

    @Override
    public Optional<Test> findById(int cod_test) {
        Optional<Test> test = testRepository.findById(cod_test);
        return test;
    }

    @Override
    public Test findByNombre(String nombre) {
        Test test = testRepository.findByNombre(nombre);
        return test;
    }

    @Override
    public List<Test> findByExample(Test test) {
        Example<Test> testExample= Example.of(test);
        List<Test> tests = testRepository.findAll(testExample);
        return tests;
    }

    @Override
    public void save(Test test) {
        testRepository.save(test);
    }

    @Override

    public void deleteById(int cod_test) {
        testRepository.deleteById(cod_test);
    }


}
