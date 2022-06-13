package com.nigmacode.apirest.controller;

import com.nigmacode.apirest.entity.Perfil;
import com.nigmacode.apirest.entity.User;
import com.nigmacode.apirest.service.PerfilService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class PerfilRestController {
    @Autowired
    private PerfilService perfilservice;


    /*Este método se hará cuando por una petición GET (como indica la anotación) se llame a la url
   http://127.0.0.1:8080/api/perfil para que muestre todos los perfiles*/
    @GetMapping("/perfil")
    public List<Perfil> FindAllPerfil(){
        List<Perfil> toret= perfilservice.findAll();
        for (Perfil perfil:toret) {
            for (User user:perfil.getUsuarios()) {
                user.setProyectos(null);
            }
        }
        return toret;
    }

    /*Este método se hará cuando por una petición GET (como indica la anotación) se llame a la url + el id del perfil
    http://127.0.0.1:8080/api/perfil/1  para que solo muestre el perfil con ese id*/
    @GetMapping("/perfil/{id}")
    public Perfil FindByID(@PathVariable int id){
        Optional<Perfil> perfil= perfilservice.findById(id);
        Perfil toret = perfil.get();
        for (User user:toret.getUsuarios()) {
            user.setProyectos(null);
        }
        return toret;
    }

    /*Este método se hará cuando por una petición POST (como indica la anotación) se llame a la url
    http://127.0.0.1:8080/api/perfil/ para publicar ese perfil */
    @PostMapping("/perfil")
    public Perfil addPerfil(@RequestBody Perfil perfil){
        perfil.setId_perfil(0);
        perfilservice.save(perfil);
        return perfil;
    }

    /*Este método se hará cuando por una petición PUT (como indica la anotación) se llame a la url
http://127.0.0.1:8080/api/perfil/ para modificar un perfil */
    @PutMapping("/perfil")
    public Perfil updatePerfil(@RequestBody Perfil perfil){
        perfilservice.save(perfil);
        return perfil;
    }

    /*Este método se hará cuando por una petición DELETE (como indica la anotación) se llame a la url + id del usuario
    http://127.0.0.1:8080/api/perfil/1  para borrar el perfil con ese id*/
    @DeleteMapping("/perfil/{id}")
    public String deletePerfil(@PathVariable int id) {
        Optional<Perfil> perfil = perfilservice.findById(id);
        if (perfil == null) {
            return "El perfil " + id + " no existe";
        } else {
            perfilservice.delete(id);
            return "El perfil " + id + " se ha borrado con exito";
        }

    }
}
