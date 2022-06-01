package com.nigmacode.apirest.service;

import com.Landra.apirest.entity.Perfil;
import com.Landra.apirest.repository.PerfilRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PerfilServiceImpl implements PerfilService{
    @Autowired
    private PerfilRepository perfilRepository;

    @Override
    public Optional< Perfil > findById(Long id) {
        return perfilRepository.findById(id);
    }

    @Override
    public void save(Perfil perfil) {
        perfilRepository.save(perfil);
    }

    @Override
    public List < Perfil > findAll() {
        return perfilRepository.findAll();
    }

    @Override
    public void delete(long id) {
        perfilRepository.deleteById(id);
    }
}
