package com.gcp;


import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Properties;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

public class ResourceReader {
    private static Properties prop = new Properties();
    private static Properties configProperties = new Properties();
    private static Properties log4jProperties = new Properties();
    private static String CONFIG = "conf/config.properties";
    private static String LOG4J = "conf/log4j.properties";
    private static InputStream inputStreamConfig ;
    private static InputStream inputStreamlog4j ;
    public static Logger log = Logger.getLogger(ResourceReader.class);
    static {
        try {

            if(OSValidator.isWindows())
            {
                inputStreamlog4j = ResourceReader.class.getClassLoader().getResourceAsStream(LOG4J);
                inputStreamConfig = ResourceReader.class.getClassLoader().getResourceAsStream(CONFIG);

            }
            else if((OSValidator.isUnix() || OSValidator.isSolaris() || OSValidator.isMac()))
            {
                inputStreamlog4j = new FileInputStream(System.getProperty("user.dir") + "/conf/log4j.properties");
                inputStreamConfig = new FileInputStream(System.getProperty("user.dir") + "/conf/config.properties");
            }

            if(null != inputStreamConfig)
            {
                log4jProperties.load(inputStreamlog4j);
                PropertyConfigurator.configure(log4jProperties);
                configProperties.load(inputStreamConfig);
                log.info("Successfully Loaded {} file , src/resources/config.properties");
            }
            else {
                log.warn("{} file does not exist in class path , src/main/resources/config.properties");
            }
            prop.putAll(configProperties);
        }catch (Exception e){
            try {
                throw new Exception("Exception occurred while loading properties file.");
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }

    public static String getValue(String key){
        return prop.getProperty(key);
    }
}
