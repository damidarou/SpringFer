package com.nigmacode.apirest.controller;

import com.nigmacode.apirest.entity.Caso_uso;
import com.nigmacode.apirest.entity.Proyecto;
import com.nigmacode.apirest.entity.User;
import com.nigmacode.apirest.service.ProyectoService;
import com.nigmacode.apirest.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityManager;
import java.util.ArrayList;
import java.util.List;

public class ProyectoRestController {
    @Autowired
    private ProyectoService proyectoService;
    @Autowired
    EntityManager entityManager;
    @Autowired
    private UserService userService;
    /*Este método se hará cuando por una petición GET (como indica la anotación) se llame a la url
    http://127.0.0.1:8080/Prueba/proyecto*/
    @GetMapping("/proyecto")
    public List<Proyecto> findAllProyecto(){
        //retornará todos los usuarios
        try {
            for (Proyecto proyectos : proyectoService.findAll()) {
                for (Caso_uso casos: proyectos.getCaso_usos()) {
                    casos.setTests(null);

                }
                proyectos.setUsuario(null);
            }

            return proyectoService.findAll();
        }catch (IllegalArgumentException err){
            List<Proyecto> p = new ArrayList<>();
            return p;
        }
    }

    /*Este método se hará cuando por una petición GET (como indica la anotación) se llame a la url + el id de un usuario
    http://127.0.0.1:8080/api/Prueba/1*/
    @GetMapping("/proyecto/{proyectId}")
    public Proyecto getProyecto(@PathVariable int proyectId){
        Proyecto proyecto = proyectoService.findById(proyectId);
        if (proyecto == null) {
            throw new RuntimeException("Project not fount-" + proyectId);
        }
        //retornará al usuario con id pasado en la url
        return proyecto;
    }

    /*Este método se hará cuando por una petición POST (como indica la anotación) se llame a la url
    http://127.0.0.1:8080/Prueba/proyecto/  */
    @PostMapping("/proyecto")
    public Proyecto addUser(@RequestBody Proyecto proyecto) {
        // user.setCod_usuario(7);
        //Este metodo guardará al usuario enviado
        User us = userService.findById(proyecto.getCod_usuario());
        if(us == null){
            throw new RuntimeException("El usuario "+proyecto.getCod_usuario()+" no existe");
        } else {
            proyectoService.save(proyecto);
        }
        return proyecto;
    }

    /*Este método se hará cuando por una petición PUT (como indica la anotación) se llame a la url
    http://127.0.0.1:8080/Prueba/proyecto/  */
    @PutMapping("/proyecto")
    public Proyecto updateUser(@RequestBody Proyecto proyecto) {
        proyectoService.save(proyecto);
        //este metodo actualizará al usuario enviado
        return proyecto;
    }

    /*Este método se hará cuando por una petición DELETE (como indica la anotación) se llame a la url + id del usuario
    http://127.0.0.1:8080/Prueba/proyecto/1  */
    @DeleteMapping("/proyecto/{proyectId}")
    public String deleteUser(@PathVariable int proyectId){
        Proyecto proyecto = proyectoService.findById(proyectId);
        if (proyecto == null) {
            throw new RuntimeException("Project not found-" + proyectId);
        }
        proyectoService.deleteById(proyectId);
        //Esto método, recibira el id de un usuario por URL y se borrará de la bd.
        return "Deleted project id-" + proyectId;
    }

    /*
        @GetMapping("/proyecto/nombre")
        public List<Proyecto> getByNombre(){
            retornará todos los usuarios
            return proyectoService.findAll();
        }
    */
    @GetMapping("/proyecto/find")
    public List<Proyecto> getByJSON(@RequestBody Proyecto proyecto){
        List<Proyecto> list = proyectoService.findByJSON(proyecto);

        for (Proyecto t : list) {
            for (Caso_uso caso_uso: t.getCaso_usos()) {
                caso_uso.setTests(null);
            }
            t.setUsuario(null);
            t.toString();
        }
        if(list.isEmpty()){
            throw new RuntimeException("Project not found");
        }
        return list;
    }
}
