package com.nigmacode.apirest.repository;

import com.nigmacode.apirest.entity.CasoUso;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CasoUsoRepository extends JpaRepository<CasoUso,Integer> {
    List<CasoUso> findByNombre(String nombre);
}
