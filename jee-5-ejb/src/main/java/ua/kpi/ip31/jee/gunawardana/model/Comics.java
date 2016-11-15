package ua.kpi.ip31.jee.gunawardana.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static javax.persistence.CascadeType.ALL;
import static javax.persistence.FetchType.EAGER;
import static javax.persistence.GenerationType.SEQUENCE;
import static javax.persistence.InheritanceType.TABLE_PER_CLASS;

/**
 * {@link Comics} POJO. Immutable. Thread-safe.
 * Has a connected {@link OnlineComics} comics.
 *
 * @author Ruslan Gunawardana
 */
@Data
@NoArgsConstructor
@Entity
@Inheritance(strategy = TABLE_PER_CLASS)
@Table(name = "comics")
public class Comics {
    @Id
    @GeneratedValue(strategy = SEQUENCE)
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

    public Comics(String title, String publisher, Integer number, BigDecimal price,
                  OnlineComics onlineComics, List<SuperHero> superHeroes) {
        this.title = title;
        this.publisher = publisher;
        this.number = number;
        this.price = price;
        this.onlineComics = onlineComics;
        if (onlineComics != null) onlineComics.setComics(this);
        this.setSuperHeroes(superHeroes);
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

    public void setSuperHeroes(List<SuperHero> superHeroes) {
        this.superHeroes = (superHeroes != null) ? new ArrayList<>(superHeroes) : null;
    }
}
