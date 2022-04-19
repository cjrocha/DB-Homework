package ro.siit.dbases;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Logger;

public class ConnManager {
    private static Logger logger = Logger.getLogger("db_log.txt");

    /**
     * Imports driver and initializes it
     */
    public ConnManager(){
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    /**
     * Takes care of connecting to database
     * @param url - the dbase url, always start's with "jdbc:postgresql:"
     * @param user - the database username
     * @param password - the username password
     * @return - the connection to DB or throw error
     */
    // jdbc:postgresql://host:port/database
    public Connection getConnection(String url, String user, String password){
        try {
            Connection conn = DriverManager.getConnection(url, user, password);
            logger.info("All good! We are connected to DB!");
            return conn;
        } catch (SQLException e) {
            logger.severe("Something went wrong! We couldn't connect to DB!");
            e.printStackTrace();
        }
        return null;
    }
}
