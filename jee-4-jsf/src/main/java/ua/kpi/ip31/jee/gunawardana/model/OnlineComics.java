package ua.kpi.ip31.jee.gunawardana.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.validator.constraints.URL;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.validation.Valid;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

import static javax.persistence.GenerationType.IDENTITY;

/**
 * {@link OnlineComics} POJO. Has a connected {@link Comics}.
 * Immutable. Thread-safe.
 *
 * @author Ruslan Gunawardana
 */
@Data
@ToString(exclude = "comics")
@NoArgsConstructor
@Entity
public class OnlineComics {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    long id;

    @NotNull
    @URL
    String url;

    @DecimalMin("0")
    @Digits(integer = 14, fraction = 2)
    BigDecimal price;

    @Valid
    @OneToOne(mappedBy = "onlineComics")
    Comics comics;

    public OnlineComics(String url, BigDecimal price) {
        this.url = url;
        this.price = price;
    }
}
