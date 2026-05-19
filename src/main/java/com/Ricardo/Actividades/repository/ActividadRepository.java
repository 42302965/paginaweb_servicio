/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.Ricardo.Actividades.repository;

import com.Ricardo.Actividades.model.Actividad;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author Ricardo
 */
public interface ActividadRepository extends JpaRepository<Actividad, Integer>{

    List<Actividad> findByAlumnoLogin(String login);

    List<Actividad> findByAlumno_Matricula(String matricula);
    

}