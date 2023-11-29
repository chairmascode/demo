package com.example.monitoring.monitor_demo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/message")
public class HelloController {

    @GetMapping("/show")
    public String displayHelloWorld() {
        return "Hello World";
    }

}
