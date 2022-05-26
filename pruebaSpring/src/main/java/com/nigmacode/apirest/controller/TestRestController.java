package com.nigmacode.apirest.controller;

import java.util.List;

import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.nigmacode.apirest.entity.Test;
import com.nigmacode.apirest.service.TestService;

//Indiciamos que es un controlador rest
@RestController
@RequestMapping("/api") //esta sera la raiz de la url, es decir http://127.0.0.1:8080/api/

public class TestRestController {

    //Inyectamos el servicio para poder hacer uso de el
    @Autowired
    private TestService userService;

    /*Este método se hará cuando por una petición GET (como indica la anotación) se llame a la url
    http://127.0.0.1:8080/api/users*/
    @GetMapping("/users")
    public List<Test> findAll(){
        //retornará todos los usuarios
        return userService.findAll();
    }

    @GetMapping("/test")
    public List<Test> findAll2(){
        //retornará todos los usuarios
        return userService.findAll();
    }

    @GetMapping("/test/params")
    public List<Test> findByParameters(@RequestBody Test test){
        List<Test> list = userService.findByParameters(test);
        if(list.isEmpty()){
            throw new RuntimeException("Test not found");
        }
        return list;
    }

    @GetMapping("/test/{nombre}")
    public Test getTest(@PathVariable String nombre){
        Test user = userService.findByName(nombre);

        if(user == null) {
            throw new RuntimeException("Test name not found -"+nombre);
        }
        //retornará al usuario con id pasado en la url
        return user;
    }

    /*Este método se hará cuando por una petición GET (como indica la anotación) se llame a la url + el id de un usuario
    http://127.0.0.1:8080/api/users/1*/
    @GetMapping("/users/{userId}")
    public Test getUser(@PathVariable int userId){
        Test user = userService.findById(userId);

        if(user == null) {
            throw new RuntimeException("User id not found -"+userId);
        }
        //retornará al usuario con id pasado en la url
        return user;
    }

    /*Este método se hará cuando por una petición POST (como indica la anotación) se llame a la url
    http://127.0.0.1:8080/api/users/  */

    @PostMapping("/users")
    public Test addUser(@RequestBody Test test) {
        test.setCod_test(0);

        //Este metodo guardará al usuario enviado
        userService.save(test);

        return test;

    }
    /*Este método se hará cuando por una petición PUT (como indica la anotación) se llame a la url
    http://127.0.0.1:8080/api/users/  */
    @PutMapping("/users")
    public Test updateUser(@RequestBody Test test) {

        userService.save(test);

        //este metodo actualizará al usuario enviado

        return test;
    }

    /*Este método se hará cuando por una petición DELETE (como indica la anotación) se llame a la url + id del usuario
    http://127.0.0.1:8080/api/users/1  */

    @DeleteMapping("users/{userId}")
    public String deleteTest(@PathVariable int userId) {

        Test test = userService.findById(userId);

        if(test == null) {
            throw new RuntimeException("User id not found -"+userId);
        }

        userService.deleteById(userId);

        //Esto método, recibira el id de un usuario por URL y se borrará de la bd.
        return "Deleted user id - "+userId;
    }

}
