package com.nigmacode.apirest.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.ForeignKey;
import org.hibernate.annotations.UpdateTimestamp;
import org.jetbrains.annotations.NotNull;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


import javax.persistence.*;
@Entity
@Table(name="ejecutar")
public class Ejecutar {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="cod_ejecuta")
    private int cod_ejecuta;

    @Column(name="cod_usuario")
    private int id_user;

    @Column(name="cod_test")
    private int id_test;

    @Column(name = "fecha_modificacion_test")
    @UpdateTimestamp
    private Date fecha_modificacion_test;

    @Column(name = "resultado")
    private estado resultado;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cod_usuario", referencedColumnName = "cod_usuario", insertable=false, updatable=false)
    @JsonIgnoreProperties("user")
    private User user;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cod_test",referencedColumnName = "cod_test",insertable = false,updatable = false)
    @JsonIgnoreProperties("ejecutarList")
    private Test test;
    public Ejecutar() {

    }

    public Ejecutar(int cod_ejecuta, int id_user, int id_test, Date fecha_modificacion_test, estado resultado) {
        this.cod_ejecuta = cod_ejecuta;
        this.id_user = id_user;
        this.id_test = id_test;
        this.fecha_modificacion_test = fecha_modificacion_test;
        this.resultado = resultado;
    }

    public int getCod_ejecuta() {
        return cod_ejecuta;
    }

    public void setCod_ejecuta(int cod_ejecuta) {
        this.cod_ejecuta = cod_ejecuta;
    }

    public int getId_user() {
        return id_user;
    }

    public void setId_user(int id_user) {
        this.id_user = id_user;
    }

    public int getId_test() {
        return id_test;
    }

    public void setId_test(int id_test) {
        this.id_test = id_test;
    }

    public Date getFecha() {
        return fecha_modificacion_test;
    }

    public void setFecha(Date fecha) {
        this.fecha_modificacion_test = fecha_modificacion_test;
    }

    public estado getResultado() {
        return resultado;
    }

    public void setResultado(estado resultado) {
        this.resultado = resultado;
    }

    @Override
    public String toString() {
        return "Ejecutar{" +
                "cod_ejecuta=" + cod_ejecuta +
                ", id_user=" + id_user +
                ", id_test=" + id_test +
                ", fecha_modificacion_test=" + fecha_modificacion_test +
                ", resultado=" + resultado +
                '}';
    }
}
