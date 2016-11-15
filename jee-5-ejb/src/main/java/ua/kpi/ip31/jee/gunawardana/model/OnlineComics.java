package ua.kpi.ip31.jee.gunawardana.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.validator.constraints.URL;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.Objects;

import static javax.persistence.GenerationType.IDENTITY;
import static javax.persistence.GenerationType.SEQUENCE;

/**
 * {@link OnlineComics} POJO. Has a connected {@link Comics}.
 * Immutable. Thread-safe.
 *
 * @author Ruslan Gunawardana
 */
@Data
@NoArgsConstructor
@EqualsAndHashCode(exclude = "comics")
@ToString(exclude = "comics")
@Entity
@Table(name = "online_comics")
public class OnlineComics {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "online_comics_id")
    Long id;

    @NotNull
    @URL
    @Column(nullable = false)
    String url;

    @DecimalMin("0")
    @Digits(integer = 14, fraction = 2)
    BigDecimal price;

    @Valid
    @OneToOne
    Comics comics;

    public OnlineComics(String url, BigDecimal price) {
        this.price = price;
        this.url = url;
    }

    public void setComics(Comics newComics) {
        Comics oldComics = this.comics;
        this.comics = newComics;
        if (!Objects.equals(oldComics, newComics)) {
            if (oldComics != null) oldComics.setOnlineComics(null);
            if (newComics != null) newComics.setOnlineComics(this);
        }
    }
}
