package Utils;


import confguration.PropertyMapYagoInt;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class MapStringToInt {
    static private PropertyMapYagoInt propertyMapYagoInt=new PropertyMapYagoInt();
    static private Map<String, Integer> mapYagoToInt= new HashMap<>();
    static private Map<Integer, String> mapIntToYago=new HashMap<>();
    static Properties mapYagoIdsProperty =new Properties();;
    static Properties mapIdsYagoProperty =new Properties();;
    public int getCounter() {
        return counter;
    }

    static private int counter=0;

    public static void writeLocationToPropertiesFiles(){

//        PropertyConfig config=new PropertyConfig();
//        int fillMapYagoIds=config.getMapYagoID();

        OutputStream mapYagoIds = null;
        OutputStream mapIdsYago=null;


        try {
            mapYagoIds = new FileOutputStream("MapYagoIds.properties");
            mapIdsYago = new FileOutputStream("MapIdsYago.properties");

            for(Map.Entry<String, Integer> entry : mapYagoToInt.entrySet()){
                String yagoId=entry.getKey();
                Integer id=entry.getValue();
                mapYagoIdsProperty.setProperty(yagoId, Integer.toString(id));
            }

            for (Map.Entry<Integer, String> entry : mapIntToYago.entrySet()){
                String yagoId=entry.getValue();
                Integer id=entry.getKey();
                mapIdsYagoProperty.setProperty(Integer.toString(id), yagoId);
            }
            mapYagoIdsProperty.store(mapYagoIds,null);
            mapIdsYagoProperty.store(mapIdsYago,null);
        } catch (IOException io) {
            io.printStackTrace();
        }

        finally {
            if (mapYagoIds != null || mapIdsYago!=null) {
                try {
                    mapYagoIds.close();
                    mapIdsYago.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

        }


    }

    public static int storeYagoToInt(String yagoId) {


        Integer i = mapYagoToInt.get(yagoId);
        if (i == null) {
            mapYagoToInt.put(yagoId, counter);

            i = counter;
            ++counter;
        }
        mapIntToYago.put(i, yagoId);

//        if (mapYagoIdsProperty.getProperty(yagoId)!=null) {
//            return Integer.parseInt(mapYagoIdsProperty.getProperty(yagoId));
//        }

        return i;
    }

    public static String getIntToYagoID(Integer i){
        return mapIntToYago.get(i);
    }

    public static int getYagoToInt(String yagoId){
        String id =propertyMapYagoInt.mapYagoIdsProperty.getProperty(yagoId);
        return Integer.parseInt(id);
    }
}
