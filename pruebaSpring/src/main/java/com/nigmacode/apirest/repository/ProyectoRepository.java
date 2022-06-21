package com.nigmacode.apirest.repository;

import com.nigmacode.apirest.entity.Perfil;
import com.nigmacode.apirest.entity.Proyecto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
public interface ProyectoRepository extends JpaRepository<Proyecto, Integer> {

    List<Proyecto> findByNombre(String nombre);
}
