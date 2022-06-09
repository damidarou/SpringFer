package com.nigmacode.apirest.service;

import com.nigmacode.apirest.entity.Perfil;

import java.util.List;
import java.util.Optional;

public interface PerfilService {

    List < Perfil > findAll();

    void save(Perfil perfil);

     Optional<Perfil> findById(Integer id);

    void delete(Integer id);
}
