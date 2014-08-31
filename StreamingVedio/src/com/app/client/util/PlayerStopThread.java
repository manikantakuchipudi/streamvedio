package com.app.client.util;

import org.apache.log4j.Logger;

public class PlayerStopThread extends Thread {
	
	private Logger logger=Logger.getLogger(PlayerStopThread.class);
	long waittime;
	public  PlayerStopThread(long time)
	{
		waittime=time;
		start();
	}
	
	
	
	public void run()
	{
		try
		{
			logger.info("PlayerStopThread starts"+waittime);
			sleep(waittime);
			logger.info("time is completed");
		}
		catch(InterruptedException ioe)
		{
			logger.error(ioe);
		}
		
	}

}
