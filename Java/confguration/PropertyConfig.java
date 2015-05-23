package Confguration;

import java.io.IOException;
import java.util.Properties;

public class PropertyConfig {

    private Properties configFile = new Properties();

    private String yagoTransitiveTypePath;

    public PropertyConfig()
    {
        try {
            configFile.load(getClass().getClassLoader().getResourceAsStream("config.properties"));
            updateFields();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
    private void updateFields(){
        this.yagoTransitiveTypePath=configFile.getProperty("YagoTransitiveTypePath");
    }


    public String getYagoTransitiveTypePath() {
        return yagoTransitiveTypePath;
    }
}
