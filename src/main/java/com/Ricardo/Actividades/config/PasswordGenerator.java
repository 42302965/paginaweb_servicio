/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.Ricardo.Actividades.config;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 *
 * @author Ricardo
 */
public class PasswordGenerator {

    public static void main(String[] args) {

        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        //crear el hash con una contraseña de prueba 
        String password = "1234";

        String hash = encoder.encode(password);

        System.out.println(hash);

    }

}
