package ua.kpi.ip31.jee.gunawardana.model;

import lombok.*;
import lombok.experimental.FieldDefaults;
import lombok.experimental.Wither;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

/**
 * {@link Comics} POJO. Immutable. Thread-safe.
 * Has a connected {@link OnlineComics} comics.
 *
 * @author Ruslan Gunawardana
 */
@Value
@Wither
@AllArgsConstructor
public class Comics {
    long id;
    String title;
    String publisher;
    int number;
    BigDecimal price;
    OnlineComics onlineComics;
    List<SuperHero> superHeroes;

    public Comics(String title, String publisher, int number, BigDecimal price,
                  OnlineComics onlineComics, List<SuperHero> superHeroes) {
        this.id = -1;
        this.title = title;
        this.publisher = publisher;
        this.number = number;
        this.onlineComics = onlineComics;
        this.superHeroes = superHeroes;
        this.price = price;
    }

    public Optional<OnlineComics> getOnlineComics() {
        return Optional.ofNullable(onlineComics);
    }
}
