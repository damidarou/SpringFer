package com.nigmacode.apirest.controller;

import com.nigmacode.apirest.entity.Ejecutar;
import com.nigmacode.apirest.entity.Test;
import com.nigmacode.apirest.entity.User;
import com.nigmacode.apirest.service.EjecutarService;
import com.nigmacode.apirest.service.TestService;
import com.nigmacode.apirest.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class EjecutarRestController {

    @Autowired
    private EjecutarService ejecutarService;
    @Autowired
    private UserService userService;
    @Autowired
    private TestService testService;

    /*Este método se hará cuando por una petición GET (como indica la anotación) se llame a la url
    http://127.0.0.1:8080/api/ejecutar para obtener todas las ejecuciones*/
    @GetMapping("/ejecutar")
    public List<Ejecutar> findAllEjecutar(){
        //retornará todas las ejecuciones
        try {
            return ejecutarService.findAll();
        }catch (IllegalArgumentException err){
            List<Ejecutar> p = new ArrayList<>();
            return p;
        }
    }
    /*Este método se hará cuando por una petición GET (como indica la anotación) se llame a la url
        http://127.0.0.1:8080/api/ejecutar mediante un Json*/
    @GetMapping("/ejecutar/params")
    public List<Ejecutar> findByParameters(@RequestBody Ejecutar ejecutar){
        List<Ejecutar> list = ejecutarService.findByExample(ejecutar);
        for (Ejecutar t :list) {
            t.toString();
        }


        if(list.isEmpty()){
            throw new RuntimeException("Ejecucion not found");
        }
        return list;
    }


    /*Este método se hará cuando por una petición GET (como indica la anotación) se llame a la url + el id de una ejecucion
    http://127.0.0.1:8080/api/ejecutar/1 para obtener una ejecución concreta mediante el Id*/
    @GetMapping("/ejecutar/{userId}")
    public Optional<Ejecutar> getEjecuta(@PathVariable int userId){
        Optional<Ejecutar> user = ejecutarService.findById(userId);

        if(user == null) {
            throw new RuntimeException("Ejecucion id not found -"+userId);
        }
        //retornará la ejecución con id pasado en la url
        return user;
    }

    /*Este método se hará cuando por una petición POST (como indica la anotación) se llame a la url
    http://127.0.0.1:8080/api/ejecutar/ para publicar una ejecución */
    @PostMapping("/ejecutar")
    public Ejecutar addEjecutar(@RequestBody Ejecutar ejecutar) {
        ejecutar.setCod_ejecuta(0);

        //Este metodo guardará la ejecución
        Optional<User> us = userService.findById(ejecutar.getId_user());
        Optional<Test> ts = testService.findById(ejecutar.getId_test());
        if(us == null){
            throw new RuntimeException("El usuario "+ejecutar.getId_user()+" no existe");
        } else if (ts == null) {
            throw new RuntimeException("El test "+ejecutar.getId_test()+" no existe");
        } else {
            ejecutarService.save(ejecutar);
        }
        return ejecutar;

    }
    /*Este método se hará cuando por una petición PUT (como indica la anotación) se llame a la url
    http://127.0.0.1:8080/api/ejecutar/  para modificar una ejecución*/
    @PutMapping("/ejecutar")
    public Ejecutar updateUser(@RequestBody Ejecutar ejecutar) {
        ejecutarService.save(ejecutar);

        //este metodo actualizará la ejecución enviada

        return ejecutar;
    }

    /*Este método se hará cuando por una petición DELETE (como indica la anotación) se llame a la url + id del usuario
    http://127.0.0.1:8080/api/ejecutar/1  para borrar la ejecución con ese id*/

    @DeleteMapping("/ejecutar/{ejecutaId}")
    public String deleteEjecucion(@PathVariable int ejecutaId) {

        Optional<Ejecutar> ejecutar = ejecutarService.findById(ejecutaId);

        if(ejecutar == null) {
            throw new RuntimeException("User id not found -"+ejecutaId);
        }

        ejecutarService.deleteById(ejecutaId);

        //Esto método, recibira el id de un usuario por URL y se borrará de la bd.
        return "Deleted ejecucion id - "+ejecutaId;
    }
}
