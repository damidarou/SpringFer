package com.nigmacode.apirest.entity;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.util.Date;
import java.util.List;

import javax.persistence.*;

@Entity
@Table(name = "caso_uso")
public class CasoUso {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="cod_caso_uso")
    private Integer cod_caso_uso;

    @Column(name="nombre_caso_uso")
    private String nombre;

    @Column(name="descripcion")
    private String descripcion;

    @Column(name="fecha_creacion_caso_uso", updatable = false)
    @CreationTimestamp
    private Date fecha_creacion_caso_uso;

    @Column(name="fecha_modificacion_caso_uso")
    @UpdateTimestamp
    private Date fecha_modificacion_caso_uso;

    @Column(name="cod_proyecto")
    private Integer id_proyecto;

    @Column(name="cod_usuario")
    private Integer cod_usuario;

    @OneToMany(mappedBy = "caso_uso")
    @JsonIgnoreProperties("caso_uso")
    private List<Test> tests;


    @OneToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    @JoinColumn(name = "cod_proyecto", referencedColumnName = "cod_proyecto", insertable=false, updatable=false)
    @JsonIgnoreProperties("caso_usos")
    private Proyecto proyecto;




    public CasoUso(){}

    public CasoUso(Integer cod_caso_uso, String nombre, String descripcion, Integer id_proyecto, Integer cod_usuario,
                   Date fecha_creacion_caso_uso, Date fecha_modificacion_caso_uso) {
        this.cod_caso_uso = cod_caso_uso;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.id_proyecto = id_proyecto;
        this.cod_usuario = cod_usuario;
        this.fecha_creacion_caso_uso = fecha_creacion_caso_uso;
        this.fecha_modificacion_caso_uso = fecha_modificacion_caso_uso;
    }


    public List<Test> getTests() {
        return tests;
    }

    public void setTests(List<Test> tests) {
        this.tests = tests;
    }

    public Integer getCod_caso_uso() {
        return cod_caso_uso;
    }

    public void setCod_caso_uso(Integer cod_caso_uso) {
        this.cod_caso_uso = cod_caso_uso;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
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

    public Integer getId_proyecto() {
        return id_proyecto;
    }

    public void setId_proyecto(Integer cod_proyecto) {
        this.id_proyecto = cod_proyecto;
    }

    public Integer getCod_usuario() {
        return cod_usuario;
    }

    public void setCod_usuario(Integer cod_usuario) {
        this.cod_usuario = cod_usuario;
    }

    @Override
    public String toString() {
        return "User{" +
                "cod_caso_uso=" + cod_caso_uso +
                ", nombre='" + nombre + '\'' +
                ", descripcion='" + descripcion + '\'' +
                ", fecha_creacion_caso_uso=" + fecha_creacion_caso_uso +
                ", fecha_modificacion=" + fecha_modificacion_caso_uso +
                ", cod_proyecto=" + id_proyecto +
                ", cod_usuario=" + cod_usuario +
                '}';
    }

}