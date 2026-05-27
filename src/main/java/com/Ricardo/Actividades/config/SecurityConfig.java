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
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
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

    // Servicio para autenticar alumnos
    @Autowired
    private AlumnoDetailsService alumnoDetailsService;

    // Servicio para autenticar maestros
    @Autowired
    private MaestroDetailsService maestroDetailsService;

    // Encriptador de contraseñas
    @Bean
    public PasswordEncoder passwordEncoder() {

        return new BCryptPasswordEncoder();

    }

    // Proveedor de autenticación para alumnos
    @Bean
    public DaoAuthenticationProvider alumnoProvider() {

        DaoAuthenticationProvider auth =
                new DaoAuthenticationProvider();

        // Servicio que busca alumnos en la BD
        auth.setUserDetailsService(alumnoDetailsService);

        // Encriptación de contraseña
        auth.setPasswordEncoder(passwordEncoder());

        return auth;

    }

    // Proveedor de autenticación para maestros
    @Bean
    public DaoAuthenticationProvider maestroProvider() {

        DaoAuthenticationProvider auth =
                new DaoAuthenticationProvider();

        // Servicio que busca maestros en la BD
        auth.setUserDetailsService(maestroDetailsService);

        // Encriptación de contraseña
        auth.setPasswordEncoder(passwordEncoder());

        return auth;

    }

    // Configuración de seguridad para alumnos
    @Bean
    public SecurityFilterChain alumnoChain(
            HttpSecurity http)
            throws Exception {

        http

            // Desactivar CSRF
            .csrf(csrf -> csrf.disable())

            // URLs del alumno protegidas
            .securityMatcher(
                    "/alumno",
                    "/login",
                    "/alumno/**"
            )

            // Permisos de acceso
            .authorizeHttpRequests(auth -> auth

                // Estas rutas son públicas
                .requestMatchers(
                        "/alumno",
                        "/login"
                ).permitAll()

                // Las demás requieren login
                .anyRequest().authenticated()

            )

            // Configuración del login
            .formLogin(login -> login

                // Página de login
                .loginPage("/alumno")

                // URL que procesa el login
                .loginProcessingUrl("/login")

                // Página al iniciar sesión correctamente
                .defaultSuccessUrl(
                        "/alumno/inicioAlumno",
                        true
                )

                // Página si ocurre error
                .failureUrl("/alumno?error=true")

            );

        // Usar autenticación de alumnos
        http.authenticationProvider(alumnoProvider());

        return http.build();

    }

    // Configuración de seguridad para maestros
    @Bean
    public SecurityFilterChain maestroChain(
            HttpSecurity http)
            throws Exception {

        http

            // Desactivar CSRF
            .csrf(csrf -> csrf.disable())

            // URLs del maestro protegidas
            .securityMatcher(
                    "/maestro",
                    "/loginMaestro",
                    "/maestro/**"
            )

            // Permisos de acceso
            .authorizeHttpRequests(auth -> auth

                // Rutas públicas
                .requestMatchers(
                        "/maestro",
                        "/loginMaestro"
                ).permitAll()

                // Las demás requieren login
                .anyRequest().authenticated()

            )

            // Configuración del login
            .formLogin(login -> login

                // Página de login maestro
                .loginPage("/maestro")

                // URL que procesa login
                .loginProcessingUrl("/loginMaestro")

                // Página principal al iniciar sesión
                .defaultSuccessUrl(
                        "/maestro/inicioMaestro",
                        true
                )

                // Página en caso de error
                .failureUrl("/maestro?error=true")
            );

        // Usar autenticación de maestros
        http.authenticationProvider(maestroProvider());

        return http.build();
    }
}