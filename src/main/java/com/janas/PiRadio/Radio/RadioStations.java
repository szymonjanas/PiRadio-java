package com.janas.PiRadio.Radio;

import org.springframework.stereotype.Component;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

@Component
public class RadioStations {

    private Map<String, String> stations = new HashMap<>();

    private File file;

    public RadioStations(){
        String path = "/home/" + System.getProperty("user.name") + "/radio-stations.txt";
        file = new File(path);
        load();
    }

    public String getStation(String name){
        if (stations.containsKey(name)){
            return stations.get(name);
        }
        return "";
    }

    public void putStation(String name, String url){
        stations.put(name, url);
        save();
    }

    public void deleteStation(String name){
        stations.remove(name);
        save();
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

    public void save() {
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

        } catch (Exception e){
            System.out.println("Exception from save file: " + e.toString());
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
