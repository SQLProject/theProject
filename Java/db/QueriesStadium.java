package db;

import Utils.MapStringToInt;
import entities.Location;
import entities.Person;
import entities.Stadium;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Types;
import java.util.HashMap;

/**
 * Created by Kobi on 09/06/2015.
 */
public class QueriesStadium extends QueriesTransitiveType {
    public QueriesStadium() {
        super();
        this.entityTableName="Stadium";
    }

    @Override
    public HashMap<?, ?> getMap() {
        return transitiveTypeParser.getStadiumsMap();
    }

    @Override
    protected void set_perpared_statments(Connection db_conn) throws SQLException {
        insert = db_conn.prepareStatement("INSERT INTO stadium(idStadium,stadiumName,idCityOfStadium) VALUES(?,?,?)");
    }

    @Override
    protected int create_statments(Object obj) throws SQLException {
        if (obj != null && (obj  instanceof Stadium))
        {
            Stadium stadium=(Stadium) obj;
            if (isValidStadium(stadium)==false){return 0;}

            int idStadium;
            String stadiumName;

            String yagoId=stadium.getYagoID();
            idStadium= MapStringToInt.storeYagoToInt(yagoId);
            stadium.setId(idStadium);

            stadiumName=stadium.getName();
            insert.setInt(1,idStadium);
            insert.setString(2, stadiumName);

            if (stadium.getCity()==null){insert.setNull(3, Types.INTEGER);}
            else {insert.setInt(3,stadium.getCity().getId());}
            insert.addBatch();
            return 1;
        }
        return 0;
    }

    protected boolean isValidStadium(Stadium stadium){
        String yagoId=stadium.getYagoID();
        if (yagoId==null ||stadium.getName()==null ){
            return false;
        }
        return true;
    }
}
