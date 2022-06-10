package com.nigmacode.apirest.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.nigmacode.apirest.entity.*;
import com.nigmacode.apirest.entity.estado;
import com.nigmacode.apirest.service.*;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.EntityManager;

//Indiciamos que es un controlador rest
@RestController
@RequestMapping("/api") //esta sera la raiz de la url, es decir http://127.0.0.1:8080/api/


public class TestRestController {

    //Inyectamos el servicio para poder hacer uso de el
    @Autowired
    private TestService testService;
    @Autowired
    private UserService userService;
    @Autowired
    private CasoService casoService;

    /*Este método se hará cuando por una petición GET (como indica la anotación) se llame a la url
    http://127.0.0.1:8080/api/users*/
    @GetMapping("/test")
    public List<Test> findAll(){
        //retornará todos los usuarios
        return testService.findAll();
    }

    @GetMapping("/test/params")
    public List<Test> findByParameters(@RequestBody Test test){
        List<Test> list = testService.findByParameters(test);
        for (Test t :list) {
            t.toString();
        }
        if(list.isEmpty()){
            throw new RuntimeException("Test not found");
        }
        return list;
    }

    @GetMapping("/test/{nombre}")
    public Test getTest(@PathVariable String nombre){
        Test user = testService.findByName(nombre);

        if(user == null) {
            throw new RuntimeException("Test name not found -"+nombre);
        }
        //retornará al usuario con id pasado en la url
        return user;
    }

    /*Este método se hará cuando por una petición GET (como indica la anotación) se llame a la url + el id de un usuario
    http://127.0.0.1:8080/api/users/1*/
    @GetMapping("/test/ID/{userId}")
    public Test getUser(@PathVariable int userId){
        Test user = testService.findById(userId);

        if(user == null) {
            throw new RuntimeException("Test id not found -"+userId);
        }
        //retornará al usuario con id pasado en la url
        else {
            return user;
        }
    }

    /*Este método se hará cuando por una petición POST (como indica la anotación) se llame a la url
    http://127.0.0.1:8080/api/users/  */

    @PostMapping("/test")
    public Test addUser(@RequestBody Test test) {
        test.setCod_test(0);

        //Este metodo guardará al usuario enviado
        User us = userService.findById(test.getCod_usuario());
        Caso_uso cs = casoService.findById(test.getId_caso_uso());
        if(us == null){
            throw new RuntimeException("El usuario "+test.getCod_usuario()+" no existe");
        }
        if(cs == null){
            throw new RuntimeException("El caso de uso "+test.getId_caso_uso()+" no existe");
        }
        else {
            testService.save(test);
        }
        return test;

    }
    /*Este método se hará cuando por una petición PUT (como indica la anotación) se llame a la url
    http://127.0.0.1:8080/api/users/  */
    @PutMapping("/test")
    public Test updateUser(@RequestBody Test test) {

        testService.save(test);

        //este metodo actualizará al usuario enviado

        return test;
    }

    /*Este método se hará cuando por una petición DELETE (como indica la anotación) se llame a la url + id del usuario
    http://127.0.0.1:8080/api/users/1  */

    @DeleteMapping("/test/{userId}")
    public String deleteTest(@PathVariable int userId) {

        Test test = testService.findById(userId);

        if(test == null) {
            throw new RuntimeException("User id not found -"+userId);
        }

        testService.deleteById(userId);

        //Esto método, recibira el id de un usuario por URL y se borrará de la bd.
        return "Deleted test id - "+userId;
    }
}
