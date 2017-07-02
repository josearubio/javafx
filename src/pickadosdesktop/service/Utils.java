package pickadosdesktop.service;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;
import org.apache.log4j.Logger;
import pickadosdesktop.ui.PickadosApp;

public final class Utils {
    private final static Logger logger = Logger.getLogger(Utils.class);
    
    public static String getFormattedCurrentDate(){
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date();
        return dateFormat.format(date);
    }
    
    public static String getProperty(String propertyName) {
       Properties property = new Properties();
       String propertyValue = "";
       try{
           property.load(PickadosApp.class.getClassLoader().getResourceAsStream("resources/config.properties"));
           propertyValue = property.getProperty(propertyName);
           logger.info(propertyName+" loaded successfully");
       }catch(Exception ex){
           logger.error(ex);
       }
       
       return propertyValue;
    }
}
