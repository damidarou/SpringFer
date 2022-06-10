package com.nigmacode.apirest.controller;

import com.nigmacode.apirest.entity.Caso_uso;
import com.nigmacode.apirest.entity.Proyecto;
import com.nigmacode.apirest.entity.User;
import com.nigmacode.apirest.service.CasoService;
import com.nigmacode.apirest.service.ProyectoService;
import com.nigmacode.apirest.service.TestService;
import com.nigmacode.apirest.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

public class CasoUsoRestController {
    @Autowired
    private CasoService casoService;
    @Autowired
    private UserService userService;
    @Autowired
    private ProyectoService proyectoService;

    /*Este método ser hará cuando por una petición GET(como indica la anotacion)
    se llame a la url http://127.0.0.1:8080/api/caso
    Esta funcion llama a todos los casos de uso
    */
    @GetMapping("/caso")
    public List<Caso_uso> findAllCasoUso() {
        //retornará todos los casos
        return casoService.findAll();
    }

    /*Este método ser hará cuando por una petición GET(como indica la anotacion)
    se llame a la url http://127.0.0.1:8080/api/caso/{casoID}

    */
    @GetMapping("/caso/{casoID}")
    public Caso_uso getUserCasoUso(@PathVariable int casoID){
        Caso_uso caso = casoService.findById(casoID);

        if(caso==null) {
            throw new RuntimeException("Caso id not found " +casoID);
        }
        //retornará al caso con id pasado en la url
        return caso;
    }


    /*Este método ser hará cuando por una petición Post(como indica la anotacion)
    se llame a la url http://127.0.0.1:8080/api/caso
    */
    @PostMapping("/caso")
    public Caso_uso addUserCasoUso(@RequestBody Caso_uso caso){
        caso.setCod_caso_uso(0);
        User us = userService.findById(caso.getCod_usuario());
        Proyecto ps = proyectoService.findById(caso.getId_proyecto());
        if(us == null){
            throw new RuntimeException("El usuario "+caso.getCod_usuario()+" no existe");
        }
        if(ps == null){
            throw new RuntimeException("El proyecto "+caso.getId_proyecto()+" no existe");
        }
        else {
            //Este metodo guardará al caso enviado
            casoService.save(caso);
        }
        return caso;
    }



    /*Este método ser hará cuando por una petición Put(como indica la anotacion)
    se llame a la url http://127.0.0.1:8080/api/caso
    */
    @PutMapping("/caso")
    public Caso_uso updateCasoUso(@RequestBody Caso_uso caso) {
        //este metodo actualizará al caso enviado
        casoService.save(caso);
        return caso;
    }

    /*Este método ser hará cuando por una petición Delete(como indica la anotacion)
    se llame a la url http://127.0.0.1:8080/api/caso/{casoID}
    */
    @DeleteMapping("/caso/{casoID}")
    public String deleteCaso(@PathVariable int casoID){
        Caso_uso caso = casoService.findById(casoID);

        if (caso==null){
            throw new RuntimeException("Caso id not found "+casoID);
        }
        //Este método, recibirá el id de un caso por URL y se borrará de la bd.
        casoService.deleteById(casoID);
        return "Delete case id "+casoID;
    }

    /*Este método ser hará cuando por una petición Get(como indica la anotacion)
    se llame a la url http://127.0.0.1:8080/api/caso/parameters
    Este metodo busca en funcion de un Json los casos de uso que mas se parezcan
    */
    @GetMapping("/caso/parameters")
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

}
