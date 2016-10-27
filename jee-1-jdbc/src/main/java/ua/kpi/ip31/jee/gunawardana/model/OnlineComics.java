package ua.kpi.ip31.jee.gunawardana.model;

import lombok.AllArgsConstructor;
import lombok.Value;
import lombok.experimental.Wither;

/**
 * {@link OnlineComics} POJO. Has a connected {@link Comics}.
 * Immutable. Thread-safe.
 *
 * @author Ruslan Gunawardana
 */
@Value
@Wither
@AllArgsConstructor
public class OnlineComics {
    long id;
    String url;
    long size;

    public OnlineComics(String url, long size) {
        this.id = -1;
        this.size = size;
        this.url = url;
    }
}
