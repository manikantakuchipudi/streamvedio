/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.bank.app;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import org.apache.log4j.Logger;

/**
 *
 * @author kuchipudi
 */
public class GetCon {

	private static String dbtype=null;
	private static String dbusername=null;
	private static String dbpassword=null;
	private static String dbname=null;
	private static String dbhost=null;
	private static String dbport=null;
	private static boolean JNDI;
	private static GetCon dbconObj=null;
	private static Connection dbcon=null;
	private static Context ctx = null;
	private final static Logger logger = Logger.getLogger(GetCon.class);

	private GetCon()
	{
		dbtype=DBpropertiesRead.getProperty("dbtype");
		dbusername=DBpropertiesRead.getProperty("username");
		dbpassword=DBpropertiesRead.getProperty("password");
		dbname=DBpropertiesRead.getProperty("dbname");
		dbhost=DBpropertiesRead.getProperty("dbhost");
		dbport=DBpropertiesRead.getProperty("dbport");
		JNDI=Boolean.parseBoolean(DBpropertiesRead.getProperty("JNDI"));
	}

	public static Connection getCon()
	{




		if(dbconObj==null)
			dbconObj=new GetCon();



		try
		{
			logger.info("JNDI property value "+JNDI);
			if(!JNDI)
			{
				String url="";
				if(dbtype.equalsIgnoreCase("MYSQL"))
				{
					url="jdbc:mysql://"+dbhost+":"+dbport+"/"+dbname;
					Class.forName ("com.mysql.jdbc.Driver");
				}
				else if(dbtype.equalsIgnoreCase("POSGRESQL"))
				{
					url="jdbc:postgresql://"+dbhost+":"+dbport+"/"+dbname;
					Class.forName("org.postgresql.Driver");

				}
				else if(dbtype.equalsIgnoreCase("ORACLE"))
				{

					url= "jdbc:oracle:thin:@"+dbhost+":"+dbport+":"+dbname;
					Class.forName("oracle.jdbc.driver.OracleDriver");

				}
				logger.info("The dynamic url prparation is :"+url);
				dbcon=DriverManager.getConnection(url, dbusername, dbpassword);
			}
			else
			{
				ctx = new InitialContext();
	            DataSource ds = (DataSource) ctx.lookup("java:/comp/env/jdbc/JNDIDB");
	            dbcon = ds.getConnection();
	            logger.info("The connection establihed via JNDI url prparation is ");
			}
		}
		catch(ClassNotFoundException classnotfe)
		{
			logger.error(classnotfe);

		}
		catch(SQLException sqlexec)
		{
			logger.error(sqlexec);
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			logger.error(e);
			JNDI=false;
			dbcon=getCon();
		}
		
		return dbcon;
	}

	
	
	
	
	

	public static void closeCon()
	{
		if(dbcon!=null)
		{
			try
			{
				dbcon.close();
				dbcon=null;
			}
			catch(SQLException sql)
			{
				System.out.println("closing error"); 
			}
		}
	}

	
	public static int getPrimaryKey(){
		int nextvalue=0;
		Connection con=GetCon.getCon();
		PreparedStatement ps2;
		try {
		
		ps2=con.prepareStatement("select javatpointnewaccount.nextval from dual");
		
		ResultSet rs=ps2.executeQuery();
		rs.next();
		nextvalue=rs.getInt(1);

		
		
	} catch (SQLException e) {
			
			e.printStackTrace();
		}
	return nextvalue;

	}



	public static void main(String[] args) {
		System.out.println(getCon());
		if(dbcon!=null)
		{
			try
			{
				dbcon.close();
				dbcon=null;
			}
			catch(SQLException sql)
			{

			}
		}

	}


}
