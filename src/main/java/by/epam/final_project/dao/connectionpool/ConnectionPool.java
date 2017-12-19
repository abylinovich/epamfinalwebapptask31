package by.epam.final_project.dao.connectionpool;

import by.epam.final_project.dao.exception.ConnectionPoolException;
import org.apache.log4j.Logger;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Properties;
import java.util.Set;

class ConnectionPool {

    private Logger logger = Logger.getLogger(ConnectionPool.class.getSimpleName());

    private static final String PROPERTIES_FILE_NAME = "environment/prop_local.properties";
    private static final String DATABASE_DRIVER_PROPERTY_KEY = "dataBase.driverClassName";
    private static final String DATABASE_URL_PROPERTY_KEY = "dataBase.url";
    private static final String DATABASE_POOL_CAPACITY_PROPERTY_KEY = "dataBase.poolCapacity";
    private static final String DATABASE_USERNAME_PROPERTY_KEY = "dataBase.username";
    private static final String DATABASE_PASSWORD_PROPERTY_KEY = "dataBase.password";


    private final Set<Connection> availableConnections = new HashSet<>();
    private final Set<Connection> usedConnections = new HashSet<>();
    private String url;
    private String username;
    private String password;
    private int capacity;

    ConnectionPool() {
    }

    void init()  throws ConnectionPoolException {
        Properties properties = new Properties();
        try {
            properties.load(getClass().getClassLoader().getResourceAsStream(PROPERTIES_FILE_NAME));
        } catch (IOException e) {
            throw new ConnectionPoolException("Cannot read properties file: '" + PROPERTIES_FILE_NAME + "'.");
        }
        url = properties.getProperty(DATABASE_URL_PROPERTY_KEY);
        capacity = Integer.valueOf(properties.getProperty(DATABASE_POOL_CAPACITY_PROPERTY_KEY));
        username = properties.getProperty(DATABASE_USERNAME_PROPERTY_KEY);
        password = properties.getProperty(DATABASE_PASSWORD_PROPERTY_KEY);
        String driver = properties.getProperty(DATABASE_DRIVER_PROPERTY_KEY);
        try {
            Class.forName(driver);
        } catch (ClassNotFoundException e) {
            logger.error("Database driver class not found.", e);
            throw new ConnectionPoolException("Cannot load driver.", e);
        }
        logger.info("Connection pool initialization complete. URL='" + url + "', username='" + username + "', capacity='" + capacity + "'.");
    }

    public synchronized Connection getConnection() throws ConnectionPoolException {
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

    public synchronized void close(Connection connection) throws ConnectionPoolException {
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
