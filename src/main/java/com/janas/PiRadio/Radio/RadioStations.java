package com.janas.PiRadio.Radio;

import com.janas.PiRadio.Support.OSValidator;
import org.springframework.stereotype.Component;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

@Component
public class RadioStations {

    private static String windowsDefaultPath = "radio-stations.txt";
    private static String linuxDefaultPath = "/home/pi/radio-stations.txt";
    public static String givenPath = null;

    public String path = null;

    private ArrayList<RadioStationModel> stations = new ArrayList<>();

    private File file;

    public RadioStations(){
        if (givenPath != null){
            path = givenPath;
        } else {
            switch (OSValidator.getSystemName()){
                case linux:
                    path = linuxDefaultPath;
                    break;
                case windows:
                    path = windowsDefaultPath;
                    break;
            }
        }
        file = new File(path);
        load();
    }

    private int getIndex(String name){
        for (int index = 0; index < stations.size(); ++index){
            if (stations.get(index).getName().equals(name))
                return index;
        }
        return -1;
    }

    public RadioStationModel getStation(String name){
        if (contains(name)){
            return stations.get(getIndex(name));
        }
        return null;
    }

    public boolean postStation(RadioStationModel station){
        stations.add(station);
        if (save()){
            return true;
        } else return false;
    }

    public boolean contains(String name){
        for (int index = 0; index < stations.size(); ++index){
            if (stations.get(index).getName().equals(name))
                return true;
        }
        return false;
    }

    public boolean deleteStation(String name){
        if (contains(name)){
            stations.remove(getStation(name));
            save();
            return true;
        } else return false;
    }

    public Iterable<RadioStationModel> getStations(){
        return stations;
    }

    public boolean save() {
        try {
            FileWriter writer = new FileWriter(file);
            StringBuilder builder = new StringBuilder();
            for (RadioStationModel model: stations)
            {
                builder.append(model.getName());
                builder.append("\n");
                builder.append(model.getUrl());
                builder.append("\n");
            }
            writer.write(builder.toString());
            writer.close();
            return true;
        } catch (Exception e){
            System.out.println("Exception from save file: " + e.toString());
            return false;
        }
    }

    public void load(){
        try {
            Scanner reader = new Scanner(file);
            ArrayList<String> data = new ArrayList<>();
            while (reader.hasNextLine()) {
                data.add(reader.nextLine());
            }
            reader.close();
            for (int i=0; i < data.size(); i+=2){
                stations.add(new RadioStationModel(data.get(i), data.get(i+1)));
            }
        } catch (Exception e){
            System.out.println("Exception from load file: " + e.toString());
        }
    }
}
