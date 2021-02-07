package com.epam.brs.pool;

import com.epam.brs.exceptions.ConnectionPoolException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayDeque;
import java.util.Optional;
import java.util.Queue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;

public enum ConnectionPool {

    INSTANCE;

    private static final Logger logger = LogManager.getLogger();
    private final BlockingQueue<ProxyConnection> freeConnections;
    private final Queue<ProxyConnection> busyConnections;

    private static final int DEFAULT_POOL_SIZE = 32;

    ConnectionPool() {
        freeConnections = new LinkedBlockingDeque<>(ConnectionCreator.getPoolSize());
        busyConnections = new ArrayDeque<>();
    }
    public Optional<Connection> getConnection() throws InterruptedException {
        ProxyConnection connection = null;
        try {
            connection = freeConnections.take();
            busyConnections.offer(connection);
        } catch (InterruptedException e) {
            logger.error("Exception by taking next free connection", e);
            throw e;
        }
        return Optional.ofNullable(connection);
    }

    public void releaseConnection(Connection connection) {
        if (connection.getClass() != ProxyConnection.class) {
            logger.error("Unreleasable connection {}", connection);
        }
        ProxyConnection proxyConnection = (ProxyConnection) connection;
        busyConnections.remove(proxyConnection);
        freeConnections.offer(proxyConnection);
    }

    public void destroyPool() throws ConnectionPoolException, InterruptedException {
        for (int i = 0; i < ConnectionCreator.getPoolSize(); i++) {
            try {
                freeConnections.take().completelyClose();
            } catch (SQLException throwable) {
                throw new ConnectionPoolException("Exception by closing free connections", throwable);
            } catch (InterruptedException e) {
                logger.error("Exception by taking next free connection", e);
                throw e;
            }
        }
        deregisterDrivers();
    }

    private void deregisterDrivers() {
        DriverManager.getDrivers().asIterator().forEachRemaining(driver -> {
            try {
                DriverManager.deregisterDriver(driver);
            } catch (SQLException e) {
                logger.error("Database access error", e);
            }
        });
    }
}
