package db;

import Utils.MapStringToInt;
import entities.Coach;
import entities.FootballPlayer;
import parser.TransitiveType_Parser;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;

/**
 * Created by Kobi on 09/06/2015.
 */
public class QueriesCoach extends QueriesTransitiveType {
    @Override
    public HashMap<?, ?> getMap() {
        return transitiveTypeParser.getCoachesMap();
    }

    @Override
    protected void set_perpared_statments(Connection db_conn) throws SQLException {
        insert = db_conn.prepareStatement("INSERT INTO coach(idCoach) VALUES(?)");

    }

    @Override
    protected int create_statments(Object obj) throws SQLException {
        if (obj != null && (obj  instanceof Coach))
        {

            Coach coach=(Coach) obj;
            if (coach.getYagoID()==null){return 0;}

            int idCoach;


            String yagoId=coach.getYagoID();
            idCoach= MapStringToInt.storeYagoToInt(yagoId);
            coach.setId(idCoach);

            insert.setInt(1,idCoach);
            insert.addBatch();
            return 1;
        }
        return 0;
    }
}
