package com.app.util;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.FileInputStream;
import java.io.FileReader;
import java.net.URL;
import java.util.ArrayList;

public class AudioFileReader {
	
	
	
	public static void main(String[] args)
	{
		AudioFileReader afr=new AudioFileReader();
	
		
		URL fileloc= AudioFileReader.class.getResource("abc.wav");
		afr.fileReader("D:\\Manik\\WSUS\\StreamingVedio\\bin\\com\\app\\util\\abc.wav");

		
	}
	
	
	public void fileReader(String filename)
	{
	
		BufferedReader br=null;
		FileReader fr=null;
		if(isAudioFile())
		{
			try
			{
				fr=new FileReader(filename);
			    br=new BufferedReader(fr);
			    FileInputStream fis=new FileInputStream(filename);
			    
			    
			    System.out.println(fis);
			    
			    ArrayList<Byte> data=new ArrayList<Byte>();
			    byte byteread;
			    while(fis.available()>0)
			    {
			    	byteread=(byte)fis.read();
			    	data.add(byteread);
			    	
			   System.out.println(byteread);
			    	
			    }
			    
			    
			    System.out.println(data);
			
			
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
			
			
			
		}
		
		
	}
	
	
	public boolean isAudioFile()
	{
	
		return true;
	}
	
	
	
	
	
	

}
