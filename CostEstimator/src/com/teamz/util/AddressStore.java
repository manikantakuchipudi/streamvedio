package com.teamz.util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.apache.log4j.Logger;
import org.json.JSONException;
import org.json.JSONObject;

import com.teamz.dbcon.DBconnection;

public class AddressStore {
	private Logger logger=Logger.getLogger(AddressStore.class);
	
	public AddressStore(JSONObject address)
	{
		
		
		PreparedStatement ptst=null;
		String query="insert into address values(?,?,?,?,?)";
		try {
			
			
			
			Connection con=DBconnection.getConnection();
			int id=getMaxaddrid(con);
			String addr=address.getString("address");
			String phonenumber=address.getString("phonenumber");
			String name=address.getString("name");
			int eventid=Integer.parseInt(address.getString("eventid"));
			String bookdate=address.getString("bookdate");
			ptst=con.prepareStatement(query);
			ptst.setInt(1, id);
			ptst.setString(2, phonenumber);
			ptst.setString(3, bookdate);
			ptst.setString(4, name);
			ptst.setInt(5, eventid);
			boolean execute=ptst.execute();
			logger.info("executd sucessfuuly");
		
		} catch (JSONException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally
		{
			closaAll(null, ptst, null);
			DBconnection.closeCon();
		}
		
		
	}
	
	
	public int getMaxaddrid(Connection con)
	{
		
		Statement stmt=null;
		String query =null;
		ResultSet rs=null;
		int id=0;
		try
		{
		  stmt = con.createStatement();
	       query = "select max(idAddress) as idaddress from address";
	       rs =  stmt.executeQuery(query);
	       if (rs.next()) {
	        id = rs.getInt("idaddress");
	      }
	      else
	      {
	    	  id=0;
	      }
		}
		catch(SQLException sqle)
		{
			logger.error(sqle);
		}
		finally
		{
			closaAll(stmt, null, rs);
		}
	      
	      return id+1;
	}
	
	
	public void closaAll(Statement st,PreparedStatement ptst,ResultSet rt)
	{
		try
		{
			if(st!=null)
			{
				st.close();
				st=null;
			}
				
			
			if(ptst!=null)
			{
				ptst.close();
				ptst=null;
			}
			if(rt!=null)
			{
				rt.close();
				rt=null;
			}
		}
		catch(Exception e)
		{
		}
	}
}
