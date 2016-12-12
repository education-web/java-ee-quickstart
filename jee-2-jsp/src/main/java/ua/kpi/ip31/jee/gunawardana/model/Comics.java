package ua.kpi.ip31.jee.gunawardana.model;

import lombok.AllArgsConstructor;
import lombok.Value;
import lombok.experimental.Wither;

import java.math.BigDecimal;

/**
 * Comics POJO. Immutable. Thread-safe.
 */
@Value
@Wither
@AllArgsConstructor
public class Comics {
    Long id;
    String title;
    String publisher;
    String author;
    int number;
    BigDecimal price;

    public Comics(String title, String publisher, String author, int number, BigDecimal price) {
        this.id = null;
        this.title = title;
        this.publisher = publisher;
        this.author = author;
        this.number = number;
        this.price = price;
    }
}
