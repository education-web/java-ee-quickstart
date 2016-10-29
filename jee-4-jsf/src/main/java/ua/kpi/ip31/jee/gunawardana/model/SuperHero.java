package ua.kpi.ip31.jee.gunawardana.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

import static javax.persistence.GenerationType.IDENTITY;

/**
 * {@link SuperHero} POJO. Appears in a connected {@link Comics}.
 * Immutable. Thread-safe.
 *
 * @author Ruslan Gunawardana
 */
@Data
@NoArgsConstructor
@Entity
public class SuperHero {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    int id;

    @NotNull
    String name;

    String alterEgo;

    public SuperHero(String name, String alterEgo) {
        this.id = -1;
        this.name = name;
        this.alterEgo = alterEgo;
    }
}
