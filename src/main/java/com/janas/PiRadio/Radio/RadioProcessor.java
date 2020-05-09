package com.janas.PiRadio.Radio;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
public class RadioProcessor {
    private static final String TAG = RadioProcessor.class.getSimpleName();

    private final RadioStations radioStations;

    private String path = null;

    public RadioProcessor(RadioStations radioStations) {
        this.radioStations = radioStations;
    }

    public HttpStatus validate(){
        return HttpStatus.OK;
    }

    public HttpStatus initRadio(String name ){
        path = radioStations.getStation(name);
        return HttpStatus.OK;
    }

    public HttpStatus turnOnRadio(){
        if (path != null){
            try {
                if (path.length() > 0){
                    Radio.init(path);
                    return HttpStatus.OK;
                }
                return HttpStatus.NOT_ACCEPTABLE;
            } catch (Exception e){
                System.out.println("Exception: " + e.toString());
                return HttpStatus.NOT_ACCEPTABLE;
            }
        } else {
            System.out.println(TAG + ": Radio is not initialized!");
            return HttpStatus.NOT_ACCEPTABLE;
        }
    }

    public HttpStatus turnOffRadio(){
        Radio.getInstance().turnOff();
        return HttpStatus.OK;
    }

    public String getStations(){
        return radioStations.getStationsNames();
    }

    public HttpStatus addStation(String name, String url){
        if (radioStations.postStation(name, url))
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



}
