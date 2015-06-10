package db;

import Utils.MapStringToInt;
import entities.Event;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Types;
import java.util.HashMap;

/**
 * Created by Kobi on 09/06/2015.
 */
public class QueriesEvent extends QueriesTransitiveType {
    public QueriesEvent() {
        super();
        this.entityTableName="Event";
    }

    @Override
    public HashMap<?, ?> getMap() {
        return transitiveTypeParser.getEventsMap();
    }

    @Override
    protected void set_perpared_statments(Connection db_conn) throws SQLException {
        insert = db_conn.prepareStatement("INSERT INTO event(idEvent,eventName,sportField,idLocation) VALUES(?,?,?,?)");
    }

    @Override
    protected int create_statments(Object obj) throws SQLException {
        if (obj != null && (obj  instanceof Event))
        {
            Event event=(Event) obj;
            if (isValidEvent(event)==false){return 0;}

            int idEvent;
            String eventName;

            String yagoId=event.getYagoID();
            idEvent= MapStringToInt.storeYagoToInt(yagoId);
            event.setId(idEvent);

            eventName=event.getName();
            insert.setInt(1,idEvent);
            insert.setString(2, eventName);



            if (event.getSportField()==null){
                insert.setNull(3, Types.CHAR);}
            else {insert.setString(3, event.getSportField().getKind());}

            if (event.getLocation()==null){insert.setNull(4, Types.INTEGER);}
            else {insert.setInt(4, event.getLocation().getId());}



            insert.addBatch();

            return 1;
        }
        return 0;
    }

    protected boolean isValidEvent(Event event){
        String yagoId=event.getYagoID();
        if (yagoId==null ||event.getName()==null ){
            return false;
        }
        return true;
    }
}
