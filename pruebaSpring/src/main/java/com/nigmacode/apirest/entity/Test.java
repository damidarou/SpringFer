package com.nigmacode.apirest.entity;

import org.hibernate.annotations.ForeignKey;
import org.jetbrains.annotations.NotNull;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;



@Entity
@Table(name="test")

public class Test {

//Crea las columnas de la tabla test, indicando el tipo de valor que van a almacenar
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="cod_test")
    private int cod_test;

    @Column(name="nombre")
    private String nombre;

    @Column(name="objetivo")
    private String objetivo;

    @Column(name="estado")
    private String estado;
//ESTADO es un enum, solo admite los valores (PASSED, FAILED, BLOCKED, UNTESTED)

    @Column(name="cod_caso_uso")
    private int cod_caso_uso;

    @Column(name="cod_usuario")
    private int cod_usuario;

    public Test(){}

    //Constructor de Test usando como parametros todas las columnas creadas
    public Test(int cod_test, String nombre, String objetivo, int cod_caso_uso, int cod_usuario, String estado) {
        this.cod_test = cod_test;
        this.nombre = nombre;
        this.objetivo = objetivo;
        this.cod_caso_uso = cod_caso_uso;
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

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public int getCod_caso_uso() {
        return cod_caso_uso;
    }

    public void setCod_caso_uso(int cod_caso_uso) {
        this.cod_caso_uso = cod_caso_uso;
    }

    public int getCod_usuario() {
        return cod_usuario;
    }

    public void setCod_usuario(int cod_usuario) {
        this.cod_usuario = cod_usuario;
    }

    @Override
    public String toString() {
        return "Test{" +
                "cod_test=" + cod_test +
                ", nombre='" + nombre + '\'' +
                ", objetivo='" + objetivo + '\'' +
                ", estado='" + estado + '\'' +
                ", cod_caso_uso=" + cod_caso_uso +
                ", cod_usuario=" + cod_usuario +
                '}';
    }
}
