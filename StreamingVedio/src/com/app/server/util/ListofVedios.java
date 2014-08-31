package com.app.server.util;

import java.io.File;
import java.util.ArrayList;

import org.apache.log4j.Logger;

public class ListofVedios {

	private ArrayList<String> listofVedios=null;
	private static final Logger logger = Logger.getLogger(ListofVedios.class);


	public ListofVedios()
	{
		init();
	}

	public void init()
	{
		listofVedios=new ArrayList<String>();
	}

   public ArrayList<String> getVedioList(String srcdir)
	{
	   
	   logger.info("srcdir Location :>>>"+srcdir);
		File directory = new File(srcdir);
		File[] fList = directory.listFiles();
		for (File file : fList){
			if (file.isFile()){
				listofVedios.add(file.getName());
				logger.debug(file.getAbsolutePath());
				//System.out.println(file.getAbsolutePath());
			} else if (file.isDirectory()){
				//listofVedios.add(file.getAbsolutePath());
				getVedioList(file.getAbsolutePath());
			}
		}

		return listofVedios;
	}









	public static void main(String[] args)
	{
		
		ListofVedios lfv=new ListofVedios();
		System.out.println(lfv.getVedioList("/home/manikanta/WorkspaceUS/Videos/"));
	}





}
