/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.Ricardo.Actividades.service;

import com.Ricardo.Actividades.model.Evidencia;
import com.Ricardo.Actividades.repository.EvidenciaRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Ricardo
 */
@Service
public class EvidenciaService {

    // Repositorio de evidencias
    @Autowired
    private EvidenciaRepository evidenciaRepository;

    // Guardar evidencia
    public void guardar(Evidencia evidencia){

        evidenciaRepository.save(evidencia);

    }

    // Listar evidencias por actividad
    public List<Evidencia> listarPorActividad(Integer id){

        return evidenciaRepository.findByActividadId(id);

    }

}
