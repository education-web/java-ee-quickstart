package ua.kpi.ip31.jee.gunawardana.repository;

import lombok.SneakyThrows;
import ua.kpi.ip31.jee.gunawardana.model.ComicsHistoryRecord;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.ejb.TransactionManagement;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceException;
import javax.transaction.UserTransaction;

import static javax.ejb.TransactionAttributeType.REQUIRES_NEW;
import static javax.ejb.TransactionManagementType.BEAN;

/**
 * Repository used to log all comics history records.
 */
@Stateless
@TransactionAttribute(REQUIRES_NEW)
@TransactionManagement(BEAN)
public class ComicsHistoryRepository {
    EntityManager entityManager;
    UserTransaction transaction;

    /**
     * Required only to satisfy EJB specification.
     * @deprecated  Should not be called. {@link Inject} annotated constructor should be used instead.
     */
    @Deprecated
    public ComicsHistoryRepository() {
    }

    @Inject
    public ComicsHistoryRepository(EntityManager entityManager, UserTransaction transaction) {
        this.entityManager = entityManager;
        this.transaction = transaction;
    }

    @SneakyThrows
    public ComicsHistoryRecord save(ComicsHistoryRecord record) {
        transaction.begin();
        try {
            ComicsHistoryRecord savedRecord = entityManager.merge(record);
            if (true) throw new PersistenceException("Shit");
            transaction.commit();
            return savedRecord;
        } catch (Exception pe) {
            try {
                transaction.rollback();
            } catch (Exception txE) {
                pe.addSuppressed(txE);
            }
            throw pe;
        }
    }
}
