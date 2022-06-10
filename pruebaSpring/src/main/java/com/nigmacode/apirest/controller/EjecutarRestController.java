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

public class EjecutarRestController {

    @Autowired
    private EjecutarService ejecutarService;
    @Autowired
    private UserService userService;
    @Autowired
    private TestService testService;

    /*Este método se hará cuando por una petición GET (como indica la anotación) se llame a la url
    http://127.0.0.1:8080/api/users*/
    @GetMapping("/ejecutar")
    public List<Ejecutar> findAllEjecutar(){
        //retornará todos los usuarios
        try {
            return ejecutarService.findAll();
        }catch (IllegalArgumentException err){
            List<Ejecutar> p = new ArrayList<>();
            return p;
        }
    }

    @GetMapping("/ejecutar/params")
    public List<Ejecutar> findByParameters(@RequestBody Ejecutar ejecutar){
        List<Ejecutar> list = ejecutarService.findByParameters(ejecutar);
        for (Ejecutar t :list) {
            t.toString();
        }


        if(list.isEmpty()){
            throw new RuntimeException("Ejecucion not found");
        }
        return list;
    }


    /*Este método se hará cuando por una petición GET (como indica la anotación) se llame a la url + el id de un usuario
    http://127.0.0.1:8080/api/users/1*/
    @GetMapping("/ejecutar/{userId}")
    public Ejecutar getEjecucion(@PathVariable int userId){
        Ejecutar user = ejecutarService.findById(userId);

        if(user == null) {
            throw new RuntimeException("Ejecucion id not found -"+userId);
        }
        //retornará al usuario con id pasado en la url
        return user;
    }

    /*Este método se hará cuando por una petición POST (como indica la anotación) se llame a la url
    http://127.0.0.1:8080/api/users/  */

    @PostMapping("/ejecutar")
    public Ejecutar addUser(@RequestBody Ejecutar ejecutar) {
        ejecutar.setCod_ejecuta(0);

        //Este metodo guardará al usuario enviado
        User us = userService.findById(ejecutar.getId_user());
        Test ts = testService.findById(ejecutar.getId_test());
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
    http://127.0.0.1:8080/api/users/  */
    @PutMapping("/ejecutar")
    public Ejecutar updateUser(@RequestBody Ejecutar ejecutar) {
        ejecutarService.save(ejecutar);

        //este metodo actualizará al usuario enviado

        return ejecutar;
    }

    /*Este método se hará cuando por una petición DELETE (como indica la anotación) se llame a la url + id del usuario
    http://127.0.0.1:8080/api/users/1  */

    @DeleteMapping("/ejecutar/{userId}")
    public String deleteEjecucion(@PathVariable int userId) {

        Ejecutar ejecutar = ejecutarService.findById(userId);

        if(ejecutar == null) {
            throw new RuntimeException("User id not found -"+userId);
        }

        ejecutarService.deleteById(userId);

        //Esto método, recibira el id de un usuario por URL y se borrará de la bd.
        return "Deleted ejecucion id - "+userId;
    }
}
