package ua.kpi.ip31.jee.gunawardana.repository;

import lombok.SneakyThrows;
import ua.kpi.ip31.jee.gunawardana.connection.ConnectionManager;
import ua.kpi.ip31.jee.gunawardana.model.Comics;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Repository implementation grants access to {@link Comics} in the PostgreSQL RDBMS.
 * Immutable. Thread-safe.
 */
public final class PostgresJdbcComicsRepository implements ComicsRepository {
    private final ConnectionManager connectionManager;

    public PostgresJdbcComicsRepository(ConnectionManager connectionManager) {
        this.connectionManager = connectionManager;
    }

    @SneakyThrows(SQLException.class)
    @Override
    public Optional<Comics> findById(Long id) {
        String sql = "SELECT id, title, publisher, author, num, price FROM comics WHERE id = ?";
        try (Connection connection = connectionManager.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setLong(1, id);
            try (ResultSet rs = statement.executeQuery()) {
                return rs.next() ? Optional.of(extractOne(rs)) : Optional.empty();
            }
        }
    }

    @SneakyThrows(SQLException.class)
    @Override
    public List<Comics> findAll() {
        String sql = "SELECT id, title, publisher, author, num, price FROM comics";
        try (Connection connection = connectionManager.getConnection();
             Statement statement = connection.createStatement();
             ResultSet rs = statement.executeQuery(sql)) {
            List<Comics> result = new ArrayList<>();
            while (rs.next()) result.add(extractOne(rs));
            return result;
        }
    }

    @SneakyThrows(SQLException.class)
    @Override
    public List<Comics> findByPublisher(String publisher) {
        String sql = "SELECT id, title, publisher, author, num, price FROM comics WHERE publisher = ?";
        try (Connection connection = connectionManager.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, publisher);
            try (ResultSet rs = statement.executeQuery()) {
                List<Comics> result = new ArrayList<>();
                while (rs.next()) result.add(extractOne(rs));
                return result;
            }
        }
    }

    @SneakyThrows(SQLException.class)
    @Override
    public Comics save(Comics comics) {
        String sql = "INSERT INTO comics (title, publisher, author, num, price)" +
                "VALUES (?, ?, ?, ?, ?) RETURNING id";
        try (Connection connection = connectionManager.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, comics.getTitle());
            statement.setString(2, comics.getPublisher());
            statement.setString(3, comics.getAuthor());
            statement.setInt(4, comics.getNumber());
            statement.setBigDecimal(5, comics.getPrice());
            try (ResultSet rs = statement.executeQuery()) {
                rs.next();
                connection.commit();
                return comics.withId(rs.getLong(1));
            }
        }
    }

    @SneakyThrows(SQLException.class)
    @Override
    public void update(Comics comics) {
        String sql = "UPDATE comics SET title = ?, publisher = ?, author = ?, num = ?, price = ? WHERE id = ?";
        try (Connection connection = connectionManager.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, comics.getTitle());
            statement.setString(2, comics.getPublisher());
            statement.setString(3, comics.getAuthor());
            statement.setInt(4, comics.getNumber());
            statement.setBigDecimal(5, comics.getPrice());
            statement.setLong(6, comics.getId());
            statement.executeUpdate();
            connection.commit();
        }
    }

    @SneakyThrows(SQLException.class)
    @Override
    public void deleteAll() {
        String sql = "DELETE FROM comics";
        try (Connection connection = connectionManager.getConnection();
             Statement statement = connection.createStatement()) {
            statement.executeUpdate(sql);
            connection.commit();
        }
    }

    private Comics extractOne(ResultSet rs) throws SQLException {
        return new Comics(rs.getLong("id"), rs.getString("title"), rs.getString("publisher"),
                rs.getString("author"), rs.getInt("num"), rs.getBigDecimal("price"));
    }
}
