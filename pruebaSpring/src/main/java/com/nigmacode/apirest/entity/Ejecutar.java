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

    @Column(name="id_user")
    private int id_user;

    @Column(name="id_test")
    private int id_test;

    @Column(name = "fecha")
    @UpdateTimestamp
    private Date fecha;

    @Column(name = "resultado")
    private int resultado;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_user", referencedColumnName = "cod_usuario", insertable = false, updatable = false)
    private User users2;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_test", referencedColumnName = "cod_test", insertable = false, updatable = false)
    private Test tests2;

    public Ejecutar() {

    }

    public Ejecutar(int cod_ejecuta, int id_user, int id_test, Date fecha, int resultado) {
        this.cod_ejecuta = cod_ejecuta;
        this.id_user = id_user;
        this.id_test = id_test;
        this.fecha = fecha;
        this.resultado = resultado;
    }

    public User getUsers2() {
        return users2;
    }

    public void setUsers2(User users2) {
        this.users2 = users2;
    }

    public Test getTests2() {
        return tests2;
    }

    public void setTests2(Test tests2) {
        this.tests2 = tests2;
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
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public int getResultado() {
        return resultado;
    }

    public void setResultado(int resultado) {
        this.resultado = resultado;
    }

    @Override
    public String toString() {
        return "Ejecutar{" +
                "cod_ejecuta=" + cod_ejecuta +
                ", id_user=" + id_user +
                ", id_test=" + id_test +
                ", fecha=" + fecha +
                ", resultado=" + resultado +
                '}';
    }
}
