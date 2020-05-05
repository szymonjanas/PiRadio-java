package com.janas.PiRadio.Radio;

import javazoom.jl.player.Player;

import java.net.URL;
import java.net.URLConnection;

public class Radio extends Thread {

    private static Radio instance = null;

    private String url;
    private static boolean init = false;

    private URLConnection connection = null;
    private Player player = null;

    public static void init(String url){
        if (init){
            instance.turnOff();
        }
        instance = null;
        instance = new Radio(url);
        instance.start();
        init = true;
    }

    private Radio(String url){
        this.url = url;
    }

    public void run(){
        try {
            connection = new URL (url).openConnection();
            connection.connect();
            player = new Player(connection.getInputStream());
            player.play();
        } catch (Exception e){
            System.out.println("Exception 2: " + e.toString());
        }
    }

    public static Radio getInstance(){
        return instance;
    }

    public void turnOff(){
        player.close();
        player = null;
        connection = null;
        init = false;
    }

    public static boolean isInit(){
        return init;
    }

}
