package ua.kpi.ip31.jee.gunawardana;

import ua.kpi.ip31.jee.gunawardana.connection.ConnectionManager;
import ua.kpi.ip31.jee.gunawardana.connection.HikariConnectionManager;
import ua.kpi.ip31.jee.gunawardana.repository.ComicsRepository;
import ua.kpi.ip31.jee.gunawardana.repository.JdbcComicsRepository;

/**
 * Entry point for getting dependencies.
 *
 * @author Ruslan Gunawardana
 */
public final class ComicsStoreServiceLocator {
    public static ConnectionManager getConnectionManager() {
        return new HikariConnectionManager("/postgresql-ds.properties");
    }

    public static ComicsRepository getComicsRepository() {
        return new JdbcComicsRepository(getConnectionManager());
    }
}
