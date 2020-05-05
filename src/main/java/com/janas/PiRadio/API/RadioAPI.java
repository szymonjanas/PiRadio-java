package com.janas.PiRadio.API;

import com.janas.PiRadio.Radio.Radio;
import com.janas.PiRadio.Radio.RadioStations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/radio")
public class RadioAPI {

    @Autowired
    private RadioStations radioStations;

    @GetMapping("/validate")
    public String validate(){
        return "VALIDATION OK";
    }

    @GetMapping("/on/{name}")
    public void turnOnRadio(@PathVariable("name") String name ){
        try {
            String path = radioStations.getStation(name);
            if (path.length() > 0){
                Radio.init(path);
            }
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
        return radioStations.getStationsNames();
    }

    @GetMapping("/add/{name}")
    public void putStation(@PathVariable("name") String name, @RequestBody String url){
        radioStations.putStation(name, url);
    }

    @DeleteMapping("/delete/{name}")
    public void deleteStation(@PathVariable("name") String name){
        radioStations.deleteStation(name);
    }
}
