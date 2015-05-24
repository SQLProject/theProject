package db;

import entities.Country;
import entities.Location;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Locale;
import java.util.Set;


public class QueriesLocation extends QueriesTransitiveType{



    @Override
    public HashMap<?, ?> getMap() {
        return parser.getLocationMap();
    }

    @Override
    protected void set_perpared_statments(Connection db_conn) throws SQLException {
        insert = db_conn.prepareStatement("INSERT INTO location(locationName) VALUES(?)");
    }

    @Override
    protected int create_statments(Object obj) throws SQLException {
        if (obj != null && (obj  instanceof Location))
        {
            Location country=(Location) obj;
            insert.setString(1, country.getName());
            insert.addBatch();
            return 1;
        }
        return 0;
    }
}
