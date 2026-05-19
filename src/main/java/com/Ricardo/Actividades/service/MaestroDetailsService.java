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

    @Autowired
    private MaestroRepository maestroRepository;

    @Override
    public UserDetails loadUserByUsername(String username)
            throws UsernameNotFoundException{

        Maestro maestro = maestroRepository.findByLogin(username);

        if (maestro == null){
            throw new UsernameNotFoundException("Maestro no encontrado");
        }

        return new User(maestro.getLogin(), maestro.getContrasena(), new ArrayList<>());
    }

}
