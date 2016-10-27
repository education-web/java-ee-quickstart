package ua.kpi.ip31.jee.gunawardana.model;

import lombok.AllArgsConstructor;
import lombok.Value;
import lombok.experimental.Wither;

/**
 * {@link SuperHero} POJO. Appears in a connected {@link Comics}.
 * Immutable. Thread-safe.
 *
 * @author Ruslan Gunawardana
 */
@Value
@Wither
@AllArgsConstructor
public class SuperHero {
    int id;
    String name;
    String alterEgo;

    public SuperHero(String name, String alterEgo) {
        this.id = -1;
        this.name = name;
        this.alterEgo = alterEgo;
    }
}
