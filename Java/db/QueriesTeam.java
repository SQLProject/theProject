package db;

import Utils.MapStringToInt;
import entities.Person;
import entities.Stadium;
import entities.Team;
import parser.TransitiveType_Parser;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Types;
import java.util.HashMap;

/**
 * Created by Kobi on 09/06/2015.
 */
public class QueriesTeam extends QueriesTransitiveType {
    @Override
    public HashMap<?, ?> getMap() {
        return transitiveTypeParser.getTeamsMap();
    }

    @Override
    protected void set_perpared_statments(Connection db_conn) throws SQLException {
        insert = db_conn.prepareStatement("INSERT INTO team(idStadium,stadiumName,idCityOfStadium) VALUES(?,?,?)");

    }

    @Override
    protected int create_statments(Object obj) throws SQLException {
        if (obj != null && (obj  instanceof Team))
        {
            Team team=(Team) obj;
            if (isValidTeam(team) ==false){return 0;}

            int idTeam;
            String teamName;

            String yagoId=team.getYagoID();
            idTeam= MapStringToInt.storeYagoToInt(yagoId);
            team.setId(idTeam);

            teamName=team.getName();
            insert.setInt(1,idTeam);
            insert.setString(2, teamName);

//
//            if (team.getSportfield()==null){
//                insert.setNull(3, Types.CHAR);}
//            else {insert.setString(3, team.getSportfield().getKind());}

            insert.addBatch();

            return 1;
        }
        return 0;
    }
    protected boolean isValidTeam(Team team){
        String yagoId=team.getYagoID();
        if (yagoId==null ||team.getName()==null ){
            return false;
        }
        return true;
    }
}
