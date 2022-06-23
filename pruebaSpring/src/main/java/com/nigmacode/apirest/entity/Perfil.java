package com.nigmacode.apirest.entity;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name="perfil")
public class Perfil {
    @Id
    @GeneratedValue
    @Column(name="id_perfil")
    private int id_perfil;
    @Column(name="nombre")
    private String nombre;
    @Column(name="permisos")
    private String permisos;

/*
    @OneToMany(mappedBy = "User", fetch = FetchType.LAZY, orphanRemoval = false)
    private List<User> users = new ArrayList<>();
 */

    @OneToMany(mappedBy = "perfil", cascade = CascadeType.ALL,orphanRemoval = true,fetch = FetchType.LAZY)
    @JsonIgnoreProperties("perfil")
    private List<User> usuarios;

    public Perfil(){}

    public Perfil(int id_perfil, String nombre, String permisos) {
        this.id_perfil = id_perfil;
        this.nombre = nombre;
        this.permisos = permisos;
    }

    public List<User> getUsuarios() {
        return usuarios;
    }

    public void setUsuarios(List<User> usuarios) {
        this.usuarios = usuarios;
    }

    public int getId_perfil() {
        return id_perfil;
    }

    public void setId_perfil(int id_perfil) {
        this.id_perfil = id_perfil;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getPermisos() {
        return permisos;
    }

    public void setPermisos(String permisos) {
        this.permisos = permisos;
    }

    @Override
    public String toString() {
        return "Perfil{" +
                "id_perfil=" + id_perfil +
                ", nombre='" + nombre + '\'' +
                ", permisos='" + permisos + '\'' +
                '}';
    }
}
