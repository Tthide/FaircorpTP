package com.emse.spring.faircorp.hello;

import org.springframework.stereotype.Service;


@Service
public class DummyUserService {

    private final GreetingService greetingService;

    public DummyUserService(GreetingService greetingService) {
        this.greetingService = greetingService;

    }

    public void greetAll() {


        String[] everyone = {"Elodie", "Charles"};

        for (int i = 0; i < everyone.length; i++) {

            this.greetingService.greet((everyone[i]));
        }
    }
}
