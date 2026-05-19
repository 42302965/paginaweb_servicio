/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.Ricardo.Actividades.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

/**
 *
 * @author Ricardo
 */
@Entity
@Table(name = "categoria")
public class Categoria {

    @Id
    @Column(name = "cat_num")
    private String num;

    @Column(name = "cat_nombre")
    private String nombre;

    public Categoria() {
    }

    public String getNum() {
        return num;
    }

    public void setNum(String num) {
        this.num = num;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    @Override
    public String toString() {
    return num;
}
}
