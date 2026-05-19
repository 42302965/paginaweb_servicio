/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.Ricardo.Actividades.service;

import com.Ricardo.Actividades.model.Alumno;
import com.Ricardo.Actividades.repository.AlumnoRepository;
import java.util.ArrayList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 *
 * @author Ricardo
 */
@Service
public class AlumnoDetailsService
        implements UserDetailsService {

    @Autowired
    private AlumnoRepository alumnoRepository;
    
    public Alumno buscarPorLogin(String login){

    return alumnoRepository.buscarPorLogin(login);

}

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        System.out.println("ENTRO A loadUserByUsername");

        System.out.println("USERNAME: " + username);

        Alumno alumno = alumnoRepository.buscarPorLogin(username);

        System.out.println("ALUMNO: " + alumno);

        if (alumno == null) {
            System.out.println("NO SE ENCONTRO USUARIO");
            throw new UsernameNotFoundException("Usuario no encontrado");
        }

        System.out.println("CONTRASENA HASH: "+ alumno.getContrasena());

        return new User(alumno.getLogin(), alumno.getContrasena(), new ArrayList<>());
    }
}
