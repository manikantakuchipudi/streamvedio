/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.app.client;

import java.io.ObjectInputStream;
import javax.swing.JTextArea;

/**
 *
 * @author 
 */
public class ServerMessageReader extends Thread{
    ObjectInputStream datainput = null;
    JTextArea clientTextArea=null;
    public ServerMessageReader(ObjectInputStream datainput,JTextArea clientTextArea)
    {
     this.datainput=datainput;   
     this.clientTextArea=clientTextArea;
    }
    
    public void run()
    {
        try
        {
        while(true)
	{
        Object readobject = null;
        readobject=datainput.readObject();
        clientTextArea.append(readobject.toString());
        }
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }
    
}
