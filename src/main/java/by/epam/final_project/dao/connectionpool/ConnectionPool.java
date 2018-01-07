package by.epam.final_project.dao.connectionpool;

import by.epam.final_project.dao.exception.ConnectionPoolException;
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.HashSet;
import java.util.Set;

public class ConnectionPool {

    private Logger logger = Logger.getLogger(ConnectionPool.class);

    private static final String DATABASE_DRIVER = "com.mysql.jdbc.Driver";
    private static final String DATABASE_DOMAIN = "DOMAIN_NAME";
    private static final String DATABASE_PORT = "DATABASE_PORT";
    private static final String DATABASE_NAME = "OUT_OF_MEMORY_PROJECT_DATABASE";
    private static final String DATABASE_LOGIN = "MYSQL_LOGIN";
    private static final String DATABASE_PASSWORD = "MYSQL_PASSWORD";
    private static final int DATABASE_CONNECTION_POOL_CAPACITY = 5;


    private final Set<Connection> availableConnections = new HashSet<>();
    private final Set<Connection> usedConnections = new HashSet<>();
    private String url;
    private String username;
    private String password;
    private int capacity;

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

        try {
            Class.forName(DATABASE_DRIVER);
        } catch (ClassNotFoundException e) {
            throw new ConnectionPoolException("Cannot load driver. Class not found.", e);
        }
        logger.info("Connection pool initialization successful. URL='" + url + "', username='" + username + "', capacity='" + capacity + "'.");
    }

    public Connection getConnection() throws ConnectionPoolException {
        Connection connection;
        if(availableConnections.size() > 0) {
            connection = availableConnections.iterator().next();
            availableConnections.remove(connection);
            usedConnections.add(connection);
            return connection;
        }
        if(usedConnections.size() == capacity) {
            throw new ConnectionPoolException("Connection pool has no available connections.");
        }
        connection = retrieveConnection();
        usedConnections.add(connection);
        logger.debug("Connection is given to caller.");
        return connection;
    }

    public void close(Connection connection) throws ConnectionPoolException {
        close(connection, null, null);
    }

    public void close(Connection connection, Statement statement) throws ConnectionPoolException {
        close(connection, statement, null);
    }

    public void close(Connection connection, Statement statement, ResultSet resultSet) throws ConnectionPoolException {
        // TODO: 04.01.2018
        if (connection == null) {
            throw new ConnectionPoolException("Connection is null");
        }
        boolean isRemoved = usedConnections.remove(connection);
        if(!isRemoved) {
            throw new ConnectionPoolException("Current connection is not from this connection pool.");
        }
        if(availableConnections.size() <= (capacity / 2)) {
            availableConnections.add(connection);
            logger.debug("Connection returned into connection pool.");
        } else {
            try {
                connection.close();
            } catch (SQLException e) {
                logger.error("Connection wasn`t closed.");
                throw new ConnectionPoolException("Exception due closing connection.");
            }
            logger.debug("Connection was closed.");
        }
    }

    private Connection retrieveConnection() throws ConnectionPoolException {
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(url, username, password);
        } catch (SQLException e) {
            logger.error("Cannot establish connection to database.", e);
            throw new ConnectionPoolException("Cannot retrieve new connection to database");
        }
        logger.debug("New connection created.");
        return conn;
    }

}
