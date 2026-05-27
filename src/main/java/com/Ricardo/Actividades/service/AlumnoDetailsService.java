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

    // Repositorio de alumnos
    @Autowired
    private AlumnoRepository alumnoRepository;
    
    // Buscar alumno por login
    public Alumno buscarPorLogin(String login){

    return alumnoRepository.buscarPorLogin(login);

}

    // Método usado por Spring Security para autenticar usuarios
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        // Mensaje de prueba
        System.out.println("ENTRO A loadUserByUsername");

        // Mostrar username recibido
        System.out.println("USERNAME: " + username);

        // Buscar alumno en la base de datos
        Alumno alumno = alumnoRepository.buscarPorLogin(username);

        // Mostrar alumno encontrado
        System.out.println("ALUMNO: " + alumno);

        // Validar si el usuario existe
        if (alumno == null) {

            System.out.println("NO SE ENCONTRO USUARIO");

            throw new UsernameNotFoundException("Usuario no encontrado");

        }

        // Mostrar contraseña encriptada
        System.out.println("CONTRASENA HASH: "+ alumno.getContrasena());

        // Retornar usuario para Spring Security
        return new User(alumno.getLogin(), alumno.getContrasena(), new ArrayList<>());
    }
}