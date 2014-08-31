package com.app.util;


import java.io.*;
import java.util.*;

import javax.media.Manager;
import javax.media.Player;
class AudioPlay
{
 public static void main(String args[]) throws Exception
 {


 // Take the path of the audio file from command line
 File f=new File("abc.wav");


 // Create a Player object that realizes the audio
 final Player p=Manager.createRealizedPlayer(f.toURI().toURL());


  // Start the music
  p.start();


  // Create a Scanner object for taking input from cmd
  Scanner s=new Scanner(System.in);


  // Read a line and store it in st
  String st=s.nextLine();


   // If user types 's', stop the audio
   if(st.equals("s"))
   {
   p.stop();
   }
 }
}