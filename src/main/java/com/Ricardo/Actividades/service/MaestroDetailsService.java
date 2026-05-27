/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.Ricardo.Actividades.service;

import com.Ricardo.Actividades.model.Maestro;
import com.Ricardo.Actividades.repository.MaestroRepository;
import java.util.ArrayList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.*;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;

/**
 *
 * @author Ricardo
 */
@Service
public class MaestroDetailsService implements UserDetailsService {

    // Repositorio de maestros
    @Autowired
    private MaestroRepository maestroRepository;

    // Método usado por Spring Security para autenticar maestros
    @Override
    public UserDetails loadUserByUsername(String username)
            throws UsernameNotFoundException{

        // Buscar maestro por login
        Maestro maestro = maestroRepository.findByLogin(username);

        // Validar si el maestro existe
        if (maestro == null){

            throw new UsernameNotFoundException("Maestro no encontrado");

        }

        // Retornar usuario para Spring Security
        return new User(maestro.getLogin(), maestro.getContrasena(), new ArrayList<>());
    }

}