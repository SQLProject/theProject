package db;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.sql.BatchUpdateException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;

import confguration.PropertyConfig;
import db.DBOperations;
import entities.Location;
import parser.TransitiveType_Parser;
import parser.abstract_parser;


/**
 * A Bussiness Logic class for import of data. Recieves parsed and normalized
 * data in sets and maps, updates db according to existing items, types of
 * import (insert, update), etc.
 **/
public abstract class AbstractLoader extends DBOperations {

    public static final int BATCH_SIZE = 1000; /* how many statements in batch */
    protected String entityTableName; /* the table updated */
    private int taskSize = 0; /* total size of load entity */
    protected PreparedStatement insert;
    protected PropertyConfig config;

    /* setters */
    public AbstractLoader() {
        config = new PropertyConfig();
    }

    /* getters */
    public int get_task_size() {
        return this.taskSize;

    }
    abstract public HashMap<?, ?> getMap();

    abstract protected void sync_update_tables() throws SQLException;

    abstract protected void set_perpared_statments(Connection db_conn)
            throws SQLException;

    abstract protected int create_statments(Object obj) throws SQLException;

    /**
     * performs a batch update, with
     *
     * @throws SQLException
     *             - if some connection error occured, we catch it at the main
     *             importer class, and
     * **/
    public void load_batch(Collection<?> update_entity) throws SQLException {
        int batch_count = 0;
        int fail_count = 0;
        int progress = 0;
        taskSize = update_entity.size();
        Connection db_conn = null;
        Iterable<Object> update_entities;
        try {
			/* update relevant tables with db */
            sync_update_tables();

			/* set prepared statments against connection */
            db_conn = getConnection();
            set_perpared_statments(db_conn);
            for (Object obj : update_entity) {
//                if (Caller.is_thread_terminated())
//                    break;
                progress++;
                if (progress % 10000 == 0)
                    this.notifyListeners(this, "progress", "0", (new Integer(
                            progress)).toString());
                batch_count += create_statments(obj);
                if (batch_count > 0 && batch_count % BATCH_SIZE == 0)
                    fail_count += execute_batches(batch_count);
            }
            fail_count += execute_batches(batch_count % BATCH_SIZE);
			/* execute remainder - if not terminated */
//            if (!Caller.is_thread_terminated()) {
//                fail_count += execute_batches(batch_count % BATCH_SIZE);
//            }
        } catch (SQLException ex) {
            throw ex;
        } catch (Exception ex) {
            System.out.println("Error in Batch, Attempt to continue");
        } finally {
            try {
                if (db_conn != null)
                    db_conn.close();
            } catch (SQLException se) {
                se.printStackTrace();
            }
        }

        StringBuilder sb = new StringBuilder();
        sb.append("loading to table: ");
        sb.append(this.entityTableName + "\n");
        sb.append("\tEntites Passed:  ");
        sb.append(this.taskSize + "\n");
        sb.append("\tRequired statements: ");
        sb.append(batch_count + "\n");
        sb.append("\tFailed statements: ");
        sb.append(fail_count + "\n");
        System.out.println(sb.toString());

    }

    /**
     *
     * @param stmt
     *            - the batch to perform
     * @return int fail_count - the amount of failed statements executes a bath
     *         statement, and handles returns fail count in all possible
     *         scenarios
     * @throws SQLException
     */
    protected int execute_batch(PreparedStatement stmt, int stmt_batch_size)
            throws SQLException {
        int fail_count = 0;
        try {
            stmt.executeBatch();
        } catch (BatchUpdateException batch_ex) {
			/* at least one command failed, see how many */
            int[] batch_results = batch_ex.getUpdateCounts();
            int i = 0;
			/* driver stopped execution after faulty statement */
            if (batch_results.length != stmt_batch_size)
                fail_count += stmt_batch_size - batch_results.length;
            else { /* driver tried executing all commands - find fails */
                for (i = 0; i < batch_results.length; i++)
                    if (batch_results[i] == PreparedStatement.EXECUTE_FAILED)
                        fail_count++;
            }
            batch_ex.printStackTrace();
        }
        return fail_count;
    }

    /**
     * executes user specific batched statements. overridden by extender
     * */
    public int execute_batches(int batch_size) throws SQLException {
        int fail_count = 0;
        fail_count += execute_batch(this.insert, batch_size);
        return fail_count;
    }

	/* helper methods, listeners */

    /**
     * We allow clients to register to property change events, that monitor the
     * current file processing. This helps us display the import progress
     **/
    private List<PropertyChangeListener> listener = new ArrayList<PropertyChangeListener>();

    /**
     * notifies all listeners on event, mostly progress in parsing
     *
     * @param object
     * @param property
     * @param oldValue
     * @param newValue
     */
    private void notifyListeners(Object object, String property,
                                 String oldValue, String newValue) {
        for (PropertyChangeListener name : listener) {
            name.propertyChange(new PropertyChangeEvent(this, property,
                    oldValue, newValue));
        }
    }

    public void addChangeListener(PropertyChangeListener newListener) {
        listener.add(newListener);
    }

    public void removeChangeListener(PropertyChangeListener newListener) {
        listener.remove(newListener);
    }

}
