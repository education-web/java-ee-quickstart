package ua.kpi.ip31.jee.gunawardana.repository;

import lombok.SneakyThrows;
import ua.kpi.ip31.jee.gunawardana.connection.ConnectionManager;
import ua.kpi.ip31.jee.gunawardana.model.Comics;
import ua.kpi.ip31.jee.gunawardana.model.OnlineComics;
import ua.kpi.ip31.jee.gunawardana.model.SuperHero;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import static java.util.stream.Collectors.toList;

/**
 * Repository implementation grants access to {@link Comics} in the PostgreSQL RDBMS.
 * Immutable. Thread-safe.
 *
 * @author Ruslan Gunawardana
 */
public final class PostgresJdbcComicsRepository implements ComicsRepository {
    private final ConnectionManager connectionManager;

    public PostgresJdbcComicsRepository(ConnectionManager connectionManager) {
        this.connectionManager = connectionManager;
    }

    private static final String FIND_BY_ID_SQL = "" +
            "SELECT\n" +
            "  comics_id,\n" +
            "  title,\n" +
            "  publisher,\n" +
            "  num,\n" +
            "  price,\n" +
            "  online_comics_id,\n" +
            "  url,\n" +
            "  size,\n" +
            "  super_hero_id,\n" +
            "  name,\n" +
            "  alter_ego\n" +
            "FROM comics\n" +
            "  LEFT JOIN online_comics USING (online_comics_id)\n" +
            "  LEFT JOIN super_hero USING (comics_id)\n" +
            "WHERE comics_id = ?";

    @SneakyThrows(SQLException.class)
    @Override
    public Optional<Comics> findById(long id) {
        try (Connection connection = connectionManager.getConnection();
             PreparedStatement statement = connection.prepareStatement(FIND_BY_ID_SQL)) {
            statement.setLong(1, id);
            try (ResultSet rs = statement.executeQuery()) {
                return extractSingleComics(rs);
            }
        }
    }

    private static final String FIND_ALL_SQL = "" +
            "SELECT\n" +
            "  comics_id,\n" +
            "  title,\n" +
            "  publisher,\n" +
            "  num,\n" +
            "  price,\n" +
            "  online_comics_id,\n" +
            "  url,\n" +
            "  size,\n" +
            "  super_hero_id,\n" +
            "  name,\n" +
            "  alter_ego\n" +
            "FROM comics\n" +
            "  LEFT JOIN online_comics USING (online_comics_id)\n" +
            "  LEFT JOIN super_hero USING (comics_id)\n" +
            "ORDER BY comics_id";

    @SneakyThrows(SQLException.class)
    @Override
    public List<Comics> findAll() {
        try (Connection connection = connectionManager.getConnection();
             Statement statement = connection.createStatement();
             ResultSet rs = statement.executeQuery(FIND_ALL_SQL)) {
            return extractAllComics(rs);
        }
    }

    private static final String FIND_BY_PUBLISHER_SQL = "" +
            "SELECT\n" +
            "  comics_id,\n" +
            "  title,\n" +
            "  publisher,\n" +
            "  num,\n" +
            "  price,\n" +
            "  online_comics_id,\n" +
            "  url,\n" +
            "  size,\n" +
            "  super_hero_id,\n" +
            "  name,\n" +
            "  alter_ego\n" +
            "FROM comics\n" +
            "  LEFT JOIN online_comics USING (online_comics_id)\n" +
            "  LEFT JOIN super_hero USING (comics_id)\n" +
            "WHERE publisher = ?\n" +
            "ORDER BY comics_id";

    @SneakyThrows(SQLException.class)
    @Override
    public List<Comics> findByPublisher(String publisher) {
        Objects.requireNonNull(publisher);
        try (Connection connection = connectionManager.getConnection();
             PreparedStatement statement = connection.prepareStatement(FIND_BY_PUBLISHER_SQL)) {
            statement.setString(1, publisher);
            try (ResultSet rs = statement.executeQuery()) {
                return extractAllComics(rs);
            }
        }
    }

