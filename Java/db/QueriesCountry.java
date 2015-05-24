package db;


import entities.Country;
import entities.Location;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;


public class QueriesCountry extends QueriesLocation {



    public QueriesCountry(){
        super();

    }

    @Override
    public HashMap<String, Country> getMap() {
        return parser.getCountriesMap();
    }


    @Override
    protected void set_perpared_statments(Connection db_conn) throws SQLException {
        insert = db_conn.prepareStatement("INSERT INTO country(countryName) VALUES(?)");
    }


}
