/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.Ricardo.Actividades.repository;

import com.Ricardo.Actividades.model.Actividad;
import jakarta.transaction.Transactional;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 *
 * @author Ricardo
 */
public interface ActividadRepository extends JpaRepository<Actividad, Integer>{

    List<Actividad> findByAlumnoLogin(String login);

    List<Actividad> findByAlumno_Matricula(String matricula);
    
    @Transactional
    @Modifying
    @Query("DELETE FROM Actividad a WHERE a.alumno.matricula = :matricula")
    void eliminarPorMatricula(@Param("matricula") String matricula);


}