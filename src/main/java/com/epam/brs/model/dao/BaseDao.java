package com.epam.brs.model.dao;

import com.epam.brs.model.entity.Entity;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

public interface BaseDao<K, T extends Entity> {

    Logger logger = LogManager.getLogger();

    List<T> findAll();

    T find(K id);

    boolean delete(K id);

    boolean delete(T entity);

    T update(T entity);

    default void close(Statement statement){
        try {
            if (statement != null) {
                statement.close();
            }
        } catch (SQLException e) {
            logger.error("ERROR while closing statement", e);
        }
    }

    default void close(Connection connection){
        try {
            if (connection != null) {
                connection.close();
            }
        } catch (SQLException e) {
            logger.error("ERROR while closing connection", e);
        }
    }
}
