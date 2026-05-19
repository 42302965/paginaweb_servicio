package com.Ricardo.Actividades.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "maestro")
public class Maestro {

    @Id
    @Column(name = "mae_id")
    private String id;

    @Column(name = "mae_login")
    private String login;

    @Column(name = "mae_contrasena")
    private String contrasena;

    @Column(name = "mae_nombre")
    private String nombre;

    @Column(name = "mae_apellido")
    private String apellido;

    public Maestro() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

}