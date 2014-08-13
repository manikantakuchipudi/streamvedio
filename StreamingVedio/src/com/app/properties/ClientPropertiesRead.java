package com.app.properties;
import java.util.MissingResourceException;
import java.util.PropertyResourceBundle;
import java.util.ResourceBundle;
import org.apache.log4j.Logger;

public class ClientPropertiesRead {
    
	protected static PropertyResourceBundle  properties = null;
	private final static Logger logger = Logger.getLogger(ClientPropertiesRead.class);
    static{

		properties =  (PropertyResourceBundle) ResourceBundle.getBundle("Clientconn");
		logger.info("Clientconn properties Readed sucessfully");
	}

	public ClientPropertiesRead(){		  
	}	


	public static String  getProperty(String property){
		try{
			property = properties.getString(property);
		}catch(MissingResourceException mre){
			logger.error(mre.getMessage());
		}		
		return property;	 

	}  




}
