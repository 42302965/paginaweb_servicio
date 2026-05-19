package com.Ricardo.Actividades;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class TestHash {

    public static void main(String[] args) {

        BCryptPasswordEncoder encoder =
                new BCryptPasswordEncoder();

        String hash =
                "$2a$10$O6YsRTyUZ4Os7cSulzmqSOEvlAnRTdRfmiWXvIpT2CRTkXN0CHG9C";

        System.out.println(
                encoder.matches("1234", hash)
        );

    }

}