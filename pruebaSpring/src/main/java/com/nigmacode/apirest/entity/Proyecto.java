package com.nigmacode.apirest.entity;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.*;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

//Proyecto pre dynamic query
//Proyecto post dynamic query
@Entity
@Table(name="proyecto")

public class Proyecto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cod_proyecto")
    private int cod_proyecto;

    @Column(name = "nombre_proyecto")
    private String nombre_proyecto;

    @Column(name = "fecha_creacion_proyecto", updatable = false)
    @CreationTimestamp
    private Date fecha_creacion_proyecto;

    @Column(name = "fecha_modificacion")
    @UpdateTimestamp
    private Date fecha_modificacion;

    @Column(name = "version_proyecto")
    private double version_proyecto;

    @Column(name = "cod_usuario")
    private int cod_usuario;

    //@OneToMany(mappedBy = "Project", cascade = CascadeType.ALL)
    //Set<Proyecto> proyectoSet = new HashSet<Proyecto>();

    /*@OneToOne(mappedBy = "proyecto")
    private Caso_uso caso_uso;
    */
    //private Test test;

    public Proyecto() {
    }

    public Proyecto(int cod_proyecto, String nombre_proyecto,
                    Date fecha_creacion_proyecto, Date fecha_modificacion,
                    double version_proyecto, int cod_usuario) {
        this.cod_proyecto = cod_proyecto;
        this.nombre_proyecto = nombre_proyecto;
        this.fecha_creacion_proyecto = fecha_creacion_proyecto;
        this.fecha_modificacion = fecha_modificacion;
        this.version_proyecto = version_proyecto;
        this.cod_usuario = cod_usuario;
    }

    public int getCod_proyecto() {
        return cod_proyecto;
    }

    public void setCod_proyecto(int cod_proyecto) {
        this.cod_proyecto = cod_proyecto;
    }

    public String getNombre_proyecto() {
        return nombre_proyecto;
    }

    public void setNombre_proyecto(String nombre_proyecto) {
        this.nombre_proyecto = nombre_proyecto;
    }

    public Date getFecha_creacion_proyecto() {
        return fecha_creacion_proyecto;
    }

    public void setFecha_creacion_proyecto(Date fecha_creacion_proyecto) {
        this.fecha_creacion_proyecto = fecha_creacion_proyecto;
    }

    public Date getFecha_modificacion() {
        return fecha_modificacion;
    }

    public void setFecha_modificacion(Date fecha_modificacion) {
        this.fecha_modificacion = fecha_modificacion;
    }

    public double getVersion_proyecto() {
        return version_proyecto;
    }

    public void setVersion_proyecto(double version_proyecto) {
        this.version_proyecto = version_proyecto;
    }

    public int getCod_usuario() {
        return cod_usuario;
    }

    public void setCod_usuario(int cod_usuario) {
        this.cod_usuario = cod_usuario;
    }


    @Override
    public String toString() {
        return "Proyecto{" +
                "cod_proyecto=" + cod_proyecto +
                ", nombre_proyecto='" + nombre_proyecto + '\'' +
                ", fecha_creacion_proyecto=" + fecha_creacion_proyecto +
                ", fecha_modificacion=" + fecha_modificacion +
                ", version_proyecto=" + version_proyecto +
                ", cod_usuario=" + cod_usuario +
                '}';
    }
}