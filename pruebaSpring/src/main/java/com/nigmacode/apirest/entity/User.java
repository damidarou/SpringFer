package com.nigmacode.apirest.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name="usuario")
public class User {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="cod_usuario")
    private int cod_usaurio;
    @Column(name = "username")
    private String username;
    @Column(name="contraseña")
    private String contraseña;

    @Column(name="id_perfil",insertable = false,updatable = false)
    private int cod_perfil;

    @Column(name="active")
    private boolean active;
    @Column(name="nombre")
    private String nombre;
    @Column(name="apellido1")
    private String apellido1;

    public List<Proyecto> getProyectos() {
        return proyectos;
    }

    public void setProyectos(List<Proyecto> proyectos) {
        this.proyectos = proyectos;
    }

    @Column(name="apellido2")
    private String apellido2;

    @OneToMany(mappedBy = "usuario")
    @JsonIgnoreProperties("usuarios")
    List<Proyecto> proyectos;

    @ManyToOne(optional = false)
    @JoinColumn(name = "id_perfil")
    @JsonIgnoreProperties("usuarios")
    private Perfil perfil;


    public Perfil getPerfil() {
        return perfil;
    }

    public void setPerfil(Perfil perfil) {
        this.perfil = perfil;
    }

    public User() {}

    public User(int cod_usaurio,String username,String contraseña,String nombre,String apellido1,String apellido2) {
        this.cod_usaurio = cod_usaurio;
        this.username = username;
        this.contraseña=contraseña;
        this.cod_perfil =0;
        this.nombre=nombre;
        this.apellido1=apellido1;
        this.apellido2=apellido2;
    }

    public String getUsername() {
        return username;
    }

    public int getCod_perfil() {
        return cod_perfil;
    }

    public void setCod_perfil(int cod_perfil) {
        this.cod_perfil = cod_perfil;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido1() {
        return apellido1;
    }

    public void setApellido1(String apellido1) {
        this.apellido1 = apellido1;
    }

    public String getApellido2() {
        return apellido2;
    }

    public void setApellido2(String apellido2) {
        this.apellido2 = apellido2;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getCod_usaurio() {
        return cod_usaurio;
    }

    public void setCod_usaurio(int cod_usaurio) {
        this.cod_usaurio = cod_usaurio;
    }

    public String getContraseña() {
        return contraseña;
    }

    public void setContraseña(String contraseña) {
        this.contraseña = contraseña;
    }

    @Override
    public String toString() {
        return "User{" +
                "cod_usaurio=" + cod_usaurio +
                ", username='" + username + '\'' +
                ", contraseña='" + contraseña + '\'' +
                ", id_perfil=" + cod_perfil +
                ", active=" + active +
                ", nombre='" + nombre + '\'' +
                ", apellido1='" + apellido1 + '\'' +
                ", apellido2='" + apellido2 + '\'' +
                '}';
    }
}