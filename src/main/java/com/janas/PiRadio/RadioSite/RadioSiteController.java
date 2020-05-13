package com.janas.PiRadio.RadioSite;

import com.janas.PiRadio.RadioProcessor.RadioProcessor;
import com.janas.PiRadio.RadioStations.RadioStationModel;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/radio")
public class RadioSiteController {

    private final RadioProcessor radioProcessor;

    public RadioSiteController(RadioProcessor radioProcessor) {
        this.radioProcessor = radioProcessor;
    }

    @GetMapping()
    public ModelAndView index(Model model){
        radioProcessor.index(model);
        return new ModelAndView("radio");
    }

    @PostMapping("/on")
    public ModelAndView turnOnRadio(@RequestParam String name){
        radioProcessor.initRadio(name);
        radioProcessor.turnOnRadio();
        return new ModelAndView("redirect:/radio");
    }

    @PostMapping("/off")
    public ModelAndView turnOffRadio(){
        radioProcessor.turnOffRadio();
        return new ModelAndView("redirect:/radio");
    }

    @PostMapping("/add")
    public ModelAndView postStation(@ModelAttribute("station") RadioStationModel station){
        radioProcessor.addStation(station);
        return new ModelAndView("redirect:/radio");
    }

    @PostMapping("/delete")
    public ModelAndView  deleteStation(@RequestParam String name){
        radioProcessor.deleteStation(name);
        return new ModelAndView("redirect:/radio");
    }
}
