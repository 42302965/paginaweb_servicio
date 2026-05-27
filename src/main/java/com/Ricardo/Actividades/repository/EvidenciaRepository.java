/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.Ricardo.Actividades.repository;

import com.Ricardo.Actividades.model.Evidencia;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Ricardo
 */
@Repository
public interface EvidenciaRepository extends JpaRepository<Evidencia, Integer>{

    List<Evidencia> findByActividadId(Integer id);

}
