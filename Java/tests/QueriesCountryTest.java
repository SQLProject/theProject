package tests;

import db.QueriesCountry;
import parser.TransitiveType_Parser;

import java.sql.SQLException;

/**
 * Created by Kobi on 23/05/2015.
 */
public class QueriesCountryTest {

    static QueriesCountry queriesCountry=new QueriesCountry();

    public static void main(String args[]) throws SQLException {
        System.out.printf("hey");


        queriesCountry.execute_batches(10);


    }
}
