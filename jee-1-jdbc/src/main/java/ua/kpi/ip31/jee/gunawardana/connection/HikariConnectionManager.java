package ua.kpi.ip31.jee.gunawardana.connection;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * {@link ConnectionManager} implementation that is based on HikariCP Connection Pool.
 * Thread-safe. Immutable.
 *
 * @author Ruslan Gunawardana
 */
public final class HikariConnectionManager implements ConnectionManager {
    private HikariDataSource dataSource;

    public HikariConnectionManager(String propertyFile) {
        HikariConfig config = new HikariConfig(propertyFile);
        dataSource = new HikariDataSource(config);
    }

    @Override
    public Connection getConnection() throws SQLException {
        return dataSource.getConnection();
    }
}
