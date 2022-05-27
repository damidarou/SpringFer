package com.nigmacode.apirest.entity;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.ForeignKey;
import org.hibernate.annotations.UpdateTimestamp;
import org.jetbrains.annotations.NotNull;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.*;

@Entity
@Table(name = "caso_uso")
public class Caso_uso {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="cod_caso_uso")
    private int cod_caso_uso;

    @Column(name="nombre_caso_uso")
    private String nombre_caso_uso;

    @Column(name="descripcion")
    private String descripcion;

    @Column(name="fecha_creacion_caso_uso", updatable = false)
    @CreationTimestamp
    private Date fecha_creacion_caso_uso;

    @Column(name="fecha_modificacion_caso_uso")
    @UpdateTimestamp
    private Date fecha_modificacion_caso_uso;

    @Column(name="id_proyecto")
    private int id_proyecto;

    @Column(name="cod_usuario")
    private int cod_usuario;
/*
    @OneToOne(mappedBy = "caso_uso")
    private Test test;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_proyecto", referencedColumnName = "cod_proyecto", insertable=false, updatable=false)

    private Proyecto proyecto;
*/
    /*
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "cod_proyecto", referencedColumnName = "cod_proyecto")
    private Proyecto proyecto;
*/
    public Caso_uso(){}

    public Caso_uso(int cod_caso_uso, String nombre_caso_uso, String descripcion, int id_proyecto, int cod_usuario, Date fecha_creacion_caso_uso, Date fecha_modificacion_caso_uso) {
        this.cod_caso_uso = cod_caso_uso;
        this.nombre_caso_uso = nombre_caso_uso;
        this.descripcion = descripcion;
        this.id_proyecto = id_proyecto;
        this.cod_usuario = cod_usuario;
        this.fecha_creacion_caso_uso = fecha_creacion_caso_uso;
        this.fecha_modificacion_caso_uso = fecha_modificacion_caso_uso;
    }

    public int getCod_caso_uso() {
        return cod_caso_uso;
    }

    public void setCod_caso_uso(int cod_caso_uso) {
        this.cod_caso_uso = cod_caso_uso;
    }

    public String getNombre_caso_uso() {
        return nombre_caso_uso;
    }

    public void setNombre_caso_uso(String nombre_caso_uso) {
        this.nombre_caso_uso = nombre_caso_uso;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Date getFecha_creacion_caso_uso() {
        return fecha_creacion_caso_uso;
    }

    public void setFecha_creacion_caso_uso(Date fecha_creacion_caso_uso) {
        this.fecha_creacion_caso_uso = fecha_creacion_caso_uso;
    }

    public Date getFecha_modificacion_caso_uso() {
        return fecha_modificacion_caso_uso;
    }

    public void setFecha_modificacion_caso_uso(Date fecha_modificacion_caso_uso) {
        this.fecha_modificacion_caso_uso = fecha_modificacion_caso_uso;
    }

    public int getId_proyecto() {
        return id_proyecto;
    }

    public void setCod_proyecto(int cod_proyecto) {
        this.id_proyecto = cod_proyecto;
    }

    public int getCod_usuario() {
        return cod_usuario;
    }

    public void setCod_usuario(int cod_usuario) {
        this.cod_usuario = cod_usuario;
    }

    @Override
    public String toString() {
        return "User{" +
                "cod_caso_uso=" + cod_caso_uso +
                ", nombre_caso_uso='" + nombre_caso_uso + '\'' +
                ", descripcion='" + descripcion + '\'' +
                ", fecha_creacion_caso_uso=" + fecha_creacion_caso_uso +
                ", fecha_modificacion=" + fecha_modificacion_caso_uso +
                ", cod_proyecto=" + id_proyecto +
                ", cod_usuario=" + cod_usuario +
                '}';
    }

    @OneToMany(mappedBy = "caso_Uso", cascade = CascadeType.ALL)
    Set<Caso_uso> caso_usoSet = new HashSet<Caso_uso>();

    @ManyToOne
    @JoinColumn(name = "fk_proyecto")
    private Proyecto Project;
}
