package db;

import Utils.MapStringToInt;
import entities.FootballPlayer;
import entities.Location;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;

/**
 * Created by Kobi on 09/06/2015.
 */
public class QueriesFootballPlayer extends QueriesTransitiveType {
    @Override
    public HashMap<?, ?> getMap() {
        return transitiveTypeParser.getFootballPlayersMap();
    }

    @Override
    protected void set_perpared_statments(Connection db_conn) throws SQLException {
        insert = db_conn.prepareStatement("INSERT INTO football_player(idFootballPlayer) VALUES(?)");
    }

    @Override
    protected int create_statments(Object obj) throws SQLException {
        if (obj != null && (obj  instanceof FootballPlayer))
        {

            FootballPlayer footballPlayer=(FootballPlayer) obj;
            if (footballPlayer.getYagoID()!=null){return 0;}

            int idFootballPlayer;


            String yagoId=footballPlayer.getYagoID();
            idFootballPlayer= MapStringToInt.storeYagoToInt(yagoId);
            footballPlayer.setId(idFootballPlayer);

            insert.setInt(1,idFootballPlayer);
            insert.addBatch();
            return 1;
        }
    return 0;
    }
}
