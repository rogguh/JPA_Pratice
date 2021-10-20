package com.example.jpa.main;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController(value = "/")
public class MainController {

    final Logger logger = LogManager.getLogger(this.getClass());

    @GetMapping(value = "")
    public String main(){
        logger.info("테스트");
        return "";
    }
}
