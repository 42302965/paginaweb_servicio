/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.Ricardo.Actividades.controller;

import com.Ricardo.Actividades.model.Alumno;
import com.Ricardo.Actividades.model.Maestro;
import com.Ricardo.Actividades.repository.AlumnoRepository;
import com.Ricardo.Actividades.repository.MaestroRepository;
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
public class MaestroController {

    @Autowired
    private AlumnoRepository alumnoRepository;

    @Autowired
    private MaestroRepository maestroRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @GetMapping("/listaAlumnos")
    public String listaAlumnos(Model model) {

        model.addAttribute(
                "alumnos", alumnoRepository.findAll()
        );

        return "listaAlumnos";

    }

    @GetMapping("/nuevoAlumno")
    public String nuevoAlumno(Model model) {

        model.addAttribute(
                "alumno",
                new Alumno()
        );

        return "nuevoAlumno";

    }

    @PostMapping("/guardarAlumno")
    public String guardarAlumno(
            @ModelAttribute Alumno alumno) {
        alumno.setContrasena(passwordEncoder.encode(alumno.getContrasena()));

        Maestro maestro = maestroRepository.findById("1001").orElse(null);
        alumno.setMaestro(maestro);
        alumnoRepository.save(alumno);
        return "redirect:/listaAlumnos";
    }

    @GetMapping("/editarAlumno/{id}")
    public String editarAlumno(
            @PathVariable("id") String matricula,
            Model model
    ) {

        Alumno alumno
                = alumnoRepository.findById(matricula)
                        .orElse(null);

        model.addAttribute(
                "alumno",
                alumno
        );

        return "editarAlumno";

    }

    @PostMapping("/actualizarAlumno")
    public String actualizarAlumno(
            @ModelAttribute Alumno alumno) {

        alumno.setContrasena(
                passwordEncoder.encode(
                        alumno.getContrasena()
                )
        );

        Maestro maestro = maestroRepository.findById("1001").orElse(null);
        alumno.setMaestro(maestro);
        alumnoRepository.save(alumno);
        return "redirect:/listaAlumnos";
    }

    @GetMapping("/eliminarAlumno/{id}")
    public String eliminarAlumno(
            @PathVariable("id") String matricula) {
        alumnoRepository.deleteById(matricula);
        return "redirect:/listaAlumnos";

    }

}
