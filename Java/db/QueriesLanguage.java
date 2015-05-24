package db;

import java.sql.SQLException;

/**
 * Created by Kobi on 23/05/2015.
 */
public class QueriesLanguage extends DBOperations {


    /**
     * insert language
     * return 	-1	- insert SQL error
     * 			0	- insert success, id retrieve error
     * 			id	- else
     */
    public static Integer create_language(String language_name)
    {
        try {
            insert("language", "`languageName`", language_name);
        } catch (SQLException e)
        {
            return (-1);
        }

        try {
            return (get_language_id(language_name));
        } catch (NumberFormatException | SQLException e) {
            return (0);
        }
    }
    public static int get_language_id(String language_name)
            throws SQLException
    {
        return (generic_id_getter("Language", language_name));
    }

}
