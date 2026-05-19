/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.Ricardo.Actividades.model;

import jakarta.persistence.*;
import java.util.Date;
import java.time.LocalDate;

/**
 *
 * @author Ricardo
 */
@Entity
@Table(name="actividad")
public class Actividad {
 
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="act_id")
    private Integer id;

    @Column(name = "act_fecha_inicio")
    private LocalDate fechaInicio;

    @Column(name = "act_fecha_fin")
    private LocalDate fechaFin;

    @Column(name="act_descripcion")
    private String descripcion;

    @Column(name="act_estado")
    private String estado;

    @Column(name="act_horas")
    private int horas;

    @ManyToOne
    @JoinColumn(name = "cat_num")
    private Categoria categoria;

    @ManyToOne
    @JoinColumn(name="alu_matricula")
    private Alumno alumno;

    @ManyToOne
    @JoinColumn(name="mae_id")
    private Maestro maestro;

    @Column(name = "act_comentario")
    private String comentario;

    public Actividad() {
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    public void setAlumno(Alumno alumno) {
        this.alumno = alumno;
    }

    public Alumno getAlumno() {
        return alumno;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public LocalDate getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(LocalDate fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public LocalDate getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(LocalDate fechaFin) {
        this.fechaFin = fechaFin;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public int getHoras() {
        return horas;
    }

    public void setHoras(int horas) {
        this.horas = horas;
    }

    public Maestro getMaestro() {
        return maestro;
    }

    public void setMaestro(Maestro maestro) {
        this.maestro = maestro;
    }

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }
    
    
}
