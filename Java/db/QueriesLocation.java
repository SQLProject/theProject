package db;

import entities.Location;
import Utils.MapStringToInt;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;


public class QueriesLocation extends QueriesTransitiveType{


    public QueriesLocation() {
        super();
        this.entityTableName="Location";
    }

    @Override
    public HashMap<?, ?> getMap() {
        return transitiveTypeParser.getLocationMap();
    }

    @Override
    protected void set_perpared_statments(Connection db_conn) throws SQLException {
        insert = db_conn.prepareStatement("INSERT INTO location(idLocation,locationName) VALUES(?,?)");
    }

    @Override
    protected int create_statments(Object obj) throws SQLException {
        if (obj != null && (obj  instanceof Location))
        {
            Location location=(Location) obj;
            if (isValidLocation(location)==false){return 0;}

            int idLocation;
            String locationName;

            String yagoId=location.getYagoID();
            idLocation= MapStringToInt.storeYagoToInt(yagoId);
            location.setId(idLocation);

            locationName=location.getName();
            insert.setInt(1,idLocation);
            insert.setString(2, locationName);
            insert.addBatch();
            return 1;
        }
        return 0;
    }

    protected boolean isValidLocation(Location location){
        String yagoId=location.getYagoID();
        if (yagoId==null ||location.getName()==null ){
            return false;
        }
        return true;
    }
}
