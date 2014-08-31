package com.app.client.util;

import java.net.URL;


import javax.media.MediaLocator;
import javax.media.Player;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

import org.apache.log4j.Logger;


public class PlayVedio {
    
	
	Player player = null;
	private Logger logger=Logger.getLogger(ServerMessageRecevier.class);
    public void playvediofile(String filepath)
	{
		logger.info("playing vedio file hereee"+filepath);
		PlayerTest(filepath);
	}
    
    public void PlayerTest(String filepath) {
		try {            
			
			logger.info("playing vedio file start");
			long starttime=System.currentTimeMillis();
			starttime=starttime*1000;	
			//long time=(long)player.getStopTime().getSeconds();
			//System.out.println(time);
			JFrame mediaTest = new JFrame( "Movie Player" );
			mediaTest.setDefaultCloseOperation( JFrame.HIDE_ON_CLOSE );
			URL mediaUrl=new MediaLocator("file:" + filepath).getURL();
			MediaPlayer mediaPanel = new MediaPlayer(mediaUrl);
			mediaTest.add( mediaPanel );
			mediaTest.setSize( 800, 700 ); // set the size of the player
			mediaTest.setLocationRelativeTo(null);
			mediaTest.setVisible( true );
			//time=time-starttime;
			//new PlayerStopThread(time);
			//logger.info("playing vedio file start"+player.getDuration().ge);
		}
		catch(Exception e) {
			JOptionPane.showMessageDialog(null, "exception : "+e.getMessage());
			logger.error("Exception: " + e);
		}        
	}

}
