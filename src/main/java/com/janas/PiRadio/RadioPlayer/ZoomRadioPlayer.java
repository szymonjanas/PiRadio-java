package com.janas.PiRadio.RadioPlayer;

import java.net.URL;
import java.net.URLConnection;
import javazoom.jl.player.Player;

public class ZoomRadioPlayer extends Thread implements RadioPlayerInterface {
    private static final String TAG = ZoomRadioPlayer.class.getSimpleName();

    private URLConnection connection = null;
    private Player player = null;

    private String url = null;

    private boolean isPlaying = false;

    ZoomRadioPlayer(String url){this.url = url;}

    public void turnOn(){
        run();
    }

    public void run(){
        try {
            connection = new URL (url).openConnection();
            connection.connect();
            player = new Player(connection.getInputStream());
            player.play();
            isPlaying = true;
        } catch (Exception e){
            System.out.println(TAG + ": " + e.toString());
        }
    }

    public void turnOff(){
        isPlaying = false;
        player.close();
        player = null;
        connection = null;
    }

    public boolean isPlaying() {
        return isPlaying;
    }

}
