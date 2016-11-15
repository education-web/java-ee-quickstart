package ua.kpi.ip31.jee.gunawardana.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.time.LocalDate;

/**
 * ComicsHistoryRecord logs the previous states of the comics.
 *
 * @author Ruslan Gunawardana
 */
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "comics_history")
public class ComicsHistoryRecord extends Comics {
    LocalDate updateDate;

    public ComicsHistoryRecord(Comics comics, LocalDate updateDate) {
        super(comics.getTitle(), comics.getPublisher(), comics.getNumber(),
                comics.getPrice(), comics.getOnlineComics(), comics.getSuperHeroes());
        this.updateDate = updateDate;
    }
}
