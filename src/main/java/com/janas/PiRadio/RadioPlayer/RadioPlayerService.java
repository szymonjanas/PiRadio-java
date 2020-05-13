package com.janas.PiRadio.RadioPlayer;

import com.janas.PiRadio.RadioStations.RadioStationModel;
import org.springframework.stereotype.Service;

@Service
public class RadioPlayerService {
    private static RadioPlayerInterface radioPlayer = null;

    private RadioStationModel radioStation;

    public RadioPlayerService() {

    }

    public boolean turnOn(RadioStationModel radioStation){
        this.radioStation = radioStation;
        createRadioPlayer(radioStation.getUrl());
        radioPlayer.turnOn();
        return true;
    }

    protected void createRadioPlayer(String url){
        if (radioPlayer != null)
            turnOff();
        radioPlayer = new ZoomRadioPlayer(url);
    }

    public void turnOff(){
        if (radioPlayer != null){
            radioPlayer.turnOff();
            radioPlayer = null;
        }
    }

    public RadioStationModel getRadioStation() {
        return radioStation;
    }
}
