package com.janas.PiRadio.RadioProcessor;

import com.janas.PiRadio.RadioPlayer.RadioPlayerService;
import com.janas.PiRadio.RadioStations.RadioStationModel;
import com.janas.PiRadio.RadioStations.RadioStations;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

@Service
public class RadioProcessor {
    private static final String TAG = RadioProcessor.class.getSimpleName();

    private final RadioStations radioStations;
    private final RadioPlayerService radioPlayer;

    private RadioStationModel station = null;

    public RadioProcessor(RadioStations radioStations, RadioPlayerService radioPlayer) {
        this.radioStations = radioStations;
        this.radioPlayer = radioPlayer;
    }

    public HttpStatus validate(){
        return HttpStatus.OK;
    }

    public HttpStatus initRadio(String name ){
        station = radioStations.getStation(name);
        return HttpStatus.OK;
    }

    public HttpStatus turnOnRadio(){
        if (station != null){
            try {
                if (station.getUrl().length() > 0){
                    radioPlayer.turnOn(station);
                    return HttpStatus.OK;
                }
                return HttpStatus.NOT_ACCEPTABLE;
            } catch (Exception e){
                System.out.println(TAG + ": " + e.toString());
                return HttpStatus.NOT_ACCEPTABLE;
            }
        } else {
            System.out.println(TAG + ": station is not initialized!");
            return HttpStatus.NOT_ACCEPTABLE;
        }
    }

    public HttpStatus turnOffRadio(){
        try {
            radioPlayer.turnOff();
        } catch (Exception e){
            System.out.println(TAG + " : " + e.toString() + " : radioPlayer is not initialized!");
        }
        return HttpStatus.OK;
    }

    public Iterable<RadioStationModel> getStations(){
        return radioStations.getStations();
    }

    public HttpStatus addStation(String name, String url){
        if (radioStations.postStation(new RadioStationModel(name, url)))
            return HttpStatus.OK;
        else
            return HttpStatus.INTERNAL_SERVER_ERROR;
    }

    public HttpStatus addStation(RadioStationModel station){
        if (radioStations.postStation(station))
            return HttpStatus.OK;
        else
            return HttpStatus.INTERNAL_SERVER_ERROR;
    }

    public HttpStatus deleteStation(String name){
        if (radioStations.deleteStation(name))
            return HttpStatus.OK;
        else
            return HttpStatus.INTERNAL_SERVER_ERROR;
    }

    public void index(Model model){
        model.addAttribute("station", new RadioStationModel());
        model.addAttribute("stations", getStations());
    }

}
