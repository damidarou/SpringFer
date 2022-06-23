package com.nigmacode.apirest.controller;

import com.nigmacode.apirest.entity.CasoUso;
import com.nigmacode.apirest.entity.Proyecto;
import com.nigmacode.apirest.entity.User;
import com.nigmacode.apirest.repository.CasoUsoRepository;
import com.nigmacode.apirest.service.CasoService;
import com.nigmacode.apirest.service.ProyectoService;
import com.nigmacode.apirest.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api") //esta sera la raiz de la url, es decir http://127.0.0.1:8080/api/

public class CasoUsoRestController {
    @Autowired
    private CasoService casoService;
    @Autowired
    private UserService userService;
    @Autowired
    private ProyectoService proyectoService;

    @Autowired
    CasoUsoRepository casoUsoRepository;

    /*Este método ser hará cuando por una petición GET(como indica la anotacion)
    se llame a la url http://127.0.0.1:8080/api/caso
    Esta funcion llama a todos los casos de uso
    */
    @GetMapping("/caso")
    public List<CasoUso> findAllCasoUso() {
        //retornará todos los casos
        return casoService.findAll();
    }

    /*Este método ser hará cuando por una petición GET(como indica la anotacion)
    se llame a la url http://127.0.0.1:8080/api/caso/{casoID}

    */
    @GetMapping("/caso/{casoID}")
    public Optional<CasoUso> getUserCasoUso(@PathVariable int casoID){
        Optional<CasoUso> caso = casoService.findById(casoID);

        if(caso==null) {
            throw new RuntimeException("Caso id not found " +casoID);
        }
        //retornará al caso con id pasado en la url
        return caso;
    }

    @GetMapping("/caso/nombre/{nombre}")
    public List<CasoUso> findByNombre(@PathVariable String nombre_caso_uso) {
        List<CasoUso> casoUsos = casoService.findByNombre(nombre_caso_uso);

        for (CasoUso casoUso : casoService.findByNombre(nombre_caso_uso)) {
                casoUso.setTests(null);
        }

        if (casoUsos == null) {
            throw new RuntimeException("Project not fount-" + nombre_caso_uso);
        }
        return casoUsos;
    }

    /*Este método ser hará cuando por una petición Post(como indica la anotacion)
    se llame a la url http://127.0.0.1:8080/api/caso
    */
    @PostMapping("/caso")
    public CasoUso addUserCasoUso(@RequestBody CasoUso caso){
        caso.setCod_caso_uso(0);
        Optional<User> us = userService.findById(caso.getCod_usuario());
        Optional<Proyecto> ps = proyectoService.findById(caso.getId_proyecto());
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
    public CasoUso updateCasoUso(@RequestBody CasoUso caso) {
        //este metodo actualizará al caso enviado
        casoService.save(caso);
        return caso;
    }

    /*Este método ser hará cuando por una petición Delete(como indica la anotacion)
    se llame a la url http://127.0.0.1:8080/api/caso/{casoID}
    */
    @DeleteMapping("/caso/{casoID}")
    public String deleteCaso(@PathVariable int casoID){
        Optional<CasoUso> caso = casoService.findById(casoID);

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
    public List<CasoUso> getByJSONCasoUso(@RequestBody CasoUso caso) {
        List<CasoUso> list = casoService.findByExample(caso);
        for (CasoUso t : list) {
            t.toString();
        }
        if(list.isEmpty()){
            throw new RuntimeException("Case not found");
        }
        return list;
    }

}
