package com.janas.PiRadio.Radio;


import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.Player;

import java.io.IOException;
import java.net.URL;
import java.net.URLConnection;

public class Radio {

    private URLConnection connection = null;
    private Player player = null;


    public void play(String url){
        try {
            if (connection == null && player == null){
                init("http://31.192.216.8:80/rmf_fm");
            }
            player.play();
        } catch (Exception e){
            System.out.println("Exception: " + e.toString());
        }
    }

    public void init(String url) throws IOException, JavaLayerException {
        connection = new URL (url).openConnection();
        connection.connect();
        player = new Player(connection.getInputStream());
    }

    public void stop(){
        player.close();
    }








}
