/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.app.server;

import javax.swing.JTextArea;

/**
 *
 * @author
 */
public class StartServerThread extends Thread{
    
    private JTextArea clientTextArea = null;
    private JTextArea serverTextArea = null;
    private String sendMessage=null;
    private Server servers=null;
   
    
    public void run()
    {
    
        servers=new Server();
        
        
        System.out.println("clientTextArea"+clientTextArea);
                
        servers.setClientTexaArea(clientTextArea);
        servers.setServerClientArea(serverTextArea);
        servers.startServer();
    
    
    }
    
     
    public void setClientTexaArea(JTextArea clienttextArea)
    {
        System.out.println("clientTextArea"+clientTextArea);
        
        this.clientTextArea=clienttextArea;
    }
    
    public void setServerClientArea(JTextArea serverTextArea)
    {
        this.serverTextArea=serverTextArea;
    }
    
    
    public void sendMessage(String sendMessage)
    {
        servers.sendMessage(sendMessage);
       
    }
    
}
