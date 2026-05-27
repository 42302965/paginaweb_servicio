/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.Ricardo.Actividades.service;

import com.Ricardo.Actividades.model.Alumno;
import com.Ricardo.Actividades.repository.AlumnoRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Ricardo
 */
@Service
public class AlumnoService {

    // Repositorio de alumnos
    @Autowired
    private AlumnoRepository alumnoRepository;

    // Listar todos los alumnos
    public List<Alumno> listarTodos(){

        return alumnoRepository.findAll();

    }

    // Guardar alumno
    public void guardarAlumno(Alumno alumno){

        alumnoRepository.save(alumno);

    }

    // Buscar alumno por matrícula
    public Alumno buscarPorId(String matricula){

        return alumnoRepository.findById(matricula).orElse(null);

    }

    // Eliminar alumno por matrícula
    public void eliminarPorId(String matricula){

        alumnoRepository.deleteById(matricula);

    }

}