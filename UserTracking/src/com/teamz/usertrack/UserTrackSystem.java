package com.teamz.usertrack;

import java.util.Scanner;
import java.util.Vector;

public class UserTrackSystem {
	
	
	
	private Scanner sc=null;
	public static void main(String[] args)
	{
		UserTrackSystem uts=new UserTrackSystem();
		uts.userActions();
	
	}
	
	
	private void userActions()
	{
		boolean flag =true;
		CommandsExecucute cmdexecutor=new CommandsExecucute();
		sc=new Scanner(System.in);
		String cmd=null;
		String[] cmdspliter=null;
		cmdexecutor.setStores();
		CompanysFileReader cmr=new CompanysFileReader();
		Vector<String> listofcompanys=cmr.fileReader(null);
		while(flag)
		{
			cmd= sc.nextLine();
			cmdspliter=cmd.trim().split(" ");
			if(isvalidCompanyNames(listofcompanys,cmdspliter[cmdspliter.length-1])||cmdspliter.length==1)
			{
			String returncommand=cmdexecutor.CommandsExecute(cmdspliter[0], cmdspliter);
			
			if(returncommand.equals("true"))
				flag=true;
			else if(returncommand.equals("false"))
			{
				System.out.println("Exit from input mode");
				flag=false;
			}
			else
				System.out.println("invalid command enter check once");
			}
			else
			{
				System.out.println("Invalid in list pleas try");
			}
			
		}
       
		
		
   
	}
	
	 public boolean isvalidCompanyNames(Vector<String> companylist ,String companyname)
	    {
		 
		 if(companylist.contains(companyname))
		   return true;
		 else
		  return false;
	    }
		
		
		

}
