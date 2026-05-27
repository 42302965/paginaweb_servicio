/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.Ricardo.Actividades.model;

import jakarta.persistence.*;

/**
 *
 * @author Ricardo
 */

@Entity
@Table(name="alumno")
public class Alumno {

    @Id
    @Column(name="alu_matricula")
    private String matricula;

    @Column(name="alu_login")
    private String login;

    @Column(name="alu_contrasena")
    private String contrasena;

    @Column(name="alu_nombre")
    private String nombre;

    @Column(name="alu_apellido")
    private String apellido;

    @ManyToOne
    @JoinColumn(name="mae_id")
    private Maestro maestro;

    public Alumno() {
    }

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public Maestro getMaestro() {
    return maestro;
}

    public void setMaestro(Maestro maestro) {
        this.maestro = maestro;
    }

}
