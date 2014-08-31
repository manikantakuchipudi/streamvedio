package com.app.client.util;

import java.util.ArrayList;

import org.apache.log4j.Logger;

public class DisplayServerFiles {
	private Logger logger=Logger.getLogger(ServerMessageRecevier.class);

	
	private ArrayList<String> listoffies=null;
	public DisplayServerFiles(ArrayList<String> listoffies)
	{
		logger.debug("list of files is"+listoffies);
		this.listoffies=listoffies;
	}
	
	public void displayList()
	{
		logger.debug("displayList of files is"+listoffies);
	}
	

}
