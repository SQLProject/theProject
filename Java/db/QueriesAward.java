package db;

import Utils.MapStringToInt;
import entities.Award;
import entities.Location;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;

/**
 * Created by Kobi on 09/06/2015.
 */
public class QueriesAward extends QueriesTransitiveType {
    @Override
    public HashMap<?, ?> getMap() {
        return transitiveTypeParser.getAwardsMap();
    }

    @Override
    protected void set_perpared_statments(Connection db_conn) throws SQLException {
        insert = db_conn.prepareStatement("INSERT INTO award(idAward,awardName) VALUES(?,?)");

    }


    @Override
    protected int create_statments(Object obj) throws SQLException {
        if (obj != null && (obj  instanceof Award))
        {
            Award award=(Award) obj;
            if (isValidAward(award)==false){return 0;}

            int idAward;
            String awardName;

            String yagoId=award.getYagoId();
            idAward= MapStringToInt.storeYagoToInt(yagoId);
            award.setId(idAward);

            awardName=award.getName();
            insert.setInt(1,idAward);
            insert.setString(2, awardName);
            insert.addBatch();
            return 1;
        }
        return 0;
    }

    protected boolean isValidAward(Award award){
        String yagoId=award.getYagoId();
        if (yagoId==null ||award.getName()==null ){
            return false;
        }
        return true;
    }
}
