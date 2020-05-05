package com.janas.PiRadio.API;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/radio")
public class RadioAPI {

    @GetMapping("/validate")
    public String validate(){
        return "VALIDATION OK";
    }

}

