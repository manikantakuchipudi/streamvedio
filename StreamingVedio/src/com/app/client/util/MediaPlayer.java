package com.app.client.util;

import java.awt.BorderLayout;
import java.awt.Component;
import java.net.MalformedURLException;
import java.net.URL;
import javax.media.Manager;
import javax.media.MediaLocator;
import javax.media.Player;
import javax.swing.JFileChooser;
import javax.swing.JFrame;

/**
 *
 * @author 
 */

public class MediaPlayer extends javax.swing.JPanel {

	public MediaPlayer(URL mediauUrl) throws Exception{
		setLayout(new BorderLayout());
		Player mediaPlayer=Manager.createRealizedPlayer(new MediaLocator(mediauUrl));
		Component video=mediaPlayer.getVisualComponent();
		Component control=mediaPlayer.getControlPanelComponent();
		if (video!=null) {
			add(video, BorderLayout.CENTER);          // place the video component in the pane
		}

		add(control, BorderLayout.SOUTH);            // place the control in  panel
		mediaPlayer.start();
	}


}