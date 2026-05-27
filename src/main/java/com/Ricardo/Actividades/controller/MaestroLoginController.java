/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.Ricardo.Actividades.controller;

import com.Ricardo.Actividades.model.Maestro;
import com.Ricardo.Actividades.repository.MaestroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
/**
 *
 * @author Ricardo
 */

@Controller
public class MaestroLoginController {
    
    @Autowired
    private MaestroRepository maestroRepository;

    @GetMapping("/loginMaestro")
    public String loginMaestro() {
        return "maestro";
    }
    
    // Procesar login de maestro
    @PostMapping("/loginMaestro")
    public String procesarLogin(
            @RequestParam String username,
            @RequestParam String password,
            Model model){
        // Buscar maestro por login
        Maestro maestro = maestroRepository.findByLogin(username);

        //pruebas para ver si funcionaba el login de maestro
        if(maestro != null && password.equals("1234")){
            return "inicioMaestro";
        }

        model.addAttribute("error","Usuario o contraseña incorrectos");
        return "maestro";
    }
}
