package com.nigmacode.apirest.controller;

import com.nigmacode.apirest.entity.Perfil;
import com.nigmacode.apirest.service.PerfilService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class PerfilRestController {
    @Autowired
    private PerfilService perfilservice;
    @GetMapping("/perfil")
    public List<Perfil> FindAllPerfil(){
        return perfilservice.findAll();
    }
    @GetMapping("/perfil/{id}")
    public Optional<Perfil> FindByID(@PathVariable long id){
        Optional<Perfil> perfil= perfilservice.findById(id);
        if(perfil == null) {
            throw new RuntimeException("Perfil id not found -"+id);
        }
        return perfil;
    }


}
