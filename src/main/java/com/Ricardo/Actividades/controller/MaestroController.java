/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.Ricardo.Actividades.controller;

import com.Ricardo.Actividades.model.Alumno;
import com.Ricardo.Actividades.model.Maestro;
import com.Ricardo.Actividades.model.Evidencia;
import com.Ricardo.Actividades.service.EvidenciaService;
import com.Ricardo.Actividades.service.ActividadService;
import com.Ricardo.Actividades.service.AlumnoService;
import com.Ricardo.Actividades.service.MaestroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

/**
 *
 * @author Ricardo
 */
@Controller
@RequestMapping("/maestro")
public class MaestroController {
    
    @Autowired
    private AlumnoService alumnoService;

    @Autowired
    private MaestroService maestroService;
    // Encriptador de contraseñas
    @Autowired
    private PasswordEncoder passwordEncoder;
    
    @Autowired
    private EvidenciaService evidenciaService;

    @GetMapping("/listaAlumnos")
    public String listaAlumnos(Model model) {
        
        model.addAttribute("alumnos", alumnoService.listarTodos());

        return "listaAlumnos";

    }

    @GetMapping("/nuevoAlumno")
    public String nuevoAlumno(Model model) {
        // Crear objeto alumno vacio
        model.addAttribute("alumno", new Alumno());

        return "nuevoAlumno";

    }

    @PostMapping("/guardarAlumno")
    public String guardarAlumno(
            @ModelAttribute Alumno alumno) {
        // Encriptar contraseña
        alumno.setContrasena(passwordEncoder.encode(alumno.getContrasena()));
        // Buscar al maestro principal
        Maestro maestro = maestroService.buscarPorId("1001");
        // Asignar maestro al alumno
        alumno.setMaestro(maestro);
        // Se guarda al alumno
        alumnoService.guardarAlumno(alumno);
        return "redirect:/maestro/listaAlumnos";
    }

    @GetMapping("/editarAlumno")
    public String editarAlumno(
            @RequestParam("id") String matricula, Model model) {
        // Buscar alumno por matrícula
        Alumno alumno = alumnoService.buscarPorId(matricula);
        // Enviar alumno a la vista
        model.addAttribute("alumno",alumno);
        return "editarAlumno";

    }
    //actualizar al alumno
    @PostMapping("/actualizarAlumno")
    public String actualizarAlumno(
            @ModelAttribute Alumno alumno) {
        // Encriptar nueva contraseña
        alumno.setContrasena(passwordEncoder.encode(alumno.getContrasena()));
        Maestro maestro = maestroService.buscarPorId("1001");
        alumno.setMaestro(maestro);
        alumnoService.guardarAlumno(alumno);
        return "redirect:/maestro/listaAlumnos";
    }

    @GetMapping("/eliminarAlumno")
    public String eliminarAlumno(
            @RequestParam("id") String matricula) {
        // Obtener la id (matricula) para borrar al alumno
        actividadService.eliminarPorMatricula(matricula);
        alumnoService.eliminarPorId(matricula);
        return "redirect:/maestro/listaAlumnos";
    }

    @Autowired
    private ActividadService actividadService;
    
    @GetMapping("/verActividadesAlumno")
    public String verActividadesAlumno(
            @RequestParam String matricula, Model model) {
        //lista de actividades de los alumnos
        model.addAttribute("listaActividades", actividadService.listarPorMatricula(matricula));
        return "actividadesAlumno";
    }
    
    
    @GetMapping("/verEvidencias")
    public String verEvidencias(
            @RequestParam Integer id, Model model) {
        // Mostrar evidencias por la id (matricula)
        model.addAttribute("listaEvidencias", evidenciaService.listarPorActividad(id));
        model.addAttribute("actividadId", id);
        return "evidencias";

    }
    
}
