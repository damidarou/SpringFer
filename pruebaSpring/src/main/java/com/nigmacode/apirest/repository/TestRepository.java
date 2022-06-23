package com.nigmacode.apirest.repository;

import com.nigmacode.apirest.entity.Test;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TestRepository extends JpaRepository<Test, Integer>  {
    Test findByNombre(String nombre);
}
