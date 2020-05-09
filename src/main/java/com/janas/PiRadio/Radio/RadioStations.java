package com.janas.PiRadio.Radio;

import com.janas.PiRadio.Support.OSValidator;
import org.springframework.stereotype.Component;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

@Component
public class RadioStations {

    private static String windowsDefaultPath = "radio-stations.txt";
    private static String linuxDefaultPath = "/home/pi/radio-stations.txt";
    public static String givenPath = null;

    public String path = null;

    private Map<String, String> stations = new HashMap<>();

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

    public String getStation(String name){
        if (stations.containsKey(name)){
            return stations.get(name);
        }
        return "";
    }

    public boolean postStation(String name, String url){
        stations.put(name, url);
        if (save()){
            return true;
        } else {
            return false;
        }
    }

    public boolean deleteStation(String name){
        if (stations.containsKey(name)){
            stations.remove(name);
            save();
            return true;
        } else {
            return false;
        }
    }

    public String getStationsNames(){
        StringBuilder builder = new StringBuilder();
        for (Map.Entry entry: stations.entrySet())
        {
            builder.append(entry.getKey());
            builder.append("\n");
        }
        return builder.toString();
    }

    public boolean save() {
        try {
            FileWriter writer = new FileWriter(file);
            StringBuilder builder = new StringBuilder();
            for (Map.Entry entry: stations.entrySet())
            {
                builder.append(entry.getKey());
                builder.append("\n");
                builder.append(entry.getValue());
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
                stations.put(data.get(i), data.get(i+1));
            }
        } catch (Exception e){
            System.out.println("Exception from load file: " + e.toString());
        }
    }

}
