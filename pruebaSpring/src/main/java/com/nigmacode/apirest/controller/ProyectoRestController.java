
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
/*Este método hace un GET de todos los proyectos que hay en la base de datos    http://127.0.0.1:8080/api/proyecto*/
    @GetMapping("/proyecto")
    public List<Proyecto> findAllProyecto(){

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

//Este método hace un GET del proyecto cuyo codigo coincide con el que le pasemos por parámetro     http://127.0.0.1:8080/api/proyecto/1/
    @GetMapping("/proyecto/{proyectId}")
    public Proyecto getProyecto(@PathVariable int proyectId){
        Proyecto proyecto = proyectoService.findById(proyectId);
        if (proyecto == null) {
            throw new RuntimeException("Project not fount-" + proyectId);
        }
//retornará al usuario con id pasado en la url
        return proyecto;
    }

//Este método hace un POST con el que crea un nuevo proyecto  http://127.0.0.1:8080/api/proyecto/
    @PostMapping("/proyecto")
    public Proyecto addUser(@RequestBody Proyecto proyecto) {

//El usuario debe existir para que se pueda crear el proyecto
        User us = userService.findById(proyecto.getCod_usuario());
        if(us == null){
            throw new RuntimeException("El usuario "+proyecto.getCod_usuario()+" no existe");
        } else {
            proyectoService.save(proyecto);
        }
        return proyecto;
    }

    /*Este método hace un PUT, con lo que actualiza el proyecto cuyo codigo le indiquemos
    Si no le indicamos el código funcionará como un POST y creará un nuevo proyecto      http://127.0.0.1:8080/api/proyecto*/
    @PutMapping("/proyecto")
    public Proyecto updateUser(@RequestBody Proyecto proyecto) {
        proyectoService.save(proyecto);
//este metodo actualizará al usuario enviado
        return proyecto;
    }

//Este método hace un DELETE del proyecto cuyo codigo le pasemos por parámetro       http://127.0.0.1:8080/api/proyecto/
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

//Este método realiza un GET de todos los proyectos cuyos datos coincidan con los parámetros que le indiquemos       http://127.0.0.1:8080/api/proyecto/find
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