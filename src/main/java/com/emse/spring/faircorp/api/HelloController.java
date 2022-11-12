package com.emse.spring.faircorp.api;

import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;

@RestController
@RequestMapping("/api/hello")
@Transactional
@CrossOrigin
public class HelloController {


    @GetMapping("/{name}")
    @Secured("ROLE_ADMIN") // (1)
    public MessageDto welcome(@PathVariable String name) {
        return new MessageDto("Hello " + name);
    }


    class MessageDto {
        String message;

        public MessageDto(String message) {
            this.message = message;
        }

        public String getMessage() {
            return message;
        }
    }
}

