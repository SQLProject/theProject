package db;

import Utils.MapStringToInt;
import entities.Location;
import entities.Person;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Types;
import java.util.HashMap;

/**
 * Created by Kobi on 09/06/2015.
 */
public class QueriesPerson extends QueriesTransitiveType {

    public QueriesPerson() {
        super();
        this.entityTableName="Person";
    }

    @Override
    public HashMap<?, ?> getMap() {
        return transitiveTypeParser.getPersonMap();
    }

    @Override
    protected void set_perpared_statments(Connection db_conn) throws SQLException {
        insert = db_conn.prepareStatement("INSERT INTO person(idPerson,personName,idCurrentCity,birthYear,idbirthCity" +
                ",sportField) VALUES(?,?,?,?,?,?)");
    }

    @Override
    protected int create_statments(Object obj) throws SQLException {
        if (obj != null && (obj  instanceof Person))
        {
            Person person=(Person) obj;
            if (isValidPerson(person)==false){return 0;}

            int idPerson;
            String personName;

            String yagoId=person.getYagoID();
            idPerson= MapStringToInt.storeYagoToInt(yagoId);
            person.setId(idPerson);

            personName=person.getName();
            insert.setInt(1,idPerson);
            insert.setString(2, personName);

            if (person.getCurrentCity()==null){insert.setNull(3, Types.INTEGER);}
            else {insert.setInt(3,person.getCurrentCity().getId());}

            if (person.getBirthDate()==null){insert.setNull(4, Types.INTEGER);}
            else {insert.setInt(4,person.getBirthDate().getYear());}

            if (person.getBirthPlace()==null){insert.setNull(5, Types.INTEGER);}
            else {insert.setInt(5, person.getBirthPlace().getId());}

            if (person.getSportfield()==null){
                insert.setNull(6, Types.CHAR);}
            else {insert.setString(6, person.getSportfield().getKind());}

            insert.addBatch();

            return 1;
        }
        return 0;
    }
    protected boolean isValidPerson(Person person){
        String yagoId=person.getYagoID();
        if (yagoId==null ||person.getName()==null ){
            return false;
        }
        return true;
    }
}
