package com.teamz.usertrack;

import java.util.Hashtable;
import java.util.Scanner;
import java.util.Vector;


public class CommandsExecucute {
	
	
	
	
	private Vector<String> users;
	private Vector<String> comapnys;
	private Vector<String> comapnyusersList;
	private Hashtable<String,Vector<String>> useractions;
	private Hashtable<String,Vector<String>> companyusers;
	private Vector<String> unemployedPersons;
	
	/*public void CommandsExecute(String command,String CompanyName)
	{
		switch (command) {
		case "JOIN":
			
			break;
			
       case "CHANGE":
			
			break;
       case "QUIT":
			
			break;
			
       case "PAYDAY":
			
			break;
       case "EMPLOYEES":
			
			break;	
			
       case "UNEMPLOYEED":
			
			break;	
			
       case "DUMP":
			
			break;			
			
       case "END":
			
			break;	
			

		default:
			break;
		}
		
		
	}*/
	
	
	
	public void setStores()
	{
		users=new Vector<String>();
		comapnys=new Vector<String>();
		unemployedPersons=new Vector<String>(); 
		comapnyusersList=new Vector<String>();
		useractions=new Hashtable<String, Vector<String>>();
		companyusers=new Hashtable<String, Vector<String>>();
	}
	
	
	
	
	public String CommandsExecute(String command,String[] usercommand)
	{
	
		
		if(command.equalsIgnoreCase("JOIN"))
		{
			JoinCommand(usercommand);
		}
		else if(command.equalsIgnoreCase("CHANGE"))
		{
			changeCompany(usercommand);
		}
		else if(command.equalsIgnoreCase("QUIT"))
		{
			quitCommand(usercommand);
		}
		else if(command.equalsIgnoreCase("PAYDAY"))
		{
			userPayDay();
		}
		else if(command.equalsIgnoreCase("EMPLOYEES"))
		{
			viewEmployes(usercommand);
		}
		else if(command.equalsIgnoreCase("UNEMPLOYEED"))
		{
			viewUnEmployedUsers();
		}
		else if(command.equalsIgnoreCase("DUMP"))
		{
			viewAll();
		}
		else if(command.equalsIgnoreCase("END"))
		{
			
			return "false";
		}
		else
		{
			return "invalid command";
		}
		return "true";
		
		
	}
	
	
	public void viewUnEmployedUsers()
	{

		System.out.println(unemployedPersons);
		for(int i=0;i<unemployedPersons.size();i++)
		{
			System.out.println(unemployedPersons.get(i));
			
		}
	}
	
	
	public void viewAll()
	{

		System.out.println(companyusers);
	}
	
	
	
	public void viewEmployes(String[] usercommand)
	{

		
		
		if(usercommand.length>=2)
		{
		String companyName=usercommand[1];
		Vector<String> listofcompanyusers=companyusers.get(companyName);
		System.out.println(listofcompanyusers);
		for(int i=0;i<listofcompanyusers.size();i++)
		{
			System.out.println(listofcompanyusers.get(i));
			
		}
		}
		else
		{
			System.out.println("INVALID COMMAND");
		}
	}
	
	public String findUserCompany(String username)
	{
		
		Vector<String> companysUsers=null;
 		for(int i=0;i<companyusers.size();i++)
		{
			for(int j=0;j<comapnys.size();j++)
			{
				companysUsers=companyusers.get(comapnys.get(i));
				if(isUserExist(companysUsers, username))
				{
					return comapnys.get(i);
				}
			}
		}
		return null;
	}
	
	
	
	public void changeCompany(String[] usercommand)
	{
		String previousCompany=findUserCompany(usercommand[1]);
		if(previousCompany!=null)
		{
		String[] quituser=new String[3];
		quituser[0]="QUIT";
		quituser[1]=usercommand[1];
		quituser[2]=previousCompany;
		quitCommand(quituser);
		JoinCommand(usercommand);
		}
		else
		{
			System.out.println("USER NOT JOIND ANY PREVOUS COMPANY");
			Scanner sc=new Scanner(System.in);
			System.out.println("user is not joind so fresly joined in that company press Y else N");
			String userinput=sc.nextLine();
			if(userinput.equalsIgnoreCase("Y"))
			{
				usercommand[0]="JOIN";
				JoinCommand(usercommand);
			}
			else
			{
				
			}
		
		sc=null;
		}
		
		
	}
	
	public void JoinCommand(String[] usercommand)
	{
	try
	{
	String name=usercommand[1];
	String company=usercommand[2];
	Vector<String> listofactions=null;
    if(!comapnys.contains(company))
    	comapnys.add(company);
    	
    	
	if(isUserExist(unemployedPersons, name))
	{
		updateunEmployedVector(name);
	}
	
	if(!isUserExist(comapnyusersList,name))
	{
	if(isUserExist(users,name))
	{
		listofactions=useractions.get(name);
		listofactions.add(usercommand[0]);
		useractions.put(name,listofactions);
    }

	else
	{
		users.add(name);
		listofactions=new Vector<String>();
	    listofactions.add(usercommand[0]);
		useractions.put(name,listofactions);
	}
	
	if(companyusers.get(company)!=null)
	{
		Vector<String> userlist=companyusers.get(company);
		userlist.add(name);
		comapnyusersList.add(name);
		companyusers.put(company,userlist);

	}
	else
	{   
		
		Vector<String> userlist=new Vector<String>();
		userlist.add(name);
		comapnyusersList.add(name);
		companyusers.put(company,userlist);
	}
	}
	
	else
	{
		System.out.println("user is already in aother company please quit and join new company");
		
	
	}
	}
	catch (Exception e) {
		e.printStackTrace();
		
	}
    }
	
	
	
	public void userPayDay()
	{
	System.out.println(comapnyusersList);
	 Vector<String> listofactions=null;
	for(int i=0;i<comapnyusersList.size();i++)
	{
		listofactions=useractions.get(comapnyusersList.get(i));
		listofactions.add("PAYDAY");
		useractions.put(comapnyusersList.get(i),listofactions);
	}
	System.out.println(useractions);
	
	}
	
	
	
	
	
	public boolean isUserExist(Vector<String> user,String userName)
	{
		boolean isexist=false;
		for(int i=0;i<user.size();i++)
		{
			if(user.get(i).equals(userName))
			{
			isexist=true;
		     break;		
			}
		}
		return isexist;
		
	}
	
	
	public void quitCommand(String[] usercommand)
	{
	String name=usercommand[1];
	String company=usercommand[2];
	
	 
	 Vector<String> listofactions=null;
	
	if(companyusers.get(company)!=null)
	{
		unemployedPersons.add(name);
		companyusers.get(company).remove(name);
		listofactions=useractions.get(name);
		listofactions.add(usercommand[0]);
		useractions.put(name,listofactions);
		comapnyusersList.remove(name);
	}
	
	
	
	//System.out.println(useractions);
	//System.out.println(companyusers);
    }
	
	
	public void updateunEmployedVector(String name)
	{
		
		
		for(int i=0;i<unemployedPersons.size();i++)
		{
			if(unemployedPersons.get(i).equals(name))
			{
				unemployedPersons.remove(i);
				break;
			}
			
		}
   }
	
	public void updateEmployeListVector(String name)
	{
		
		
		for(int i=0;i<comapnyusersList.size();i++)
		{
			if(comapnyusersList.get(i).equals(name))
			{
				comapnyusersList.remove(i);
				break;
			}
			
		}
   }
	
	
	
	

}
