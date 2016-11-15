package ua.kpi.ip31.jee.gunawardana.service;

import lombok.extern.log4j.Log4j2;
import ua.kpi.ip31.jee.gunawardana.model.Comics;
import ua.kpi.ip31.jee.gunawardana.model.ComicsHistoryRecord;
import ua.kpi.ip31.jee.gunawardana.repository.ComicsHistoryRepository;
import ua.kpi.ip31.jee.gunawardana.repository.ComicsRepository;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.inject.Inject;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

/**
 *
 * @author Ruslan Gunawardana
 */
@Stateless
@TransactionAttribute
@Log4j2
public class ComicsService {
    ComicsRepository comicsRepository;
    ComicsHistoryRepository comicsHistoryRepository;

    /**
     * Required only to satisfy EJB specification.
     * @deprecated  Should not be called. {@link Inject} annotated constructor should be used instead.
     */
    @Deprecated
    public ComicsService() {
    }

    @Inject
    public ComicsService(ComicsRepository comicsRepository, ComicsHistoryRepository comicsHistoryRepository) {
        this.comicsRepository = comicsRepository;
        this.comicsHistoryRepository = comicsHistoryRepository;
    }

    public List<Comics> findAll() {
        return comicsRepository.findAll();
    }

    public Optional<Comics> findById(long id) {
        return comicsRepository.findById(id);
    }

    public List<Comics> findByPublisher(String publisher) {
        return comicsRepository.findByPublisher(publisher);
    }

    public Comics save(Comics comics) {
        Comics saved = comicsRepository.save(comics);
        comicsHistoryRepository.save(new ComicsHistoryRecord(comics, LocalDate.now()));
        return saved;
    }
}
