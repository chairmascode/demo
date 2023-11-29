package com.example.elk.demo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.logging.Logger;

@RestController
@RequestMapping("welcome")
public class WelcomeController {

    private static final Logger logger = Logger.getLogger(WelcomeController.class.getName());

    @GetMapping("message/{name}")
    public String displayWelcomeMessage(@PathVariable String name){
        logger.info("WELCOME "+name);
        return "WELCOME "+name;
    }

}
