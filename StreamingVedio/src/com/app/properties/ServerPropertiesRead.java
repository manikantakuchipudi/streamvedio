package com.app.properties;

import java.util.MissingResourceException;
import java.util.PropertyResourceBundle;
import java.util.ResourceBundle;
import org.apache.log4j.Logger;

public class ServerPropertiesRead {

    protected static PropertyResourceBundle properties = null;
    private final static Logger logger = Logger.getLogger(ServerPropertiesRead.class);

    static {

        properties = (PropertyResourceBundle) ResourceBundle.getBundle("Serverconn");
        logger.info("Serverconn properties Readed sucessfully");
    }

    public ServerPropertiesRead() {
    }

    public static String getProperty(String property) {
        try {
            property = properties.getString(property);
        } catch (MissingResourceException mre) {
            logger.error(mre.getMessage());
        }
        return property;

    }

}
