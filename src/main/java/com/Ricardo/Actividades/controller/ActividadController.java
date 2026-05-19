/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.Ricardo.Actividades.controller;

import com.Ricardo.Actividades.model.Actividad;
import com.Ricardo.Actividades.model.Alumno;
import com.Ricardo.Actividades.service.ActividadService;
import com.Ricardo.Actividades.service.AlumnoDetailsService;
import java.security.Principal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

/**
 *
 * @author Ricardo
 */
@Controller
public class ActividadController {

    @Autowired
    private ActividadService actividadService;
    
    @Autowired
    private AlumnoDetailsService alumnoDetailsService;

    @GetMapping("/misActividades")
    public String verActividades(Model model, Principal principal) {

        String login = principal.getName();

        model.addAttribute("listaActividades", actividadService.listarPorAlumno(login));
        return "misActividades";

    }

    @GetMapping("/nuevaActividad")
    public String nuevaActividad(Model model) {

        model.addAttribute("actividad", new Actividad());

        return "nuevaActividad";

    }
    
    @PostMapping("/guardarActividad")
    public String guardarActividad(
        @ModelAttribute Actividad actividad,
        Principal principal) {

            Alumno alumno = alumnoDetailsService.buscarPorLogin(principal.getName());

            actividad.setAlumno(alumno);

            actividad.setEstado("Pendiente");

            actividadService.guardarActividad(actividad);

            return "redirect:/misActividades";

    }
    
    @GetMapping("/verActividadesAlumno/{matricula}")
    public String verActividadesAlumno(
        @PathVariable String matricula, Model model) {
        
        model.addAttribute("listaActividades", actividadService.listarPorMatricula(matricula));
        
        return "actividadesAlumno";

    }

}
