package ua.kpi.ip31.jee.gunawardana.repository;

import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j2;
import lombok.val;
import ua.kpi.ip31.jee.gunawardana.model.Comics;
import ua.kpi.ip31.jee.gunawardana.model.Comics_;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

/**
 * Repository implementation grants access to {@link Comics}, persisted in JPA data storage.
 * Immutable. Thread-safe.
 *
 * @author Ruslan Gunawardana
 */
@Log4j2
@NoArgsConstructor
@Transactional
@ApplicationScoped
public class JpaComicsRepository implements ComicsRepository {
    @Inject EntityManager em;

    @Override
    public Optional<Comics> findById(long id) {
        log.debug("comics with id: {} queried", id);
        return Optional.ofNullable(em.find(Comics.class, id));
    }

    @Override
    public List<Comics> findAll() {
        log.debug("all comics queried");
        val query = em.getCriteriaBuilder().createQuery(Comics.class);
        val comics = query.from(Comics.class);
        query.select(comics);
        return em.createQuery(query).getResultList();
    }

    @Override
    public List<Comics> findByPublisher(String publisher) {
        log.debug("publisher comics queried: {}", publisher);
        val builder = em.getCriteriaBuilder();
        val query = builder.createQuery(Comics.class);
        val comics = query.from(Comics.class);
        query.select(comics);
        query.where(builder.equal(comics.get(Comics_.publisher), publisher));
        return em.createQuery(query).getResultList();
    }

    @Override
    public Comics save(Comics comics) {
        log.debug("comics saved: {}", comics);
        return em.merge(comics);
    }

    @Override
    public void update(Comics comics) {
        log.debug("comics updated: {}", comics);
        em.merge(comics);
    }

    @Override
    public void deleteAll() {
        log.debug("all comics deleted");
        val delete = em.getCriteriaBuilder().createCriteriaDelete(Comics.class);
        delete.from(Comics.class);
        em.createQuery(delete).executeUpdate();
    }
}
