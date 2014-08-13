package com.teamz.usertrack;

import java.util.Hashtable;
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
			
		}
		else if(command.equalsIgnoreCase("QUIT"))
		{
			quitCommand(usercommand);
		}
		else if(command.equalsIgnoreCase("PAYDAY"))
		{
			
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

		String companyName=usercommand[1];
		Vector<String> listofcompanyusers=companyusers.get(companyName);
		System.out.println(listofcompanyusers);
		for(int i=0;i<listofcompanyusers.size();i++)
		{
			System.out.println(listofcompanyusers.get(i));
			
		}
	}
	
	
	public void JoinCommand(String[] usercommand)
	{
	try
	{
	String name=usercommand[1];
	String company=usercommand[2];
	Vector<String> listofactions=null;

	
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
		comapnyusersList=companyusers.get(company);
		comapnyusersList.add(name);
		companyusers.put(company,comapnyusersList);
	
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
	
	public void changeCommand(String[] usercommand)
	{
	
	
	
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
