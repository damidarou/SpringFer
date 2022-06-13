package com.nigmacode.apirest.dao;

import com.nigmacode.apirest.entity.User;
import com.nigmacode.apirest.service.UserService;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.util.List;

@Repository
public class UserDAOImpl implements UserDAO{

    @Autowired
    private EntityManager entityManager;

    /*Esta función desarrolla un método que funciona como un SELECT * FROM User para obtener todos los usuarios*/
    @Override
    public List<User> findAll() {
        Session currentSession = entityManager.unwrap(Session.class);
        Query<User> theQuery = currentSession.createQuery("from User", User.class);
        List<User> users = theQuery.getResultList();
        return users;

    }

    /*Esta función desarrolla un método que funciona como un SELECT * para el usuario cuyo código coincida con el que le pasamos*/
    @Override
    public User findById(int id) {
        Session currentSession = entityManager.unwrap(Session.class);

        User user = currentSession.get(User.class, id);

        return user;
    }

    /*Esta función desarrolla un método que funciona como un SELECT * para el usuario cuyo username coincida con el que le pasamos*/
    public List<User> findByUsername(String username) {
        Session currentSession = entityManager.unwrap(Session.class);

        Query<User> theQuery = currentSession.createQuery("from User where username like :username", User.class);
        theQuery.setParameter("username", username);
        List<User> user =  theQuery.getResultList();
        return user;
    }

    /*Esta funcion hace un SELECT * de todos los usuarios cuyos valores sean como los que les pasemos por parámetros*/
    public List<User> buscar(User user){
        String s="from User";
        Session currentSession = entityManager.unwrap(Session.class);
        if (user.getCod_usaurio()!=0 || user.getContraseña()!=null || user.getUsername()!=null || user.getId_perfil()!=0 ||user.getNombre()!=null||user.getApellido1()!=null||user.getApellido2()!=null){
            s+=" where ";
        }
        if (user.getCod_usaurio()!=0){
            s=s+"cod_usaurio="+user.getCod_usaurio();
        }
        if (user.getContraseña()!=null){
            if (s.contentEquals("from User where ")){
                s=s+" contraseña like '"+user.getContraseña()+"'";
            }else {
                s = s + " and contraseña like '"+user.getContraseña()+"'";
            }
        }
        if (user.getUsername()!=null){
            if (s.contentEquals("from User where ")){
                s=s+" username like '"+user.getUsername()+"'";
            }else {
                s = s + " and username like '"+user.getUsername()+"'";
            }
        }

        if (!s.contentEquals("from User")) {
            if (s.contentEquals("from User where ")) {
                s = s + " active=" + user.isActive();
            } else {
                s = s + " and active=" + user.isActive();
            }
        }
        if (user.getId_perfil()!=0){
            if(s.contentEquals("from User where ")){
                s=s+" id_perfil="+user.getId_perfil();
            }else{
                s=s+" and id_perfil="+user.getId_perfil();
            }
        }
        if (user.getNombre()!=null){
            if (s.contentEquals("from User where ")){
                s=s+"nombre like '"+user.getNombre()+"'";
            }else{
                s=s+" and nombre like '"+user.getNombre()+"'";
            }
        }
        if (user.getApellido1()!=null){
            if (s.contentEquals("from User where ")){
                s=s+" apellido1 like '"+user.getApellido1()+"'";
            }else {
                s=s+" and apellido1 like '"+user.getApellido1()+"'";
            }
        }
        if (user.getApellido2()!=null){
            if (s.contentEquals("from User where ")){
                s=s+" apellido2 like '"+user.getApellido2()+"'";
            }else {
                s=s+" and apellido2 like '"+user.getApellido2()+"'";
            }
        }
        Query<User> theQuery = currentSession.createQuery(s, User.class);
        List<User> users = theQuery.getResultList();
        return users;
    }

    /*Esta función sirve para guardar o actualizar los datos de un usuario*/
    @Override
    @Transactional
    public void save(User user) {
        Session currentSession = entityManager.unwrap(Session.class);
        currentSession.saveOrUpdate(user);
    }

    /*Esta función elimina un usuario cuyo código le pasemos por parámetro*/
    @Override
    @Transactional
    public void deleteById(int id) {
        Session currentSession = entityManager.unwrap(Session.class);
        Query<User> theQuery = currentSession.createQuery("delete from User where id=:idUser");
        theQuery.setParameter("idUser", id);
        theQuery.executeUpdate();
    }


}