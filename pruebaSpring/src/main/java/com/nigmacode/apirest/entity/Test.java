package com.nigmacode.apirest.entity;

import org.hibernate.annotations.ForeignKey;
import org.jetbrains.annotations.NotNull;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.*;


@Entity
@Table(name="test")

public class Test {

    //Crea las columnas de la tabla test, indicando el tipo de valor que van a almacenar
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    @Column(name="cod_test")
    private int cod_test;

    @Column(name="nombre")
    private String nombre;

    @Column(name="objetivo")
    private String objetivo;

    @Column(name="estado")
    private estado estado;
//ESTADO es un enum, solo admite los valores (PASSED, FAILED, BLOCKED, UNTESTED)

    @Column(name="id_caso_uso")
    private int id_caso_uso;

    @Column(name="cod_usuario")
    private int cod_usuario;

/*
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_caso_uso", referencedColumnName = "cod_caso_uso", insertable=false, updatable=false)
    private Caso_uso caso_uso;
 */

   // private Proyecto proyecto;

   @ManyToOne
   @JoinColumn(name = "fk_cod_caso_uso")
   private Caso_uso caso_Uso;

    public Test(){}

    //Constructor de Test usando como parametros todas las columnas creadas
    public Test(int cod_test, String nombre, String objetivo, int id_caso_uso, int cod_usuario, estado estado) {
        this.cod_test = cod_test;
        this.nombre = nombre;
        this.objetivo = objetivo;
        this.id_caso_uso = id_caso_uso;
        this.cod_usuario = cod_usuario;
        this.estado = estado;
    }

    //Estos son los getters, setters y el toString que se generaron para cada columna
    public int getCod_test() {
        return cod_test;
    }

    public void setCod_test(int cod_test) {
        this.cod_test = cod_test;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) { this.nombre = nombre; }

    public String getObjetivo() {
        return objetivo;
    }

    public void setObjetivo(String objetivo) {
        this.objetivo = objetivo;
    }

    public estado getEstado() {
        return estado;
    }

    public void setEstado(estado estado) {
        this.estado = estado;
    }

    public int getId_caso_uso() {
        return id_caso_uso;
    }

    public void setId_caso_uso(int id_caso_uso) {
        this.id_caso_uso = id_caso_uso;
    }

    public int getCod_usuario() {
        return cod_usuario;
    }

    public void setCod_usuario(int cod_usuario) {
        this.cod_usuario = cod_usuario;
    }

    public int getCaso_uso(){return  id_caso_uso;}
    @Override
    public String toString() {
        return "Test{" +
                "cod_test=" + cod_test +
                ", nombre='" + nombre + '\'' +
                ", objetivo='" + objetivo + '\'' +
                ", estado='" + estado + '\'' +
                ", cod_caso_uso=" + id_caso_uso +
                ", cod_usuario=" + cod_usuario +
                '}';
    }


}
