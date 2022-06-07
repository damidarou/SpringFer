package com.nigmacode.apirest.controller;

import com.nigmacode.apirest.entity.Perfil;
import com.nigmacode.apirest.entity.User;
import com.nigmacode.apirest.service.PerfilService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class PerfilRestController {
    @Autowired
    private PerfilService perfilservice;
    @GetMapping("/perfil")
    public List<Perfil> FindAllPerfil(){
        List<Perfil>toret=perfilservice.findAll();
        for (Perfil perfil:toret) {
            for (User usuario:perfil.getUsuarios()) {
                usuario.setProyectos(null);
            }
        }
        return toret;
    }
    @GetMapping("/perfil/{id}")
    public Optional<Perfil> FindByID(@PathVariable long id){
        Optional<Perfil> perfil= perfilservice.findById(id);
        if(perfil == null) {
            throw new RuntimeException("Perfil id not found -"+id);
        }
        return perfil;
    }
    @PostMapping("/perfil")
    public Perfil addPerfil(@RequestBody Perfil perfil){
        perfilservice.save(perfil);
        perfil.setUsuarios(null);
        return perfil;
    }
    @PutMapping("/perfil")
    public Perfil updatePerfil(@RequestBody Perfil perfil){
        perfilservice.save(perfil);
        perfil.setUsuarios(null);
        return perfil;
    }
    @DeleteMapping("/perfil/{perfilID}")
    public String borrarPerfil(@PathVariable int perfilID){
        Optional<Perfil> perfil = perfilservice.findById(Long.valueOf(perfilID));

        if(perfil == null) {
            throw new RuntimeException("User id not found -"+perfilID);
        }

        perfilservice.delete(perfilID);

        return "Deleted user id - "+perfilID;

    }

}
