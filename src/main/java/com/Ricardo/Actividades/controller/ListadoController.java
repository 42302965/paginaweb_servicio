/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.Ricardo.Actividades.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @author Ricardo
 */

//URLs de inicio
@Controller
public class ListadoController {

    @RequestMapping("/")
    public String elegirLogin() {
        //elegir login maestro o alumno
        return "elegirlogin";
    }

    @RequestMapping("/alumno")
    public String alumno() {
        return "alumno";
    }

    @RequestMapping("/maestro")
    public String maestro() {
        return "maestro";
    }

    @RequestMapping("/alumno/inicioAlumno")
    public String inicioAlumno() {
        return "inicioAlumno";
    }

    @RequestMapping("/maestro/inicioMaestro")
    public String inicioMaestro() {
        return "inicioMaestro";

    }
}
