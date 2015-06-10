package confguration;

import java.io.IOException;
import java.util.Properties;


public class PropertyMapYagoInt {



    public Properties mapYagoIdsProperty = new Properties();



    public PropertyMapYagoInt()
    {
        try {
            mapYagoIdsProperty.load(getClass().getClassLoader().getResourceAsStream("MapYagoIds.properties"));
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
}
