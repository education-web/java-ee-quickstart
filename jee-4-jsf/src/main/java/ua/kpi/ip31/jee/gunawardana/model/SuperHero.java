package ua.kpi.ip31.jee.gunawardana.model;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

import static javax.persistence.GenerationType.IDENTITY;

/**
 * {@link SuperHero} POJO. Appears in a connected {@link Comics}.
 * Immutable. Thread-safe.
 *
 * @author Ruslan Gunawardana
 */
@Data
@Entity
@Table(name = "super_hero")
public class SuperHero {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "super_hero_id")
    Integer id;

    @NotNull
    String name;

    String alterEgo;

    protected SuperHero() {}

    public SuperHero(String name) {
        this.name = name;
    }

    public SuperHero(String name, String alterEgo) {
        this.id = -1;
        this.name = name;
        this.alterEgo = alterEgo;
    }

    @Override
    public String toString() {
        return name;
    }
}
