/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.Ricardo.Actividades.service;

import com.Ricardo.Actividades.model.Maestro;
import com.Ricardo.Actividades.repository.MaestroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 *
 * @author Ricardo
 */
@Service
public class MaestroService {

    // Repositorio de maestros
    @Autowired
    private MaestroRepository maestroRepository;

    // Buscar maestro por id
    public Maestro buscarPorId(String id){

        return maestroRepository.findById(id).orElse(null);

    }

}