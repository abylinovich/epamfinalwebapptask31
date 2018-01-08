package by.epam.final_project.dao.connectionpool;

import by.epam.final_project.dao.exception.ConnectionPoolException;
import org.apache.log4j.Logger;

import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;


public class ConnectionPool {

    private Logger logger = Logger.getLogger(ConnectionPool.class);

    private static final String DATABASE_DRIVER = "com.mysql.jdbc.Driver";
    private static final String DATABASE_DOMAIN = "DOMAIN_NAME";
    private static final String DATABASE_PORT = "DATABASE_PORT";
    private static final String DATABASE_NAME = "OUT_OF_MEMORY_PROJECT_DATABASE";
    private static final String DATABASE_LOGIN = "MYSQL_LOGIN";
    private static final String DATABASE_PASSWORD = "MYSQL_PASSWORD";
    private static final int DATABASE_CONNECTION_POOL_CAPACITY = 5;


    private String url;
    private String username;
    private String password;
    private int capacity;
    private BlockingQueue<Connection> availableConnections;
    private BlockingQueue<Connection> usedConnections;


    private static volatile ConnectionPool connectionPool = new ConnectionPool();

    public static ConnectionPool getConnectionPool() {
        return connectionPool;
    }

    public void init() throws ConnectionPoolException {
        String domain = System.getenv(DATABASE_DOMAIN);
        String port = System.getenv(DATABASE_PORT);
        String name = System.getenv(DATABASE_NAME);

        url = "jdbc:mysql://" + domain + ":" + port + "/" + name;
        username = System.getenv(DATABASE_LOGIN);
        password = System.getenv(DATABASE_PASSWORD);
        capacity = DATABASE_CONNECTION_POOL_CAPACITY;
        availableConnections = new LinkedBlockingQueue<>(capacity);
        usedConnections = new LinkedBlockingQueue<>(capacity);

        try {
            Class.forName(DATABASE_DRIVER);
        } catch (ClassNotFoundException e) {
            throw new ConnectionPoolException("Cannot load driver. Class not found.", e);
        }

        for(int i = 1; i <= capacity; i++) {
            try {
                Connection connection = DriverManager.getConnection(url, username, password);
                availableConnections.put(connection);
            } catch (InterruptedException e) {
                throw new ConnectionPoolException("Cannot retrieve connection.", e);
            } catch (SQLException e) {
                throw new ConnectionPoolException("Cannot retrieve new connection to database", e);
            }
            logger.debug("New connection #" + i + " was created.");
        }

        logger.info("Connection pool has been successfully initialized.");
    }

    public Connection getConnection() throws ConnectionPoolException {
        Connection connection;
        try {
            connection = availableConnections.take();
            usedConnections.put(connection);
        } catch (InterruptedException e) {
            throw new ConnectionPoolException("Cannot get connection.", e);
        }
        logger.debug("Connection was given to caller.");
        return connection;
    }

    public void close(Connection connection, Statement statement, ResultSet resultSet) throws ConnectionPoolException {
        if(resultSet != null) {
            try {
                resultSet.close();
            } catch (SQLException e) {
                logger.error("Cannot close ResultSet.", e);
            }
        }
        close(connection, statement);
    }

    public void close(Connection connection, Statement statement) throws ConnectionPoolException {
        if(statement != null) {
            try {
                statement.close();
            } catch (SQLException e) {
                logger.error("Cannot close Statement.", e);
            }
        }
        close(connection);
    }

    public void close(Connection connection) throws ConnectionPoolException {
        if (connection == null) {
            throw new ConnectionPoolException("Connection is null.");
        }

        boolean isOwnConnection = usedConnections.remove(connection);
        if(!isOwnConnection) {
            throw new ConnectionPoolException("Attempt to close strange connection.");
        }

        try {
            availableConnections.put(connection);
        } catch (InterruptedException e) {
            throw new ConnectionPoolException("Cannot return connection into connection pool.", e);
        }
        logger.debug("Connection was returned into connection pool successfully.");
    }

    public void destroy() {
        closeConnections(availableConnections);
        closeConnections(usedConnections);
        logger.info("Connection pool has been successfully destroyed.");
    }

    private void closeConnections(BlockingQueue<Connection> queue) {
        for(Connection connection : queue) {
            try {
                connection.close();
            } catch (SQLException e) {
                logger.error("Cannot close connection.", e);
            }
        }
    }

}
