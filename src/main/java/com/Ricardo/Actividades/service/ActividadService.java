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

    @Autowired
    private ActividadRepository actividadRepository;

    public List<Actividad> listarPorAlumno(String matricula){

        return actividadRepository.findByAlumnoLogin(matricula);

    }

    public void guardarActividad(Actividad actividad){

        actividadRepository.save(actividad);

    }

    public List<Actividad> listarPorLogin(String login){

        return actividadRepository.findByAlumnoLogin(login);

    }

    public List<Actividad> listarPorMatricula(String matricula){

        return actividadRepository.findByAlumno_Matricula(matricula);

    }

}