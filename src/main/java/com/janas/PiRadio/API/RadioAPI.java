package com.janas.PiRadio.API;

import com.janas.PiRadio.Radio.Radio;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/radio")
public class RadioAPI {

    Radio radio = null;

    @GetMapping("/validate")
    public String validate(){
        return "VALIDATION OK";
    }

    @GetMapping("/on")
    public void turnOnRadio(){
        radio = new Radio();
        try {
            radio.play("http://31.192.216.8:80/rmf_fm");
        } catch (Exception e){
            System.out.println("Exception: " + e.toString());
        }
    }

    @GetMapping("/off")
    public void turnOffRadio(){
        if (radio != null){
            radio.stop();
        }
    }


}

