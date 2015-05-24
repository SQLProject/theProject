package db;

import entities.Country;
import parser.TransitiveType_Parser;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;

public class QueriesCountry extends AbstractLoader {

    public HashMap<String, Country> entity_map;

    public QueriesCountry(){
        this.entityTableName="Country";
        //TODO : entity_map=fillCountriesWithParser
    }
    @Override
    protected void sync_update_tables() throws SQLException {

    }

    @Override
    protected void set_perpared_statments(Connection db_conn) throws SQLException {
        insert = db_conn.prepareStatement("INSERT INTO country(countryName) VALUES(?)");
    }

    @Override
    public int create_statments(Object obj) throws SQLException {
        if (entity_map.get(((String)obj).toString()) == null)
        {
			/*create and add*/
            insert.setString(1, ((String)obj).toString());
            insert.addBatch();
            return 1;
        }
        return 0;
    }
    protected void loadCountriesToDB(HashMap countryMap){
        set_perpared_statments();
    }

}
