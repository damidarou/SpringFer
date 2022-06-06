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

    /*Este método se hará cuando por una petición GET (como indica la anotación) se llame a la url
    http://127.0.0.1:8080/api/users*/
    @GetMapping("/test")
    public List<Test> findAll(){
        //retornará todos los usuarios
        return testService.findAll();
    }

    @GetMapping("/tests")
    public List<Test> findAll2(){
        //retornará todos los usuarios
        return testService.findAll();
    }

    @GetMapping("/tests/params")
    public List<Test> findByParameters(@RequestBody Test test){
        List<Test> list = testService.findByParameters(test);
        estado estado = null;
        for (Test t :list) {
            t.toString();
            System.out.println("Estado: "+estado.getCodigoEstado(t.getEstado()));
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
    @GetMapping("/test/{userId}")
    public Test getUser(@PathVariable int userId){
        Test user = testService.findById(userId);

        if(user == null) {
            throw new RuntimeException("Test id not found -"+userId);
        }
        //retornará al usuario con id pasado en la url
        return user;
    }

    /*Este método se hará cuando por una petición POST (como indica la anotación) se llame a la url
    http://127.0.0.1:8080/api/users/  */

    @PostMapping("/test")
    public Test addUser(@RequestBody Test test) {
        test.setCod_test(0);

        //Este metodo guardará al usuario enviado
        User us = userService.findById(test.getCod_usuario());
        if(us == null){
            throw new RuntimeException("El usuario "+test.getCod_usuario()+" no existe");
        } else {
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

    // TODOS LOS METODOS PARA LAS OPERACIONES DE CASOS DE USO

    @Autowired
    private CasoService casoService;

    /*Este método ser hará cuando por una petición GET(como indica la anotacion)
    se llame a la url http://127.0.0.1:8080/api/users
    */
    @GetMapping("/caso")
    public List<Caso_uso> findAllCasoUso() {

        //retornará todos los usuarios
        return casoService.findAll();
    }

    /*Este método ser hará cuando por una petición GET(como indica la anotacion)
    se llame a la url http://127.0.0.1:8080/api/users/1
    */
    @GetMapping("/caso/{userId}")
    public Caso_uso getUserCasoUso(@PathVariable int userId){
        Caso_uso caso = casoService.findById(userId);

        if(caso==null) {
            throw new RuntimeException("User id not found -" +userId);
        }
        //retornará al usuario con id pasado en la url
        return caso;
    }


    /*Este método ser hará cuando por una petición GET(como indica la anotacion)
    se llame a la url http://127.0.0.1:8080/api/users
    */
    @PostMapping("/caso")
    public Caso_uso addUserCasoUso(@RequestBody Caso_uso caso){
        caso.setCod_caso_uso(0);

        User us = userService.findById(caso.getCod_usuario());
        if(us == null){
            throw new RuntimeException("El usuario "+caso.getCod_usuario()+" no existe");
        } else {
            casoService.save(caso);
        }
        //Este metodo guardará al usario enviado
        return caso;
    }



    /*Este método ser hará cuando por una petición GET(como indica la anotacion)
    se llame a la url http://127.0.0.1:8080/api/users
    */
    @PutMapping("/caso")
    public Caso_uso updateCasoUso(@RequestBody Caso_uso caso) {
        casoService.save(caso);

        //este metodo actualizará al usuario enviado

        return caso;
    }

    /*Este método ser hará cuando por una petición GET(como indica la anotacion)
    se llame a la url http://127.0.0.1:8080/api/users/1
    */
    @DeleteMapping("/caso/{userId}")
    public String deleteCaso(@PathVariable int userId){
        Caso_uso caso = casoService.findById(userId);

        if (caso==null){
            throw new RuntimeException("Case id not found -"+userId);
        }
        testService.deleteById(userId);

        //Este método, recibirá el ide de un usuario por URL y se borrará de la bd.
        return "Delete case id -"+userId;
    }
    /*
    @GetMapping("caso/{nombre_caso_uso}")
    public Caso_uso getNombreCasoUso(@PathVariable String nombre_caso_uso){
        Caso_uso caso = casoService.findByNombre(nombre_caso_uso);

        if (caso==null){
            throw new RuntimeException("Nombre caso de uso not found -"+nombre_caso_uso);
        }

        Este método, recibirá el ide de un usuario por URL y se borrará de la bd.
        return caso;
    }
*/
    @GetMapping("/casos/parameters")
    public List<Caso_uso> getByJSONCasoUso(@RequestBody Caso_uso caso) {
        List<Caso_uso> list = casoService.findByJSON(caso);
        for (Caso_uso t : list) {
            t.toString();
        }
        if(list.isEmpty()){
            throw new RuntimeException("Case not found");
        }
        return list;
    }

    //TODOS LOS METODOS PARA LAS OPERACIONES DE PROYECTO

    @Autowired
    private ProyectoService proyectoService;
    @Autowired
    EntityManager entityManager;
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

    @Autowired
    private UserService userService;
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
            t.toString();
        }
        if(list.isEmpty()){
            throw new RuntimeException("Project not found");
        }
        return list;
    }

    //TODOS LOS METODOS PARA LAS OPERACIONES DE EJECUTAR

    @Autowired
    private EjecutarService ejecutarService;

    /*Este método se hará cuando por una petición GET (como indica la anotación) se llame a la url
    http://127.0.0.1:8080/api/users*/
    @GetMapping("/ejecutar")
    public List<Ejecutar> findAllEjecutar(){
        //retornará todos los usuarios
        try {
            for (Ejecutar ejecutar : ejecutarService.findAll()) {
              ejecutar.setUsers2(null);
              ejecutar.setTests2(null);
            }

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
