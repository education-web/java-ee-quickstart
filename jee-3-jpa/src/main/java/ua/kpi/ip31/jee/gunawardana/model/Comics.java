package ua.kpi.ip31.jee.gunawardana.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.validation.Valid;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Digits;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.math.BigDecimal;
import java.util.List;
import java.util.Set;

import static javax.persistence.CascadeType.ALL;
import static javax.persistence.FetchType.EAGER;
import static javax.persistence.GenerationType.IDENTITY;

/**
 * {@link Comics} POJO. Immutable. Thread-safe.
 * Has a connected {@link OnlineComics} comics.
 *
 * @author Ruslan Gunawardana
 */
@Data
@Entity
public class Comics {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    Long id;

    @NotNull
    @Size(min = 2, max = 35)
    String title;

    @NotNull
    @Size(min = 2, max = 35)
    String publisher;

    @Min(0)
    Integer number;

    @DecimalMin("0")
    @Digits(integer = 14, fraction = 2)
    BigDecimal price;

    @Valid
    @OneToOne(cascade = ALL, orphanRemoval = true)
    OnlineComics onlineComics;

    @Valid
    @OneToMany(cascade = ALL, fetch = EAGER, orphanRemoval = true)
    @JoinColumn(nullable = false)
    List<SuperHero> superHeroes;

    protected Comics() {}

    public Comics(String title, String publisher, Integer number, BigDecimal price,
                  OnlineComics onlineComics, List<SuperHero> superHeroes) {
        this.title = title;
        this.publisher = publisher;
        this.number = number;
        this.onlineComics = onlineComics;
        this.superHeroes = superHeroes;
        this.price = price;
    }
}
