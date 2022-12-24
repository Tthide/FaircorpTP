package com.emse.spring.faircorp.logs;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class TestLog4J {

    // Récupération de notre logger.
    // Récupération de notre logger.
    private static final Logger LOGGER =  LogManager.getLogger( TestLog4J.class );

    // Le point d'entrée du programme.
    public static void main( String [] args ) {

        // On produit un log de niveau informatif.
        LOGGER.log( Level.INFO, "Hello World with Log4J 2" );

        // On produit un log de niveau erreur.
        LOGGER.log( Level.ERROR, "Houston, we have a problem" );
//api
        System.out.println(org.apache.logging.log4j.Logger.class.getResource("/org/ap‌​ache/logging/log4j/Logger.class"));
//core
        System.out.println(org.apache.logging.log4j.Logger.class.getResource("/org/ap‌​ache/logging/log4j/core/Appender.class"));
//config
        System.out.println(org.apache.logging.log4j.Logger.class.getResource("/log4j2.xml"));
    }
}
