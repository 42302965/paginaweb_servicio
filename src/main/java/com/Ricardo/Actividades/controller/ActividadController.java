/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.Ricardo.Actividades.controller;

import com.Ricardo.Actividades.model.Actividad;
import com.Ricardo.Actividades.model.Alumno;
import com.Ricardo.Actividades.model.Evidencia;
import com.Ricardo.Actividades.repository.CategoriaRepository;
import com.Ricardo.Actividades.service.ActividadService;
import com.Ricardo.Actividades.service.AlumnoDetailsService;
import com.Ricardo.Actividades.service.EvidenciaService;
import java.security.Principal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @author Ricardo
 */
@Controller
@RequestMapping("/alumno")
public class ActividadController {

    @Autowired
    private ActividadService actividadService;

    @Autowired
    private AlumnoDetailsService alumnoDetailsService;

    @Autowired
    private CategoriaRepository categoriaRepository;

    @Autowired
    private EvidenciaService evidenciaService;

    // Mostrar las actividades del alumno
    @GetMapping("/misActividades")
    public String verActividades(Model model, Principal principal) {

        // Obtener login del usuario autenticado
        String login = principal.getName();

        // Enviar lista de actividades a la vista
        model.addAttribute("listaActividades", actividadService.listarPorAlumno(login));

        return "misActividades";

    }

    // Mostrar formulario de nueva actividad
    @GetMapping("/nuevaActividad")
    public String nuevaActividad(Model model) {

        // Crear objeto actividad vacío
        model.addAttribute("actividad", new Actividad());

        // Enviar lista de categorías
        model.addAttribute("listaCategorias", categoriaRepository.findAll());

        return "nuevaActividad";

    }

    // Guardar actividad nueva
    @PostMapping("/guardarActividad")
    public String guardarActividad(
            @ModelAttribute Actividad actividad,
            @RequestParam("archivos") MultipartFile[] archivos,
            Principal principal) throws Exception {

        // Buscar alumno autenticado
        Alumno alumno = alumnoDetailsService.buscarPorLogin(principal.getName());
        // Asignar alumno a la actividad
        actividad.setAlumno(alumno);
        // Estado inicial de la actividad
        actividad.setEstado("Pendiente");
        // Guardar actividad
        actividadService.guardarActividad(actividad);
        // Recorrer archivos enviados
        for (MultipartFile archivo : archivos) {
            if (!archivo.isEmpty()) {
                // Obtener nombre del archivo
                String nombreArchivo = archivo.getOriginalFilename();
                // Ruta donde se guardará la imagen
                Path ruta = Paths.get("src/main/resources/static/uploads/" + nombreArchivo);
                // Guardar archivo físicamente
                Files.write(ruta, archivo.getBytes());
                // Crear evidencia
                Evidencia evidencia = new Evidencia();
                evidencia.setNombre(nombreArchivo);
                evidencia.setActividad(actividad);
                // Guardar evidencia en BD
                evidenciaService.guardar(evidencia);
            }
        }
        return "redirect:/alumno/misActividades";
    }

    // Mostrar actividades de un alumno específico
    @GetMapping("/verActividadesAlumno")
    public String verActividadesAlumno(
            @RequestParam String matricula, Model model) {
        // Enviar lista de actividades
        model.addAttribute("listaActividades", actividadService.listarPorMatricula(matricula));
        return "actividadesAlumno";
    }

    // Mostrar evidencias de una actividad
    @GetMapping("/verEvidencias")
    public String verEvidencias(
            @RequestParam Integer id, Model model) {
        // Enviar lista de evidencias
        model.addAttribute("listaEvidencias", evidenciaService.listarPorActividad(id));
        // Enviar id de la actividad
        model.addAttribute("actividadId", id);
        return "evidencias";
    }

    // Mostrar formulario para agregar evidencias
    @GetMapping("/agregarEvidencias")
    public String agregarEvidencias(
            @RequestParam Integer id, Model model) {
        // Buscar actividad
        Actividad actividad = actividadService.buscarPorId(id);
        // Enviar actividad a la vista
        model.addAttribute("actividad", actividad);
        return "agregarEvidencias";
    }

    // Guardar evidencias nuevas
    @PostMapping("/guardarEvidencias")
    public String guardarEvidencias(
            @RequestParam Integer id,
            @RequestParam("archivos") MultipartFile[] archivos)
            throws Exception {
        // Buscar actividad
        Actividad actividad = actividadService.buscarPorId(id);
        // Recorrer archivos enviados
        for (MultipartFile archivo : archivos) {
            if (!archivo.isEmpty()) {
                // Obtener nombre del archivo
                String nombreArchivo = archivo.getOriginalFilename();
                // Ruta donde se guardará la imagen
                Path ruta = Paths.get("src/main/resources/static/uploads/" + nombreArchivo);
                // Guardar archivo físicamente
                Files.write(ruta, archivo.getBytes());
                // Crear evidencia
                Evidencia evidencia = new Evidencia();
                evidencia.setNombre(nombreArchivo);
                evidencia.setActividad(actividad);
                // Guardar evidencia en BD
                evidenciaService.guardar(evidencia);
            }
        }
        return "redirect:/alumno/verEvidencias?id=" + id;
    }
}
