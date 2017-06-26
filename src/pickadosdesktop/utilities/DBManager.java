/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pickadosdesktop.utilities;

import java.sql.Connection;
import java.sql.DriverManager;
import org.apache.log4j.Logger;

/**
 *
 * @author user
 */
public final class DBManager {

    private static Connection conn = null;
    private final static Logger logger = Logger.getLogger(DBManager.class);

    public static Connection getConnection() {
        logger.info("Trying to connect to database");
        try {
            Class.forName("org.sqlite.JDBC");
            conn = DriverManager.getConnection("jdbc:sqlite:test.db");
            logger.debug("Opened database successfully");
            logger.info("Opened database successfully");
        } catch (Exception e) {
            logger.error("Error while connecting to database: " + e.getClass().getName() + ": " + e.getMessage());
        }
        return conn;
    }
}
