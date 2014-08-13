/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.app.server;

import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import javax.swing.JTextArea;


/**
 *
 * @author 
 */
public class MessageRecevier extends Thread{
        private Socket _CLIENTSOCKET = null;
        public static final int BUFFER_SIZE = 100;  
        private ObjectInputStream dataread =null;
	private ObjectOutputStream datawrite=null;
        private JTextArea serverTextArea=null;
        private JTextArea clientTextArea=null;
        private int index=0;
    private String sendMessage=null;
    public MessageRecevier(Socket clientSocket,JTextArea servertextarea,JTextArea clientTextArea)
    {
        this._CLIENTSOCKET=clientSocket;
        this.serverTextArea=servertextarea;
        this.clientTextArea=clientTextArea;
        
        start();
    }
    
     public void messagesender(String message) {                                      
        try
        {
         datawrite.writeObject(message);
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    } 
    public void run()
    {
        try
        {
        datawrite = new ObjectOutputStream(_CLIENTSOCKET.getOutputStream());  
        dataread = new ObjectInputStream(_CLIENTSOCKET.getInputStream());
        while(true)
	{
        Object readobject = null;
        readobject=dataread.readObject();
        serverTextArea.append(readobject.toString());
        }
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        
        
        }
    
 



}
