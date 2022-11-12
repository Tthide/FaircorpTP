package com.emse.spring.faircorp;

import com.emse.spring.faircorp.hello.ConsoleGreetingService;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class FaircorpApplicationConfig {

    @Bean
    public CommandLineRunner greetingCommandLine() {


        ConsoleGreetingService consoleGreetingServiceGreetingService = new ConsoleGreetingService() ;// (3)

        return args -> {
            consoleGreetingServiceGreetingService.greet("Spring");// (4)
        };
    }
}
