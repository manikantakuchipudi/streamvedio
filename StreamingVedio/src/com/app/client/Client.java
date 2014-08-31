package com.app.client;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ConnectException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import org.apache.log4j.Logger;
import com.app.client.util.ServerMessageRecevier;
import com.app.properties.ClientPropertiesRead;

public class Client implements ClientInterface{

	
	
	private int _PORT;
	private String _HOST = "";
	private Socket _CLIENTSOCKET = null;
	private ObjectInputStream datainput = null;
	private ObjectOutputStream dataoutput = null;
	private final static Logger logger = Logger.getLogger(Client.class);
	private String vedioname=null;
	public ArrayList<String> listofvedios=null;
	public JComboBox<String> combodata=null;
	
	
	public Client()
	{
		
		try {
			_PORT = (Integer.parseInt(ClientPropertiesRead.getProperty("PORT")));
			_HOST = ClientPropertiesRead.getProperty("HOST");
		} catch (NumberFormatException nbe) {
			logger.info("" + nbe.getMessage());
		}
	}
	
	public void setvedio(String vedioname)
	{
		this.vedioname=vedioname;
	}
	
	public void setArrayList(ArrayList<String> listofvedios)
	{
		this.listofvedios=listofvedios;
	}
	
	public void setCombobject(JComboBox<String> combodata)
	{
		this.combodata=combodata;
	}
	
	
	
	
	
	@Override
	public Socket createCon() {
		try {
			_CLIENTSOCKET = new Socket(_HOST, _PORT);
			if(datainput==null)    
				datainput = new ObjectInputStream(_CLIENTSOCKET.getInputStream());
			if(dataoutput==null)
				dataoutput = new ObjectOutputStream(_CLIENTSOCKET.getOutputStream());
			ServerMessageRecevier smreader=new ServerMessageRecevier(this,datainput);
			smreader.start();
		} catch (ConnectException e) {

			JOptionPane.showMessageDialog(null, "Connection problem occured reason is" + e.getMessage());
			logger.info(e.getMessage());
			logger.info("Check server starts or not " + "\n");
			logger.error(e);
			_CLIENTSOCKET = null;
			System.exit(0);

		} catch (UnknownHostException e) {
			JOptionPane.showMessageDialog(null, "Connection problem occured reason is" + e.getMessage());
			logger.info(e.getMessage() + "\n");
			logger.error(e);
			_CLIENTSOCKET = null;
			System.exit(0);

		} catch (IOException e) {
			JOptionPane.showMessageDialog(null, "Connection problem occured reason is" + e.getMessage());
			logger.error(e);
			logger.info(e.getMessage() + "\n");
			_CLIENTSOCKET = null;
			System.exit(0);

		}
		return _CLIENTSOCKET;
	}

	@Override
	public void fileTransfer(String file, String typeofdata) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void closeCon() {
		// TODO Auto-generated method stub
		
		if (_CLIENTSOCKET != null) {
			try {
				dataoutput.writeObject("close");
				dataoutput.close();
				datainput.close();
				_CLIENTSOCKET.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
			dataoutput = null;
			dataoutput = null;
			_CLIENTSOCKET = null;
		}
		
	}

	@Override
	public void sendMessage(String message) {
		// TODO Auto-generated method stub
		try
		{

			dataoutput.writeObject(message);

		}
		catch(Exception e)
		{
			e.printStackTrace();
		}

	}
	
	

	public void downloadfileMessage(String message,String filename) {
		// TODO Auto-generated method stub
		try
		{

			dataoutput.writeObject(message);
			dataoutput.writeObject(filename);

		}
		catch(Exception e)
		{
			e.printStackTrace();
		}

	}
	
	
	public static void main(String[] args)
	{
		Client c=new Client();
		c.createCon();
	}

}
