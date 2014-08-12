package com.teamz.util;



import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Hashtable;

import org.apache.log4j.Logger;
import org.json.JSONException;
import org.json.JSONObject;



import com.teamz.dbcon.DBconnection;

public class SelectiondataObject {
	
	private JSONObject USDN=null;
	private JSONObject USPN=null;
	private JSONObject USFOOD=null;
	private JSONObject USDRINK=null;
	private JSONObject USHEALTHY=null;
	
	private Logger logger=Logger.getLogger(SelectiondataObject.class);
	private CostEstimatorCaluculator costEstimator=null;
	private JSONObject eventString;
	private int noofPeopels=0;
	
	public SelectiondataObject(JSONObject entireData,int noofPeopels)
	{
		try {
			this.USDN=entireData.getJSONObject("USDN");
			this.USPN=entireData.getJSONObject("USPN");
			this.USFOOD=entireData.getJSONObject("USFOOD");
			this.USDRINK=entireData.getJSONObject("USDRINK");
			this.USHEALTHY=entireData.getJSONObject("USHEALTHY");
			this.noofPeopels=noofPeopels;
			
			logger.info("sssss"+USDN.get("checked"));
			
			logger.info("sssss"+USPN.get("checked"));
			
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			logger.error(e);
		}
		costEstimator=new CostEstimatorCaluculator();
		costEstimator.setNumberOfPeople(noofPeopels);
		eventString=new JSONObject();
	}
	
	
	public void CostEstomatorCaluclate()
	{
		try {
			if(USDN.getBoolean("checked"))
			{
				eventString.put("USDN","true");
				
				logger.info(USDN.getDouble("dcprice")+(USDN.getDouble("costperperson")*noofPeopels));
				costEstimator.setCostOfDecorations(USDN.getDouble("dcprice")+(USDN.getDouble("costperperson")*noofPeopels));
	
				
		
			
			}
			else
			{
				eventString.put("USDN","false");
			}
			if(USPN.getBoolean("checked"))
			{
				eventString.put("USPN","true");
				logger.info(USPN.getDouble("dcprice")+(USPN.getDouble("costperperson")*noofPeopels));
				costEstimator.setCostOfDecorations(USPN.getDouble("dcprice")+(USPN.getDouble("costperperson")*noofPeopels));
			}
			else
			{
				eventString.put("USPN","false");
			}
			if(USFOOD.getBoolean("checked"))
			{
				eventString.put("USFOOD","true");
				costEstimator.setCostOfDecorations(USFOOD.getDouble("price"));
		    }
			else
			{
				eventString.put("USFOOD","false");
			}
			if(USDRINK.getBoolean("checked"))
			{
				eventString.put("USDRINK","true");
				costEstimator.setCostOfDrinksPerPerson(USDRINK.getDouble("price"));
			}
			else
			{
				eventString.put("USDRINK","false");
			}
			if(USHEALTHY.getBoolean("checked"))
			{
				eventString.put("USHEALTHY","true");
				costEstimator.setHealthyOption(true);
				eventString.put("USHEALTHYDISCOUNT",""+USHEALTHY.getDouble("percent"));
				
				costEstimator.setDiscountvalue(USHEALTHY.getDouble("percent"));
			}
			else
			{costEstimator.setHealthyOption(false);
			
				
			eventString.put("USHEALTHY","false");
			eventString.put("USHEALTHYDISCOUNT","0");
			}
			
			
			
			
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			logger.error(e);
		}
	}
	
	
	
	public int eventid()
	{

		CostEstomatorCaluclate();
		eventString.toString();
		Connection con=DBconnection.getConnection();
		int id=getMaxeventid(con);
		String eventdata=eventString.toString();
		double totalcost=costEstimator.getTotalCost();
		PreparedStatement ptst=null;
		String query="insert into event values (?,?,?)";
		try
		{
			ptst=con.prepareStatement(query);
			ptst.setInt(1, id);
			ptst.setString(2, eventdata);
			ptst.setDouble(3, totalcost);
		    boolean isexecute=ptst.execute();
			logger.info("successfull executed");
		}
	   catch(Exception e)
		{
		logger.error(e);   
		}
		finally
		{
			closaAll(null, ptst, null);
			DBconnection.closeCon();
		}
		
	
		return id;
	}
	
	
	public int getMaxeventid(Connection con)
	{
		
		Statement stmt=null;
		String query =null;
		ResultSet rs=null;
		int id=0;
		try
		{
		  stmt = con.createStatement();
	       query = "select max(idevent) as idevent from event";
	       rs =  stmt.executeQuery(query);
	      System.out.println("Id Name    Job");
	      if (rs.next()) {
	        id = rs.getInt("idevent");
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
