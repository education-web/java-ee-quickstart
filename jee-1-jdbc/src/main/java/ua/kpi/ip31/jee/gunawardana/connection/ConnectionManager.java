package ua.kpi.ip31.jee.gunawardana.connection;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * Provides an access to relational datasource via JDBC {@link Connection} instances.
 *
 * @author Ruslan Gunawardana
 */
public interface ConnectionManager {
    /**
     * Returns a relational datasource connection
     *
     * @return relational datasource connection
     * @throws SQLException on connection timeout
     */
    Connection getConnection() throws SQLException;
}
