package com.emse.spring.faircorp.logs;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;


public class Log4jTest {

    private static final Logger logger = LogManager.getLogger(Log4jTest.class);

    public static void main(String[] args) {
        logger.debug("Debug log message");
        logger.info("Info log message");
        logger.error("Error log message");

    }

}