    private Optional<Comics> extractSingleComics(ResultSet rs) throws SQLException {
        if (!rs.next()) Optional.empty();
        Comics comics = extractThisRow(rs);
        while (rs.next()) extractSuperHeroToList(rs, comics.getSuperHeroes());
        return Optional.of(comics);
    }

    private List<Comics> extractAllComics(ResultSet rs) throws SQLException {
        List<Comics> result = new ArrayList<>();
        Comics lastComics = null;
        while (rs.next()) {
            if (lastComics != null && lastComics.getId() == rs.getLong("comics_id")) {
                extractSuperHeroToList(rs, lastComics.getSuperHeroes());
            } else {
                lastComics = extractThisRow(rs);
                result.add(lastComics);
            }
        }
        return result;
    }

    private Comics extractThisRow(ResultSet rs) throws SQLException {
        long online_comics_id = rs.getLong("online_comics_id");
        OnlineComics onlineComics;
        if (!rs.wasNull()) {
            onlineComics = new OnlineComics(online_comics_id, rs.getString("url"), rs.getLong("size"));
        } else {
            onlineComics = null;
        }
        return new Comics(rs.getLong("comics_id"), rs.getString("title"), rs.getString("publisher"), rs.getInt("num"),
                rs.getBigDecimal("price"), onlineComics, extractSuperHeroToList(rs, new ArrayList<>()));
    }

    private List<SuperHero> extractSuperHeroToList(ResultSet rs, List<SuperHero> heroes) throws SQLException {
        int superHeroId = rs.getInt("super_hero_id");
        if (!rs.wasNull()) heroes.add(new SuperHero(superHeroId, rs.getString("name"), rs.getString("alter_ego")));
        return heroes;
    }

    private static final String INSERT_ONLINE_COMICS_SQL = "" +
            "INSERT INTO online_comics (url, size)\n" +
            "VALUES (?, ?)\n" +
            "RETURNING online_comics_id";
    private static final String INSERT_COMICS_SQL = "" +
            "INSERT INTO comics (title, publisher, num, online_comics_id, price)\n" +
            "VALUES (?, ?, ?, ?, ?)\n" +
            "RETURNING comics_id";
    private static final String INSERT_SUPER_HERO_SQL = "" +
            "INSERT INTO super_hero (comics_id, name, alter_ego)\n" +
            "VALUES (?, ?, ?)\n" +
            "RETURNING super_hero_id";

    @SneakyThrows(SQLException.class)
    @Override
    public Comics save(Comics comics) {
        Objects.requireNonNull(comics);
        try (Connection connection = connectionManager.getConnection();
             PreparedStatement onlineComicsStatement = connection.prepareStatement(INSERT_ONLINE_COMICS_SQL);
             PreparedStatement comicsStatement = connection.prepareStatement(INSERT_COMICS_SQL);
             PreparedStatement superHeroStatement = connection.prepareStatement(INSERT_SUPER_HERO_SQL)) {
            Comics toBeSavedComics = comics.getOnlineComics()
                    .map(c -> comics.withOnlineComics(saveOnlineComics(onlineComicsStatement, c)))
                    .orElse(comics);
            Comics savedComics = saveComics(comicsStatement, toBeSavedComics);
            List<SuperHero> savedSuperHeroes = savedComics.getSuperHeroes().stream()
                    .map(hero -> saveSuperHero(superHeroStatement, savedComics, hero))
                    .collect(toList());
            connection.commit();
            return savedComics.withSuperHeroes(savedSuperHeroes);
        }
    }

    @SneakyThrows(SQLException.class)
    private OnlineComics saveOnlineComics(PreparedStatement statement, OnlineComics onlineComics) {
        statement.setString(1, onlineComics.getUrl());
        statement.setLong(2, onlineComics.getSize());
        try (ResultSet rs = statement.executeQuery()) {
            rs.next();
            return onlineComics.withId(rs.getLong(1));
        }
    }

