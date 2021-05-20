package com.example.helloworld.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloWorldController {
	
	    @GetMapping(path="/hello", produces = "application/json")
	    public String getHello() 
	    {
	        return "Hello World!";
	    }

}
