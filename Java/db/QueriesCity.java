package db;

import Utils.MapStringToInt;
import entities.City;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Types;
import java.util.HashMap;


public class QueriesCity extends QueriesLocation {


    public QueriesCity() {
        super();
        this.entityTableName="City";
    }

    @Override
    public HashMap<String, City> getMap() {
        return transitiveTypeParser.getCitiesMap();
    }


    @Override
    protected void set_perpared_statments(Connection db_conn) throws SQLException {
        insert = db_conn.prepareStatement("INSERT INTO city(idCity,cityName,idCountryOfCity) VALUES(?,?,?)");
    }
    @Override
    protected int create_statments(Object obj) throws SQLException {
        if (obj != null && (obj  instanceof City))
        {
            City city=(City) obj;
            if (isValidLocation(city)==false){return 0;}

            int idCity;
            String cityName;

            String yagoId=city.getYagoID();
            idCity= MapStringToInt.storeYagoToInt(yagoId);
            city.setId(idCity);

            cityName=city.getName();
            insert.setInt(1, idCity);
            insert.setString(2, cityName);
            if (city.getCountry()==null){
                insert.setNull(3, Types.INTEGER);
            }
            else {
                String yagoIdCountryOfCity=city.getCountry().getYagoID();
                int idCountryOfCity =MapStringToInt.storeYagoToInt(yagoIdCountryOfCity);
                insert.setInt(3, idCountryOfCity);
            }


            insert.addBatch();
            return 1;
        }
        return 0;
    }
}
