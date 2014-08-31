package com.app.client.util;

import java.io.File;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import javax.swing.JTextArea;

import org.apache.log4j.Logger;

import com.app.client.Client;

public class ServerMessageRecevier extends Thread{



	private ObjectInputStream datainput = null;
	private Client client = null;
	private Logger logger=Logger.getLogger(ServerMessageRecevier.class);

	public ServerMessageRecevier(Client client,ObjectInputStream datainput)
	{
		this.datainput=datainput;  
		this.client=client;
	}

	public void run()
	{
		try
		{
			while(true)
			{
				Object readobject = null;
				readobject=datainput.readObject();
				if(readobject instanceof ArrayList<?>)
				{
					logger.info("Mesaage read from server"+readobject);
					DisplayServerFiles dsf=new DisplayServerFiles((ArrayList<String>)readobject);
					//client.listofvedios=(ArrayList<String>)readobject;
					for(String data:(ArrayList<String>)readobject)
					client.combodata.addItem(data);
					dsf.displayList();
					
				}
				else if(readobject instanceof byte[])
				{
					logger.info("Mesaage read from server byte array"+readobject); 
					byte[] b=(byte[])readobject;
					readobject=datainput.readObject();
					String downloadfile=(String)readobject;
					logger.debug(b.length);
					FileOutputStream fos=new FileOutputStream(System.getProperty("java.io.tmpdir")+File.separator+downloadfile);
					fos.write(b);
					fos.flush();
					fos.close();
				PlayVedio pv=new PlayVedio();
				pv.playvediofile(System.getProperty("java.io.tmpdir")+File.separator+downloadfile);
					
					
					
				}
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}





}
