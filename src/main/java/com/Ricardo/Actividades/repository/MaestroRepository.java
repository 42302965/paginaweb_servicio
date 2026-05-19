/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.Ricardo.Actividades.repository;

import com.Ricardo.Actividades.model.Maestro;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author Ricardo
 */
public interface MaestroRepository extends JpaRepository<Maestro, String>{

    Maestro findByLogin(String login);

}