    @SneakyThrows(SQLException.class)
    private Comics saveComics(PreparedStatement comicsStmnt, Comics comics) {
        comicsStmnt.setString(1, comics.getTitle());
        comicsStmnt.setString(2, comics.getPublisher());
        comicsStmnt.setInt(3, comics.getNumber());
        Optional<OnlineComics> onlineComics = comics.getOnlineComics();
        if (onlineComics.isPresent()) {
            comicsStmnt.setLong(4, onlineComics.get().getId());
        } else {
            comicsStmnt.setNull(4, Types.BIGINT);
        }
        comicsStmnt.setBigDecimal(5, comics.getPrice());
        try (ResultSet rs = comicsStmnt.executeQuery()) {
            rs.next();
            return comics.withId(rs.getLong(1));
        }
    }

    @SneakyThrows(SQLException.class)
    private SuperHero saveSuperHero(PreparedStatement statement, Comics comics, SuperHero hero) {
        if (hero == null) return null;
        statement.setLong(1, comics.getId());
        statement.setString(2, hero.getName());
        statement.setString(3, hero.getAlterEgo());
        try (ResultSet rs = statement.executeQuery()) {
            rs.next();
            return hero.withId(rs.getInt(1));
        }
    }

    private static final String UPDATE_COMICS_SQL = "" +
            "UPDATE comics\n" +
            "SET title = ?, publisher = ?, num = ?, price = ?, online_comics_id = ?\n" +
            "WHERE comics_id = ?;" +
            "UPDATE online_comics\n" +
            "SET url = ?, size = ?\n" +
            "WHERE online_comics_id = ?";

    private static final String UPDATE_SUPER_HERO_SQL = "" +
            "UPDATE super_hero\n" +
            "SET comics_id = ?, name = ?, alter_ego = ?\n" +
            "WHERE super_hero_id = ?";

    @SneakyThrows(SQLException.class)
    @Override
    public void update(Comics comics) {
        try (Connection connection = connectionManager.getConnection();
             PreparedStatement comicsStmnt = connection.prepareStatement(UPDATE_COMICS_SQL);
             PreparedStatement heroStmnt = connection.prepareStatement(UPDATE_SUPER_HERO_SQL)) {
            comicsStmnt.setString(1, comics.getTitle());
            comicsStmnt.setString(2, comics.getPublisher());
            comicsStmnt.setInt(3, comics.getNumber());
            comicsStmnt.setBigDecimal(4, comics.getPrice());

            Optional<OnlineComics> maybeOnlineComics = comics.getOnlineComics();
            if (maybeOnlineComics.isPresent()) {
                OnlineComics online = maybeOnlineComics.get();
                comicsStmnt.setLong(5, online.getId());
                comicsStmnt.setString(7, online.getUrl());
                comicsStmnt.setLong(8, online.getSize());
                comicsStmnt.setLong(9, online.getId());
            } else {
                comicsStmnt.setNull(5, Types.BIGINT);
            }
            comicsStmnt.setLong(6, comics.getId());
            comicsStmnt.executeUpdate();

            for (SuperHero hero : comics.getSuperHeroes()) {
                heroStmnt.setLong(1, comics.getId());
                heroStmnt.setString(2, hero.getName());
                heroStmnt.setString(3, hero.getAlterEgo());
                heroStmnt.setInt(4, hero.getId());
            }
            heroStmnt.executeUpdate();

            connection.commit();
        }
    }

    private static final String DELETE_ALL_SQL = "" +
            "DELETE FROM super_hero;\n" +
            "DELETE FROM comics;\n" +
            "DELETE FROM online_comics";

    @SneakyThrows(SQLException.class)
    @Override
    public void deleteAll() {
        try (Connection connection = connectionManager.getConnection();
             Statement statement = connection.createStatement()) {
            statement.executeUpdate(DELETE_ALL_SQL);
            connection.commit();
        }
    }
}
