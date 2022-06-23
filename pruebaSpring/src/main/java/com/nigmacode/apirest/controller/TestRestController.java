package com.nigmacode.apirest.controller;

import java.util.List;
import java.util.Optional;

import com.nigmacode.apirest.entity.*;
import com.nigmacode.apirest.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
        http://127.0.0.1:8080/api/test para obtener todos test*/
    @GetMapping("/test")
    public List<Test> findAll(){
//retornará todos los usuarios
        return testService.findAll();
    }

    /*Este método se hará cuando por una petición GET (como indica la anotación) se llame a la url
           http://127.0.0.1:8080/api/test mediante un Json*/
    @GetMapping("/test/params")
    public List<Test> findByParameters(@RequestBody Test test){
        List<Test> list = testService.findByExample(test);
        for (Test t :list) {
            t.toString();
        }
        if(list.isEmpty()){
            throw new RuntimeException("Test not found");
        }
        return list;
    }

    /*Este método se hará cuando por una petición GET (como indica la anotación) se llame a la url + el id de una ejecucion
        http://127.0.0.1:8080/api/test/nombre1 para obtener un test concreto mediante el nombre*/
    @GetMapping("/test/{nombre}")
    public Test getTest(@PathVariable String nombre){
        Test user = testService.findByNombre(nombre);

        if(user == null) {
            throw new RuntimeException("Test name not found -"+nombre);
        }
        return user;
    }
    /*Este método se hará cuando por una petición GET (como indica la anotación) se llame a la url + el id de una ejecucion
            http://127.0.0.1:8080/api/test/ID/1 para obtener un test concreto mediante el ID*/
    @GetMapping("/test/ID/{userId}")
    public Optional<Test> getUser(@PathVariable int userId){
        Optional<Test> test = testService.findById(userId);

        if(test == null) {
            throw new RuntimeException("Test id not found -"+userId);
        }
        else {
            return test;
        }
    }

/*Este método se hará cuando por una petición POST (como indica la anotación) se llame a la url
       http://127.0.0.1:8080/api/test/ para publicar un test */

    @PostMapping("/test")
    public Test addUser(@RequestBody Test test) {
        test.setCod_test(0);
/*Para crear un nuevo test, no debe incumplir ninguna restricción como la de la clave foránea.
El test no puede hacer referencia a un usuario o a un caso de uso que no exista.
*/
        Optional<User> us = userService.findById(test.getCod_usuario());
        Optional<CasoUso> cs = casoService.findById(test.getId_caso_uso());
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
    /*Este método hace un PUT con el que actualizará el test que le indiquemos por su cod_test.
    En caso de no indicarle el cod_test funcionará como un POST e insertará un nuevo test.
     http://127.0.0.1:8080/api/test/
    */
    @PutMapping("/test")
    public Test updateUser(@RequestBody Test test) {
        testService.save(test);
        return test;
    }

/*Este método hace un DELETE con el que borra el test cuyo cod_test coincida con el que le pasemos por parámetro   http://127.0.0.1:8080/api/ejecutar/1*/

    @DeleteMapping("/test/{userId}")
    public String deleteTest(@PathVariable int userId) {

        Optional<Test> test = testService.findById(userId);

        if(test == null) {
            throw new RuntimeException("Test id not found -"+userId);
        }
        testService.deleteById(userId);

        return "Deleted test id - "+userId;
    }
}