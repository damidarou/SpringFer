package com.nigmacode.apirest.service;

import com.nigmacode.apirest.entity.Perfil;
import com.nigmacode.apirest.repository.PerfilRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PerfilServiceImpl implements PerfilService{
    @Autowired
    private PerfilRepository perfilRepository;

    public Optional<Perfil> findById(int id) {
        return perfilRepository.findById(id);
    }

    @Override
    public void save(Perfil perfil) {
        perfilRepository.save(perfil);
    }

    @Override
    public Optional<Perfil> findById(Integer id) {
        return perfilRepository.findById(id);
    }

    @Override
    public List < Perfil > findAll() {
        return perfilRepository.findAll();
    }

    @Override
    public void delete(Integer id) {
        perfilRepository.deleteById(id);
    }
}
