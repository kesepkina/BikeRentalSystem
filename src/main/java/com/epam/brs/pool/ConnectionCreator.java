package com.epam.brs.pool;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class ConnectionCreator {

    private static final Logger logger = LogManager.getLogger();
    private static final Properties properties = new Properties();
    private static final String DATABASE_URL;
    private static final int POOL_SIZE;

    private static final String PROPERTIES_PATH = "properties/database.properties";
    private static final String DB_DRIVER = "db.driver";
    private static final String DB_URL = "db.url";
    private static final String DB_POOL_SIZE = "pool.size";

    static {
        try{
            properties.load(new FileReader(PROPERTIES_PATH));
            String driverName = (String) properties.get(DB_DRIVER);
            Class.forName(driverName);
        } catch (IOException | ClassNotFoundException e) {
            logger.fatal(e);
        }
        DATABASE_URL = (String) properties.get(DB_URL);
        POOL_SIZE = (Integer) properties.get(DB_POOL_SIZE);
    }

    private ConnectionCreator(){}
    public static Connection createConnection() throws SQLException {
        return DriverManager.getConnection(DATABASE_URL, properties);
    }

    public static int getPoolSize() {
        return POOL_SIZE;
    }
}
