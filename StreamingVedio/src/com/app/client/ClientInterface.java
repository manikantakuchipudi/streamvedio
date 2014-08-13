package com.app.client;

import java.net.Socket;



public interface ClientInterface {
	
	
	
	public Socket createCon();
	public void fileTransfer(String file,String typeofdata);
	public void closeCon();
        public void sendMessage(String message);
	
	

}
