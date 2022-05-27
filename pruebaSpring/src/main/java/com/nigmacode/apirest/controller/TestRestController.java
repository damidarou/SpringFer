package com.nigmacode.apirest.controller;

import java.util.List;

import com.nigmacode.apirest.dao.TestDAO;
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
import com.nigmacode.apirest.entity.Caso_uso;
import com.nigmacode.apirest.service.CasoService;

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
    @GetMapping("/test/{userId}")
    public Test getUser(@PathVariable int userId){
        Test user = testService.findById(userId);

        if(user == null) {
            throw new RuntimeException("User id not found -"+userId);
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
        testService.save(test);

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

    @DeleteMapping("test/{userId}")
    public String deleteTest(@PathVariable int userId) {

        Test test = testService.findById(userId);

        if(test == null) {
            throw new RuntimeException("User id not found -"+userId);
        }

        testService.deleteById(userId);

        //Esto método, recibira el id de un usuario por URL y se borrará de la bd.
        return "Deleted user id - "+userId;
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

        //Este metodo guardará al usario enviado
        casoService.save(caso);

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
    @DeleteMapping("caso/{userId}")
    public String deleteCaso(@PathVariable int userId){
        Caso_uso caso = casoService.findById(userId);

        if (caso==null){
            throw new RuntimeException("User id not found -"+userId);
        }
        testService.deleteById(userId);

        //Este método, recibirá el ide de un usuario por URL y se borrará de la bd.
        return "Delete user id -"+userId;
    }
    /*
    @GetMapping("caso/{nombre_caso_uso}")
    public Caso_uso getNombreCasoUso(@PathVariable String nombre_caso_uso){
        Caso_uso caso = casoService.findByNombre(nombre_caso_uso);

        if (caso==null){
            throw new RuntimeException("Nombre caso de uso not found -"+nombre_caso_uso);
        }

        //Este método, recibirá el ide de un usuario por URL y se borrará de la bd.
        return caso;
    }
*/
    @GetMapping("/casos/parameters")
    public List<Caso_uso> getByJSONCasoUso(@RequestBody Caso_uso caso){
        return casoService.findByJSON(caso);
    }


}
