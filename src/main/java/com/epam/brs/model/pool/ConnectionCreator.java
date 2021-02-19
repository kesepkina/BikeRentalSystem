package com.epam.brs.model.pool;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

class ConnectionCreator {

    private static final Logger logger = LogManager.getLogger();
    private static final Properties properties = new Properties();
    private static final String DATABASE_URL;
    private static final int POOL_SIZE;
    private static final String PROPERTIES_PATH ="properties/database.properties";
    private static final String DB_DRIVER = "db.driver";
    private static final String DB_URL = "db.url";
    private static final String DB_POOL_SIZE = "pool.size";

    static {
        try {
            InputStream inputStream = ConnectionCreator.class.getClassLoader().getResourceAsStream(PROPERTIES_PATH);
            properties.load(inputStream);
        } catch (FileNotFoundException e) {
            logger.fatal("Properties file not found", e);
            throw new RuntimeException("FATAL ERROR Properties file not found", e);
        } catch (IOException e) {
            logger.fatal("Reader of properties files not loaded", e);
            throw new RuntimeException("FATAL ERROR Reader of properties files not loaded", e);
        }
        try {
            String driverName = (String) properties.get(DB_DRIVER);
            Class.forName(driverName);
        } catch (ClassNotFoundException e) {
            logger.fatal("Driver class not found", e);
            throw new RuntimeException("Driver class not found", e);
        }
        DATABASE_URL = (String) properties.get(DB_URL);
        POOL_SIZE = Integer.valueOf((String) properties.get(DB_POOL_SIZE));
    }

    private ConnectionCreator(){}
    public static Connection createConnection() throws ConnectionCreatorException {
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(DATABASE_URL, properties);
        } catch (SQLException e) {
            logger.error("Exception while getting connection", e);
            throw new ConnectionCreatorException("Exception while getting connection", e);
        }
        return connection;
    }

    static int getPoolSize() {
        return POOL_SIZE;
    }
}
