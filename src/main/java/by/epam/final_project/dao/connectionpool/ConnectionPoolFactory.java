package by.epam.final_project.dao.connectionpool;

import by.epam.final_project.dao.exception.ConnectionPoolException;


public class ConnectionPoolFactory {

    private static final ConnectionPoolFactory connectionPoolFactory = new ConnectionPoolFactory();
    private final ConnectionPool connectionPool = new ConnectionPool();

    private ConnectionPoolFactory() {
    }

    public void init() throws ConnectionPoolException {
        connectionPool.init();
    }

    public static ConnectionPoolFactory getInstance() {
        return connectionPoolFactory;
    }

    public ConnectionPool getConnectionPool() {
        return connectionPool;
    }

}
