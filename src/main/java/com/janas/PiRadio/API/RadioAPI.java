package com.janas.PiRadio.API;

import com.janas.PiRadio.Radio.Radio;
import com.janas.PiRadio.Radio.RadioStations;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/radio")
public class RadioAPI {

    @GetMapping("/validate")
    public String validate(){
        return "VALIDATION OK";
    }

    @GetMapping("/on/{name}")
    public void turnOnRadio(@PathVariable("name") String name ){
        try {
            Radio.init(RadioStations.getStation(name));
        } catch (Exception e){
            System.out.println("Exception: " + e.toString());
        }
    }

    @GetMapping("/off")
    public void turnOffRadio(){
        Radio.getInstance().turnOff();
    }

    @GetMapping("/stations")
    public String getStations(){
        return RadioStations.getStationsNames();
    }

    @GetMapping("/add/{name}")
    public void putStations(@PathVariable("name") String name, @RequestBody String url){
        RadioStations.putStation(name, url);
    }
}
