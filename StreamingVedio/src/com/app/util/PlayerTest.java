package com.app.util;

import javax.media.*;

import java.io.IOException;
import java.net.URL;

public class PlayerTest {
    Player player = null;
    Class myClass = getClass();

    public PlayerTest() {
        try {            
            URL mediaURL = myClass.getResource("abc.wav");
            System.out.println(mediaURL);
            player = Manager.createPlayer(mediaURL);
        }
        catch(Exception e) {
            System.err.println("Exception: " + e);
        }        
    }

    public void play() {
        if (player != null)
        player.start();
    }
    
    public static void main(String args[]) throws NoDataSourceException, IOException, IncompatibleSourceException, CannotRealizeException, NoDataSinkException, NoPlayerException  {

    	new PlayerTest();
    	
    }
    
    
    
    
}
