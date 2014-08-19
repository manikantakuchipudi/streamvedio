/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.bank.app;


import java.util.MissingResourceException;
import java.util.PropertyResourceBundle;
import java.util.ResourceBundle;
import org.apache.log4j.Logger;

/**
 *
 * @author kuchipudi
 */
public class DBpropertiesRead {
    protected static PropertyResourceBundle properties = null;
    private final static Logger logger = Logger.getLogger(DBpropertiesRead.class);

    static {

        properties = (PropertyResourceBundle) ResourceBundle.getBundle("DBproperties");
        logger.info("DBproperties properties Readed sucessfully");
    }

    public DBpropertiesRead() {
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
