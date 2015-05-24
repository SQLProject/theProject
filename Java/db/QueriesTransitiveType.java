package db;


import parser.TransitiveType_Parser;

import java.sql.SQLException;
import java.util.HashMap;


public abstract class QueriesTransitiveType extends AbstractLoader {

    public TransitiveType_Parser parser;

    public QueriesTransitiveType(){
        parser=new TransitiveType_Parser();

    }



    public void sync_update_tables() {
        parser.parse_transitive_type();
    }




}
