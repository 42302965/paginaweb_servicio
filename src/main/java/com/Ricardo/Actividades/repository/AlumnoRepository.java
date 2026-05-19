/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.Ricardo.Actividades.repository;


import com.Ricardo.Actividades.model.Alumno;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 *
 * @author Ricardo
 */

public interface AlumnoRepository
        extends JpaRepository<Alumno, String> {

    @Query("SELECT a FROM Alumno a WHERE a.login = :login")
    Alumno buscarPorLogin(@Param("login") String login);

}
