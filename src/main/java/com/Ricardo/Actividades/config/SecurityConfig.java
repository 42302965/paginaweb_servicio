/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.Ricardo.Actividades.config;


import com.Ricardo.Actividades.service.AlumnoDetailsService;
import com.Ricardo.Actividades.service.MaestroDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

/**
 *
 * @author Ricardo
 */
@Configuration
public class SecurityConfig {

    //detalles de alumno
    @Autowired
    private AlumnoDetailsService alumnoDetailsService;
    //detalles del maestro
    @Autowired
    private MaestroDetailsService maestroDetailsService;
    
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
    //Crear las contraseñas para el alumno
    @Bean
    public DaoAuthenticationProvider alumnoProvider() {

        DaoAuthenticationProvider auth = new DaoAuthenticationProvider();
        auth.setUserDetailsService(alumnoDetailsService);
        auth.setPasswordEncoder(passwordEncoder());
        return auth;

    }
    //Crear la contraseña para el maestro
    @Bean
    public DaoAuthenticationProvider maestroProvider() {

        DaoAuthenticationProvider auth = new DaoAuthenticationProvider();
        auth.setUserDetailsService(maestroDetailsService);
        auth.setPasswordEncoder(passwordEncoder());
        return auth;
    }

    //urls filtradas por seguridad para el alumno
    @Bean
public SecurityFilterChain alumnoChain(HttpSecurity http)
        throws Exception {

    http

        .csrf(csrf -> csrf.disable())

        .securityMatcher(
                "/alumno",
                "/login",
                "/inicioAlumno",
                "/misActividades",
                "/guardarActividad"
        )

        .authorizeHttpRequests(auth -> auth

            .requestMatchers(
                    "/alumno",
                    "/login"
            ).permitAll()

            .anyRequest().authenticated()

        )

        .formLogin(login -> login

            .loginPage("/alumno")

            .loginProcessingUrl("/login")

            .defaultSuccessUrl("/inicioAlumno", true)

            .failureUrl("/alumno?error=true")

        );

    http.authenticationProvider(alumnoProvider());

    return http.build();
}

    //urls filtradas con seguridad para el maestro
    @Bean
    public SecurityFilterChain maestroChain(HttpSecurity http)
        throws Exception {

    //urls que solo puede acceder si se loguea correctamente
    http
            
            .csrf(csrf -> csrf.disable())
            
            .securityMatcher(
                "/maestro",
                "/loginMaestro",
                "/inicioMaestro",
                "/listaAlumnos",
                "/nuevoAlumno",
                "/guardarAlumno",
                "/editarAlumno/**",
                "/actualizarAlumno",
                "/eliminarAlumno/**"
        )

        .authorizeHttpRequests(auth -> auth

            .requestMatchers(
                    "/maestro",
                    "/loginMaestro"
            ).permitAll()

            .anyRequest().authenticated()

        )

        .formLogin(login -> login
            .loginPage("/maestro")
            .loginProcessingUrl("/loginMaestro")
            .defaultSuccessUrl("/inicioMaestro", true)
            .failureUrl("/maestro?error=true")

        );

    http.authenticationProvider(maestroProvider());

    return http.build();

}

}