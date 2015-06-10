package confguration;

import java.io.IOException;
import java.util.Properties;

public class PropertyConfig {

    private Properties configFile = new Properties();



    public PropertyConfig()
    {
        try {
            configFile.load(getClass().getClassLoader().getResourceAsStream("config.properties"));
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    /** get the path of the file yagoSimpleTypes.ttl */
    public String get_yago_simple_types_path(){
        return configFile.getProperty("YagoSimpleTypesFilePath");
    }

    /** get the path of the file yagoSimpleTypes.ttl */
    public String get_yago_transitive_types_path(){
        return configFile.getProperty("YagoTransitiveTypePath");
    }

    /** get the path of the file yagoFacts.ttl */
    public String get_yago_facts_path(){
        return configFile.getProperty("YagoFactsFilePath");
    }
    
    /**get the path of the file yagoDateFacts.tsv**/
    public String get_yago_date_facts_path(){
    	return configFile.getProperty("YagoDateFactsFilePath");
    }

    /** get the path of the file yagoLiteralFacts.ttl */
    public String get_yago_literal_facts_path(){
        return configFile.getProperty("YagoLiteralFactsFilePath");
    }

    /** get the path of the file yagoWikipediaInfo.ttl */
    public String get_yago_wikipedia_path(){
        return configFile.getProperty("YagoWikipediaInfoFilePath");
    }

    public String get_yago_labels_path(){
        return configFile.getProperty("YagoMultilingualInfoFilePath");
    }

    /** get the server address */
    public String get_host_address(){
        return configFile.getProperty("Host");
    }

    /** get the port of the server */
    public String get_port(){
        return configFile.getProperty("Port");
    }

    /** get the dbName(Schema name) */
    public String get_db_name(){
        return configFile.getProperty("DbName");
    }

    /** get the user name */
    public String get_user_name(){
        return configFile.getProperty("UserName");
    }

    /** get user password */
    public String get_password(){
        return configFile.getProperty("Password");
    }

    /** get number of connections to create **/
    public int get_number_connection(){
        return Integer.parseInt(configFile.getProperty("NumOfConnections"));
    }

    /** get number of connections to create **/
    public  int get_window_height(){
        return Integer.parseInt(configFile.getProperty("WINDOW_HEIGHT"));
    }
    /** get number of connections to create **/
    public int get_window_width(){
        return Integer.parseInt(configFile.getProperty("WINDOW_WIDTH"));
    }

    public int get_default_small_limit() {
        return Integer.parseInt(configFile.getProperty("DEFAULT_SMALL_LIMIT"));
    }

    public int get_default_large_limit() {
        String x= configFile.getProperty("DEFAULT_BIG_LIMIT");
        return Integer.parseInt(x);
    }

    public int getMapYagoID(){
        return Integer.parseInt(configFile.getProperty("MapYagoID"));
    }

    public int get_admin_userid() {
        return Integer.parseInt(configFile.getProperty("ADMIN_USERNAME"));
    }




}
