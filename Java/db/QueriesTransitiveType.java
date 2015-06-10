package db;


import parser.Facts_Parser;
import parser.TransitiveType_Parser;


public abstract class QueriesTransitiveType extends AbstractLoader {

    public TransitiveType_Parser transitiveTypeParser;
    public Facts_Parser factsParser;
    public QueriesTransitiveType(){

    }



    public void sync_update_tables() {
        transitiveTypeParser.parse_transitive_type(true);
    }




}
