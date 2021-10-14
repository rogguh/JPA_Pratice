package com.example.jpa.main;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController(value = "/")
public class MainController {

    @GetMapping(value = "")
    public String main(){
        return "";
    }
}
