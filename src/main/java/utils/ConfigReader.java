package utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ConfigReader {

    Properties prop;

    public ConfigReader() {

        try {
        	
        	String env = System.getProperty("env");
        	
        	if (env == null) {
        		env ="qa";
        	}

            FileInputStream fis =
                    new FileInputStream(
                            System.getProperty("user.dir")
                            + "/src/test/resources/config-" + env + ".properties");

            prop = new Properties();

            prop.load(fis);

        } catch (IOException e) {

            e.printStackTrace();
        }
    }

    public String getBaseUrl() {

        return prop.getProperty("baseUrl");
    }

    public String getApiKey() {

        return prop.getProperty("apiKey");
    }
}