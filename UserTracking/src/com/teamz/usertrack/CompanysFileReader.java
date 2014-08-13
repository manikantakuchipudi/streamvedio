package com.teamz.usertrack;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOError;
import java.io.IOException;
import java.util.Vector;

public class CompanysFileReader {
	
	private Vector<String> companysList=null;
	
	
	
	public Vector<String> fileReader(String filename)
	{
		companysList=new Vector<String>();
		BufferedReader br=null;
		String stline=null;
		try
		{
		
			if(filename==null)
			br=new BufferedReader(new FileReader("resources/CompanyNames.txt"));
			else
				br=new BufferedReader(new FileReader(filename));
			while((stline=br.readLine())!=null)
			{
				companysList.add(stline);
			}
		}
		catch(IOException e)
		{
			e.printStackTrace();
		}
		finally
		{
		try
		{
			if(br!=null)
			{
				br.close();
				br=null;
			}
		}
		catch(IOException ioe)
		{
			
		}
		
	}
		return companysList;
	}
}
