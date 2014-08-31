package com.app.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import javax.swing.JButton;
import javax.swing.JTextArea;

import com.app.properties.ServerPropertiesRead;
import com.app.server.util.ClentMessageReader;

import javax.swing.JOptionPane;

import org.apache.log4j.Logger;

public class Server {

    private int PORT;
    private ServerSocket _SOCKET = null;
    private Socket _CLIENTSOCKET = null;
    private static final Logger logger = Logger.getLogger(Server.class);
    private static boolean statusflag = true;
    private JTextArea clientTextArea = null;
    private JTextArea serverTextArea = null;
    private String sendMessage=null;
   
    private boolean isPortInUse() {
        boolean result;
        try {
            ServerSocket server_SOCKET = new ServerSocket(PORT);
            server_SOCKET.close();
            result = true;
        } catch (Exception e) {
            e.printStackTrace();
            result = false;
        }
        return (result);
    }

    public Server() {
        try {
             PORT = (Integer.parseInt(ServerPropertiesRead.getProperty("PORT")));
            logger.debug("SERVER PORT IS :" + PORT);
           
        } catch (NumberFormatException nbe) {
            JOptionPane.showMessageDialog(null, "port number problem occured" + nbe.getMessage());
            logger.error("port number problem occured :" + nbe.getMessage());
        }
    }

    public ServerSocket establishedServer() {
        if (isPortInUse()) {
            if (_SOCKET == null) {
                try {
                    _SOCKET = new ServerSocket(PORT);
                   
                } catch (IOException e) {
                    JOptionPane.showMessageDialog(null,"Some problem occured to start" + e.getMessage());
                    logger.error("Some problem occured to start " + e.getMessage());
                    e.printStackTrace();
                    System.exit(0);
                }
            }
        } else {
            JOptionPane.showMessageDialog(null, "Port is already Used try to close port and start server");
            logger.info("Port is already Used try to close port and start server" + "\n");
            System.exit(0);
        
        
        }
        return _SOCKET;
    }

    public void startServer() {
        establishedServer();
        statusflag = true;
        logger.info("server starts the application");
        logger.info(statusflag);
        while (statusflag) {

            try {
                logger.info("server waiting clients connection");
                _CLIENTSOCKET = _SOCKET.accept();
                logger.info("client connection is established");
                logger.info("Server is conected to client ip is : " + _CLIENTSOCKET);
                logger.info("Server is conected to client ip is : " + _CLIENTSOCKET.getInetAddress());
                //mesrecv=new MessageRecevier(_CLIENTSOCKET,serverTextArea,clientTextArea);
                new ClentMessageReader(_CLIENTSOCKET);
             
            } catch (IOException e) {
                logger.info("client connection is closed ");
                
            }

        }

    }
    
    
    public void setClientTexaArea(JTextArea clienttextArea)
    {
        this.clientTextArea=clienttextArea;
    }
    
    public void setServerClientArea(JTextArea serverTextArea)
    {
        this.serverTextArea=serverTextArea;
    }
    
    
    public void sendMessage(String sendmessage)
    {
        this.sendMessage=sendmessage;
    }

    public void stopserver() {
        if (_SOCKET != null) {
            try {

                _SOCKET.close();
                logger.info("server stops sucessfully");
                
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }
    
    public static void main(String[] args)
    {
    	Server servers=new Server();
    	servers.startServer();
    }
    

}
