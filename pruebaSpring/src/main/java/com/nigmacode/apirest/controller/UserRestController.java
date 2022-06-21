package com.nigmacode.apirest.controller;

import com.nigmacode.apirest.entity.Proyecto;
import com.nigmacode.apirest.entity.User;
import com.nigmacode.apirest.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

//Indiciamos que es un controlador rest
@RestController
@RequestMapping("/api") //esta sera la raiz de la url, es decir http://127.0.0.1:8080/api/

public class UserRestController {

    //Inyectamos el servicio para poder hacer uso de el
    @Autowired
    private UserService userService;

    /*Este método se hará cuando por una petición GET (como indica la anotación) se llame a la url
    http://127.0.0.1:8080/api/users*/
    @GetMapping("/users")
    public List<User> findAll(){
        //retornará todos los usuarios
        try {
            for (User user : userService.findAll()) {
                for (Proyecto proyecto:user.getProyectos()) {
                  proyecto.setCaso_usos(null);
                }
            }

            return userService.findAll();
        }catch (IllegalArgumentException err){
            List<User> p = new ArrayList<>();
            return p;
        }

    }

    /*Este método se hará cuando por una petición GET (como indica la anotación) se llame a la url + el id de un usuario
    http://127.0.0.1:8080/api/users/1*/
    @GetMapping("/users/{userId}")
    public User getUser(@PathVariable int userId){
        User user = userService.findById(userId);

        if(user == null) {
            throw new RuntimeException("User id not found -"+userId);
        }
        for (Proyecto proyecto:user.getProyectos()) {
            proyecto.setCaso_usos(null);
        }
        return user;
    }
    @GetMapping("/users/username/{username}")
    public List<User> getUsername(@PathVariable String username){
        List<User> users = userService.findByUsername(username);

        if(users == null) {
            throw new RuntimeException("User id not found -"+username);
        }
        for (User user:users) {
            for (Proyecto proyecto:user.getProyectos()) {
                proyecto.setCaso_usos(null);
            }
        }

        return users;
    }
    /*Este método se hará cuando por una petición POST (como indica la anotación) se llame a la url
    http://127.0.0.1:8080/api/users/  */
    @PostMapping("/users")
    public User addUser(@RequestBody User user) {
        user.setCod_usaurio(0);

        //Este metodo guardará al usuario enviado
        userService.save(user);

        return user;

    }
    /*Este método se hará cuando por una petición PUT (como indica la anotación) se llame a la url
    http://127.0.0.1:8080/api/users/  */
    @PutMapping("/users")
    public User updateUsaurio(@RequestBody User user) {

        userService.save(user);

        //este metodo actualizará al usuario enviado

        return user;
    }

    /*Este método se hará cuando por una petición DELETE (como indica la anotación) se llame a la url + id del usuario
    http://127.0.0.1:8080/api/users/1  */
    @DeleteMapping("users/{userId}")
    public String deteteUser(@PathVariable int userId) {

        User user = userService.findById(userId);

        if(user == null) {
            throw new RuntimeException("User id not found -"+userId);
        }

        userService.deleteById(userId);

        //Esto método, recibira el id de un usuario por URL y se borrará de la bd.
        return "Deleted user id - "+userId;
    }
    @GetMapping("/users/find")
    public List<User> buscar(@RequestBody User user){
        //retornará todos los usuarios
        return userService.buscar(user);
    }

}