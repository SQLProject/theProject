package tests;

import db.AbstractLoader;
import db.QueriesCountry;
import db.QueriesLocation;
import db.QueriesTransitiveType;

import java.sql.SQLException;
import java.util.Collection;

/**
 * Created by Kobi on 23/05/2015.
 */
public class QueriesLocationTest {


    public static void main(String args[]) throws SQLException {
        System.out.printf("hey");
        QueriesLocation locationLoader=new QueriesLocation();
        QueriesCountry countryLoader=new QueriesCountry();
        locationLoader.load_batch((Collection<?>) locationLoader.getMap().values());
        countryLoader.load_batch((Collection<?>) countryLoader.getMap().values());






    }
}
