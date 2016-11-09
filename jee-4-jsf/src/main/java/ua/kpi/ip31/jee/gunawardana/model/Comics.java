package ua.kpi.ip31.jee.gunawardana.model;

import lombok.Data;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Digits;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;
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
@Table(name = "comics")
public class Comics {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "comics_id")
    Long id;

    @NotNull
    @Size(min = 2, max = 35)
    @Column(nullable = false)
    String title;

    @NotNull
    @Size(min = 2, max = 35)
    @Column(nullable = false)
    String publisher;

    @Min(0)
    Integer number;

    @DecimalMin("0")
    @Digits(integer = 14, fraction = 2)
    BigDecimal price;

    @Valid
    @OneToOne(cascade = ALL, mappedBy = "comics", orphanRemoval = true)
    OnlineComics onlineComics;

    @Valid
    @OneToMany(cascade = ALL, fetch = EAGER, orphanRemoval = true)
    @JoinColumn(name = "comics_id", nullable = false)
    List<SuperHero> superHeroes;

    protected Comics() {}

    public Comics(String title, String publisher, Integer number, BigDecimal price,
                  OnlineComics onlineComics, List<SuperHero> superHeroes) {
        this.title = title;
        this.publisher = publisher;
        this.number = number;
        this.onlineComics = onlineComics;
        if (onlineComics != null) onlineComics.setComics(this);
        this.superHeroes = superHeroes;
        this.price = price;
    }

    public void setOnlineComics(OnlineComics newOnlineComics) {
        OnlineComics oldOnlineComics = this.onlineComics;
        this.onlineComics = newOnlineComics;
        System.out.println(oldOnlineComics);
        System.out.println(newOnlineComics);
        if (!Objects.equals(oldOnlineComics, newOnlineComics)) {
            if (oldOnlineComics != null) oldOnlineComics.setComics(null);
            if (newOnlineComics != null) newOnlineComics.setComics(this);
        }
    }
}
