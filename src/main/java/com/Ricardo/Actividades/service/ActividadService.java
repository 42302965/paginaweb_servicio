/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.Ricardo.Actividades.service;

import com.Ricardo.Actividades.model.Actividad;
import com.Ricardo.Actividades.repository.ActividadRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 *
 * @author Ricardo
 */

@Service
public class ActividadService {

    // Repositorio de actividades
    @Autowired
    private ActividadRepository actividadRepository;

    // Listar actividades por login de alumno
    public List<Actividad> listarPorAlumno(String matricula){

        return actividadRepository.findByAlumnoLogin(matricula);

    }

    // Guardar actividad
    public void guardarActividad(Actividad actividad){

        actividadRepository.save(actividad);

    }

    // Listar actividades por login
    public List<Actividad> listarPorLogin(String login){

        return actividadRepository.findByAlumnoLogin(login);

    }

    // Listar actividades por matrícula
    public List<Actividad> listarPorMatricula(String matricula) {

        return actividadRepository.findByAlumno_Matricula(matricula);

    }

    // Buscar actividad por id
    public Actividad buscarPorId(Integer id) {

        return actividadRepository.findById(id).orElse(null);

    }

    // Eliminar actividades de un alumno
    public void eliminarPorMatricula(String matricula) {

    actividadRepository.eliminarPorMatricula(matricula);

}
}