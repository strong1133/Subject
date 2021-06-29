package com.daib.backend.controller;

import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloApi {

    @RequestMapping(value = "/hello", method = RequestMethod.GET)
    public String helloApi(){
        return "안녕 안녕";
    }
}
