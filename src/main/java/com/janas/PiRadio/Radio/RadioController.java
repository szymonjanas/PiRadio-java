package com.janas.PiRadio.Radio;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/radio")
public class RadioController {

    private final RadioProcessor radioProcessor;

    public RadioController(RadioProcessor radioProcessor) {
        this.radioProcessor = radioProcessor;
    }

    @GetMapping("/validate")
    public ResponseEntity<?>  validate(){
        return new ResponseEntity<>(radioProcessor.validate());
    }

    @GetMapping("/on/{name}")
    public ResponseEntity<?>  turnOnRadio(@PathVariable("name") String name ){
        radioProcessor.initRadio(name);
        return new ResponseEntity<>(radioProcessor.turnOnRadio());
    }

    @GetMapping("/on")
    public ResponseEntity<?>  turnOnLastRadio(){
        return new ResponseEntity<>(radioProcessor.turnOnRadio());
    }

    @GetMapping("/off")
    public ResponseEntity<?>  turnOffRadio(){
        return new ResponseEntity<>(radioProcessor.turnOffRadio());
    }

    @GetMapping("/stations")
    public ResponseEntity<?> getStations(){
        return new ResponseEntity<>(radioProcessor.getStations(), HttpStatus.OK);
    }

    @PostMapping("/add/{name}")
    public ResponseEntity<?>  postStation(@PathVariable("name") String name, @RequestBody String url){
        return new ResponseEntity<>(radioProcessor.addStation(name, url));
    }

    @DeleteMapping("/delete/{name}")
    public ResponseEntity<?>  deleteStation(@PathVariable("name") String name){
        return new ResponseEntity<>(radioProcessor.deleteStation(name));
    }
}
