package com.app.util;

import java.awt.event.WindowAdapter;

import java.awt.event.WindowEvent;

import java.io.IOException;



import javax.media.CannotRealizeException;

import javax.media.ControllerAdapter;

import javax.media.ControllerEvent;

import javax.media.IncompatibleSourceException;

import javax.media.Manager;

import javax.media.MediaLocator;

import javax.media.NoDataSinkException;

import javax.media.NoDataSourceException;

import javax.media.NoPlayerException;

import javax.media.Player;

import javax.media.RealizeCompleteEvent;
import javax.media.protocol.CaptureDevice;

import javax.swing.JFrame;



public class Test {

  

  static Player  player;

  static CaptureDevice myFrame; 



  public static void main(String args[]) throws NoDataSourceException, IOException, IncompatibleSourceException, CannotRealizeException, NoDataSinkException, NoPlayerException  {

    

    //CREATE FRAME.

    final JFrame  myFrame = new JFrame();

                  myFrame.setVisible(true);

                  myFrame.setSize(300,300);

                  myFrame.addWindowListener(

                    new WindowAdapter(){

                      public void windowClosing(WindowEvent event){  

                        player.stop();

                        player.close();

                        myFrame.dispose();

                      }

                    }

                  );            

    

    //CREATE PLAYER.

    String filename = "/home/manikanta/WorkspaceUS/Videos/Rabhasa.mp4";

    
    
   
    
    
    player = Manager.createPlayer(new MediaLocator("file:" + filename));

    player.addControllerListener(

        new ControllerAdapter(){

          public void controllerUpdate(ControllerEvent event){  

            if (event instanceof RealizeCompleteEvent) {

              myFrame.add("Center",player.getVisualComponent());

              myFrame.add("South" ,player.getControlPanelComponent());

              myFrame.validate();

            }

          }

        }

    );         

    player.start(); 

  }



}