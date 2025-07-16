package com.example.demo.myfirstapi.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController // Marks this class as a REST controller
public class HelloWorldController {

    @Value("${app.greeting.message}") // Inject value from application.properties
    private String customGreeting;

    @GetMapping("/hello") // Maps HTTP GET requests to the "/hello" URL
    public String sayHello() {
        return customGreeting;
    }

    @GetMapping("/greeting")
    public String greetWithParam(@RequestParam(value="name", required = false, defaultValue = "World") String name) {
        return "Hello " + name + "!";
    }

    @GetMapping("/calculate/sum")
    public String sumNumbers(
            @RequestParam("num1") int number1, @RequestParam("num2") int number2
    ) {
        return String.format("The sum of %s and %s is %s", number1, number2, (number1 + number2));
    }

}