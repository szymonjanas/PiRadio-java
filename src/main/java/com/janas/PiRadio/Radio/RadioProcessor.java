package com.janas.PiRadio.Radio;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

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
        path = radioStations.getStation(name).getUrl();
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
                System.out.println(TAG + ": " + e.toString());
                return HttpStatus.NOT_ACCEPTABLE;
            }
        } else {
            System.out.println(TAG + ": Radio is not initialized!");
            return HttpStatus.NOT_ACCEPTABLE;
        }
    }

    public HttpStatus turnOffRadio(){
        try {
            Radio.getInstance().turnOff();
        } catch (Exception e){
            System.out.println(TAG + " : " + e.toString() + " : Radio not init!");
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
