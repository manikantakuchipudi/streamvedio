package com.app.server.util;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;

import javax.swing.JTextArea;

import org.apache.log4j.Logger;

import com.app.properties.ServerPropertiesRead;

public class ClentMessageReader extends Thread{

	private Socket _CLIENTSOCKET = null;
	public static final int BUFFER_SIZE = 100;  
	private ObjectInputStream dataread =null;
	private ObjectOutputStream datawrite=null;
	private Logger logger=Logger.getLogger(ClentMessageReader.class);

	public ClentMessageReader(Socket clientSocket)
	{
		this._CLIENTSOCKET=clientSocket;
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
				if(readobject.equals("getfiles"))
				{

					logger.info("readobject >>>>> :"+readobject);
					ListofVedios lfv=new ListofVedios();
					ArrayList<String> listoffiles=lfv.getVedioList(ServerPropertiesRead.getProperty("VEDIOLOCATION"));
					datawrite.writeObject(listoffiles);
					logger.debug(listoffiles);
				}
				else if(readobject.equals("download"))
				{

                    logger.info("readobject >>>>> :"+readobject);
					readobject=dataread.readObject();
					String filename=(String)readobject;
					logger.info("readobject >>>>> :"+readobject);
					File transferFile = new File (ServerPropertiesRead.getProperty("VEDIOLOCATION")+File.separator+filename); 
					byte [] bytearray = new byte [(int)transferFile.length()]; 
					FileInputStream fin = new FileInputStream(transferFile); 
					BufferedInputStream bin = new BufferedInputStream(fin); 
					bin.read(bytearray,0,bytearray.length); 
					logger.info("Sending Files..."); 
					datawrite.writeObject(bytearray);
					datawrite.writeObject(filename);
					datawrite.flush();
					}
			}
		}
		catch(Exception e)
		{
		   logger.error(e);
		}


	}

}
