package com.epam.brs.model.dao;

import com.epam.brs.model.dao.exception.DaoException;
import com.epam.brs.model.entity.Entity;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

public abstract class AbstractDao<K, T extends Entity> {
    protected Connection connection;

    public abstract List<T> findAll();

    public abstract T find(K id);

    public abstract boolean delete(K id);

    public abstract boolean delete(T entity);

    public abstract boolean add(T entity) throws DaoException;

    public abstract T update(T entity);

    public void close(Statement statement) throws DaoException {
        try {
            if (statement != null) {
                statement.close();
            }
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    public void setConnection(Connection connection) {
        this.connection = connection;
    }
}
