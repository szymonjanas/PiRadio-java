package com.janas.PiRadio.Radio;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class RadioStations {

    private static Map<String, String> stations = new HashMap<>();

    private static File file = new File("radio-stations.txt");

    public static void init(){
        load();
    }

    public static String getStation(String name){
        return stations.get(name);
    }

    public static void putStation(String name, String url){
        stations.put(name, url);
        save();
    }

    public static String getStationsNames(){
        StringBuilder builder = new StringBuilder();
        for (Map.Entry entry: stations.entrySet())
        {
            builder.append(entry.getKey());
            builder.append("\n");
        }
        return builder.toString();
    }

    public static void save() {
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

    public static void load(){
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
